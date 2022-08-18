package cn.zhgliu.ezdp.resolver.impl.ali.druid.visitor;

import cn.hutool.json.JSONUtil;
import cn.zhgliu.ezdp.consts.Relation;
import cn.zhgliu.ezdp.consts.ValueType;
import cn.zhgliu.ezdp.model.DataPermissionItem;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.ast.expr.MySqlCharExpr;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlASTVisitorAdapter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static cn.zhgliu.ezdp.resolver.impl.ali.druid.visitor.PermissionProcessMysqlVisitorHelper.getOperator;
import static cn.zhgliu.ezdp.resolver.impl.ali.druid.visitor.PermissionProcessMysqlVisitorHelper.getValueExpr;

/**
 * @author zhgliu
 */
public class PermissionProcessMysqlVisitor extends MySqlASTVisitorAdapter {

    Logger log = LoggerFactory.getLogger(PermissionProcessMysqlVisitor.class);

    private ThreadLocal<List<DataPermissionItem>> localRules;

    /**
     * 这个数据结构的含义是这样的：
     * 最外面的Map是权限组的id，不同的权限组之间是或的关系
     * 第二层的Map是字段名，同一权限组中的相同字段被分为一组，各个字段之间是且的关系
     * 第三层的List是一个字段的多个条件，这些条件之间是或的关系
     */
    private ThreadLocal<Map<String, Map<String, List<DataPermissionItem>>>> groupedRules;
    /**
     * 2021-2-4
     * by:liuzhiguang@ecmsglobal.com
     * 之前的设计存在问题：当一个人有多个角色的时候，查询到的权限列表是将几个角色的权限混到一起返回的，会导致权限混乱。
     * 这次改进是在原来的基础上，再增加一层list，list中按角色将权限分组，最后拼装的时候，将这些组以 or 的关系连接起来。
     **/
    private ThreadLocal<List<List<DataPermissionItem>>> localGroupedRules;
    private ThreadLocal<List<Map<String, Map<String, List<DataPermissionItem>>>>> roleGroupedRules;

    private ThreadLocal<String> applyMethod;

    private ThreadLocal<String> tableAlias;


    private PermissionProcessMysqlVisitor() {
        localRules = new ThreadLocal<>();
        groupedRules = new ThreadLocal<>();
        applyMethod = new ThreadLocal<>();
        tableAlias = new ThreadLocal<>();
        localGroupedRules = new ThreadLocal<>();
        roleGroupedRules = new ThreadLocal<>();
    }

    private static PermissionProcessMysqlVisitor instance = new PermissionProcessMysqlVisitor();

    public static PermissionProcessMysqlVisitor getInstanceWithGroupRules(List<List<DataPermissionItem>> rules) {
        instance.setLocalGroupedRules(rules);
        return instance;
    }

    private void setLocalGroupedRules(List<List<DataPermissionItem>> localGroupedRules) {
        this.localGroupedRules.set(localGroupedRules);
    }

    @Override
    public boolean visit(SQLSelectStatement selectStatement) {

//        groupRules(this.localRules.get());
        groupRoleGroupedRules(this.localGroupedRules.get());

        processStatement(selectStatement);

        log.debug("处理完成的sql为：", SQLUtils.toSQLString(selectStatement));

        return true;
    }

    private void groupRoleGroupedRules(List<List<DataPermissionItem>> lists) {
        roleGroupedRules.set(new LinkedList<Map<String, Map<String, List<DataPermissionItem>>>>());
        for (List<DataPermissionItem> oneRoleRules : lists) {
            groupRules(oneRoleRules);
            Map<String, Map<String, List<DataPermissionItem>>> stringMapMap = this.groupedRules.get();
            roleGroupedRules.get().add(stringMapMap);
            this.groupedRules.remove();
        }
    }

    //将传入的条件按权限组分组、将逗号分隔的条件拆分成独立的条件
    private void groupRules(List<DataPermissionItem> rules) {
        DataPermissionItem firstRule = rules.get(0);
        applyMethod.set(firstRule.getApplyMethod());

        groupedRules.set(new HashMap<String, Map<String, List<DataPermissionItem>>>());
        for (DataPermissionItem rule : rules) {
            if (Relation.valueOf(rule.getRelation()) == Relation.NOT_CONTROL
                    || ValueType.valueOf(rule.getValueType()) == ValueType.ALL_VALUE) {
                continue;
            }
            Integer metadataId = rule.getMetadataId();

            if (groupedRules.get().get(metadataId.toString()) == null) {
                groupedRules.get().put(metadataId.toString(), new HashMap<>());
            }
            Map<String, List<DataPermissionItem>> oneGroupRules = groupedRules.get().get(String.valueOf(metadataId));

            String fieldName = rule.getFieldName();
            if (oneGroupRules.get(fieldName) == null) {
                oneGroupRules.put(fieldName, new LinkedList<>());
            }
            List<DataPermissionItem> oneFieldRules = oneGroupRules.get(fieldName);

            log.debug(JSONUtil.toJsonStr(rule));
            oneFieldRules.addAll(PermissionProcessMysqlVisitorHelper.splitRule(rule));
        }
        log.debug(JSONUtil.toJsonPrettyStr(groupedRules.get()));

    }

    private ThreadLocal<Map<String, String>> tableNameAliasMapping = new ThreadLocal<>();

    //汇总所有的表，以表的原名和别名为key，以别名为value，加入到mapping中
    private void getAndSetCoreTableAlias(SQLTableSource from) {
        String coreTableAlias = "";
        if (from instanceof SQLExprTableSource) {
            String tableName = ((SQLName) ((SQLExprTableSource) from).getExpr()).getSimpleName();
            if (StringUtils.isNotEmpty(from.getAlias())) {
                coreTableAlias = from.getAlias();
            } else {
                coreTableAlias = tableName;
//              对于mybatis，在表没有别名的情况下，给表增加一个别名
                from.setAlias(coreTableAlias);
            }

            tableNameAliasMapping.get().put(tableName, coreTableAlias);
            tableNameAliasMapping.get().put(coreTableAlias, coreTableAlias);
        }
        if (from instanceof SQLJoinTableSource) {
            SQLJoinTableSource joinFrom = (SQLJoinTableSource) from;

            getAndSetCoreTableAlias(joinFrom.getRight());
            getAndSetCoreTableAlias(joinFrom.getLeft());
        }
        if (from instanceof SQLSubqueryTableSource) {
            tableNameAliasMapping.get().put(from.getAlias(), from.getAlias());
            SQLSubqueryTableSource joinFrom = (SQLSubqueryTableSource) from;
            getAndSetCoreTableAlias(joinFrom.getSelect().getQueryBlock().getFrom());
        }
    }

    // 再套一层循环，调用原来的方法，将按角色分组的权限生成druid对象。
    private List<Map<String, Map<String, List<SQLBinaryOpExpr>>>> createRoleBaseConditions() {
        List<Map<String, Map<String, List<SQLBinaryOpExpr>>>> ret = new LinkedList<>();
        List<Map<String, Map<String, List<DataPermissionItem>>>> maps = roleGroupedRules.get();
        for (int i = 0; i < maps.size(); i++) {
            Map<String, Map<String, List<DataPermissionItem>>> oneRoleConditions = maps.get(i);
            Map<String, Map<String, List<SQLBinaryOpExpr>>> oneRoleDruidOjbects = createBaseConditions(oneRoleConditions);
            ret.add(oneRoleDruidOjbects);
        }
        return ret;
    }

    //将整理好的条件，按照原来的结构，转换成druid的条件对象
    private Map<String, Map<String, List<SQLBinaryOpExpr>>> createBaseConditions(Map<String, Map<String, List<DataPermissionItem>>> originalRules) {
        //要返回的主结构
        Map<String, Map<String, List<SQLBinaryOpExpr>>> ret = new HashMap<>();

        for (Map.Entry<String, Map<String, List<DataPermissionItem>>> stringMapEntry : originalRules.entrySet()) {
            //建立起对应的第一层结构
            if (ret.get(stringMapEntry.getKey()) == null) {
                ret.put(stringMapEntry.getKey(), new HashMap<String, List<SQLBinaryOpExpr>>());
            }

            Set<Map.Entry<String, List<DataPermissionItem>>> oneGroupRules = stringMapEntry.getValue().entrySet();
            for (Map.Entry<String, List<DataPermissionItem>> oneGroupRule : oneGroupRules) {
                //建立起第二层结构
                if (ret.get(stringMapEntry.getKey()).get(oneGroupRule.getKey()) == null) {
                    ret.get(stringMapEntry.getKey()).put(oneGroupRule.getKey(), new LinkedList<SQLBinaryOpExpr>());
                }
                for (DataPermissionItem DatapermissionItem : oneGroupRule.getValue()) {
                    ret.get(stringMapEntry.getKey()).get(oneGroupRule.getKey()).add(createOneRule(DatapermissionItem));
                }
            }
        }
        return ret;
    }

    //根据一个EC的查询条件，创建一个druid的条件对象
    private SQLBinaryOpExpr createOneRule(DataPermissionItem DatapermissionItem) {
        String valueType = DatapermissionItem.getValueType();
        String fieldValue = DatapermissionItem.getFieldValue();
        //获取到的值如果是保留字“EMPTY_VALUE”，则生成 is null or ='' 的条件
        if (ValueType.valueOf(valueType) != ValueType.EMPTY_VALUE) {
            SQLBinaryOpExpr ret = new SQLBinaryOpExpr();
            String tableAlias = getTableAlias(DatapermissionItem);
            if (StringUtils.isEmpty(tableAlias)) {
                ret.setLeft(new SQLIdentifierExpr(DatapermissionItem.getFieldName()));
            } else {
                ret.setLeft(new SQLPropertyExpr(tableAlias, DatapermissionItem.getFieldName()));
            }
            ret.setRight(getValueExpr(DatapermissionItem));
            ret.setOperator(getOperator(DatapermissionItem.getRelation()));
            return ret;
        } else {
            //这段代码改的比较难看，实现的目的是当设置了值为“EMPTY”的时候，生成查询空值的条件
            SQLBinaryOpExpr finalRet = new SQLBinaryOpExpr();

            SQLBinaryOpExpr ret = new SQLBinaryOpExpr();
            String tableAlias = getTableAlias(DatapermissionItem);
            if (StringUtils.isEmpty(tableAlias)) {
                ret.setLeft(new SQLIdentifierExpr(DatapermissionItem.getFieldName()));
            } else {
                ret.setLeft(new SQLPropertyExpr(tableAlias, DatapermissionItem.getFieldName()));
            }
            ret.setOperator(SQLBinaryOperator.Equality);
            ret.setRight(new MySqlCharExpr(""));

            finalRet.setLeft(ret);

            SQLBinaryOpExpr ret1 = new SQLBinaryOpExpr();
            if (StringUtils.isEmpty(tableAlias)) {
                ret1.setLeft(new SQLIdentifierExpr(DatapermissionItem.getFieldName()));
            } else {
                ret1.setLeft(new SQLPropertyExpr(tableAlias, DatapermissionItem.getFieldName()));
            }
            ret1.setOperator(SQLBinaryOperator.Is);
            ret1.setRight(new SQLNullExpr());

            finalRet.setRight(ret1);
            finalRet.setOperator(SQLBinaryOperator.BooleanOr);
            finalRet.setBracket(true);
            return finalRet;
        }
    }

    //根据EC对象，获取当前字段对应表别名，如果没有，返回null
    private String getTableAlias(DataPermissionItem DatapermissionItem) {
        String targetTableName = DatapermissionItem.getTargetTableName();
        if (StringUtils.isEmpty(targetTableName)) {
            return null;
        }
        String alias = tableNameAliasMapping.get().get(targetTableName);
        if (StringUtils.isEmpty(alias)) {
            throw new RuntimeException("数据权限系统中，指定的表名" + targetTableName + "即不是原sql中的表原名，也不是原sql中的表别名。请修改后再试。");
        }
        return alias;
    }

    // 对下面的方法进行循环调用
    private SQLBinaryOpExpr createPermissionConditions(List<Map<String, Map<String, List<SQLBinaryOpExpr>>>> firstStepResult) {
        List<SQLBinaryOpExpr> roleSQLBinaryOpExprList = new LinkedList<>();
        for (Map<String, Map<String, List<SQLBinaryOpExpr>>> stringMapMap : firstStepResult) {
            SQLBinaryOpExpr permissionConditions = createPermissionConditions(stringMapMap);
            if (permissionConditions != null) {
                roleSQLBinaryOpExprList.add(permissionConditions);
            }
        }
        if (roleSQLBinaryOpExprList != null && roleSQLBinaryOpExprList.size() > 0) {
            return joinConditions(roleSQLBinaryOpExprList, SQLBinaryOperator.BooleanOr);
        } else {
            return null;
        }
    }

    private SQLBinaryOpExpr createPermissionConditions(Map<String, Map<String, List<SQLBinaryOpExpr>>> firstStepResult) {
        List<SQLBinaryOpExpr> finalRuleList = new LinkedList<>();
        for (String groupName : firstStepResult.keySet()) {
            Map<String, List<SQLBinaryOpExpr>> oneGroupRule = firstStepResult.get(groupName);
            List<SQLBinaryOpExpr> oneGroupRuleList = new LinkedList<>();
            for (String colName : oneGroupRule.keySet()) {
                List<SQLBinaryOpExpr> oneColumnRule = oneGroupRule.get(colName);
                SQLBinaryOpExpr sqlBinaryOpExpr = joinConditions(oneColumnRule, SQLBinaryOperator.BooleanOr);
                oneGroupRuleList.add(sqlBinaryOpExpr);
            }
            SQLBinaryOpExpr joinedOneGroupRule = joinConditions(oneGroupRuleList, SQLBinaryOperator.BooleanAnd);
            joinedOneGroupRule.setBracket(true);
            finalRuleList.add(joinedOneGroupRule);
        }
        if (finalRuleList != null && finalRuleList.size() > 0) {
            return joinConditions(finalRuleList, SQLBinaryOperator.BooleanOr);
        } else {
            return null;
        }
    }

    private SQLBinaryOpExpr joinConditions(Collection<SQLBinaryOpExpr> conditions, SQLBinaryOperator sqlBinaryOperator) {
        Iterator<SQLBinaryOpExpr> iter = conditions.iterator();
        SQLBinaryOpExpr ret;
        if (iter.hasNext()) {
            ret = new SQLBinaryOpExpr();
            SQLBinaryOpExpr next = iter.next();
            iter.remove();
            if (iter.hasNext()) {
                ret.setRight(next);
                ret.setOperator(sqlBinaryOperator);
                ret.setLeft(joinConditions(conditions, sqlBinaryOperator));
            } else {
                ret = next;
            }
            return ret;
        } else {
            throw new RuntimeException("conditions不可以传空集合过来");
        }
    }


    private void processStatement(SQLSelectStatement selectStatement) {
        MySqlSelectQueryBlock currentQuery = (MySqlSelectQueryBlock) selectStatement.getSelect().getQuery();

        //ODSS中原来这个位置有段代码实现给statement中每个没有别名的select的字段加个别名
        //mybatis的插件不需要人为给字段增加别名

        tableNameAliasMapping.set(new HashMap<String, String>());

        //这段收集所有的表的表名和别名
        SQLTableSource from = currentQuery.getFrom();
        getAndSetCoreTableAlias(from);

        //这段生成权限控制的条件
        List<Map<String, Map<String, List<SQLBinaryOpExpr>>>> firstStepResult = createRoleBaseConditions();
        SQLBinaryOpExpr permissionConditions = null;
        if (firstStepResult.size() > 0) {
            permissionConditions = createPermissionConditions(firstStepResult);
        }

        //合并权限条件与原来的条件
        SQLBinaryOpExpr finalWhere = null;
        SQLExpr where = currentQuery.getWhere();
        if (where != null && permissionConditions != null) {
            finalWhere = new SQLBinaryOpExpr();
            finalWhere.setLeft(permissionConditions);
            finalWhere.setRight(where);
            finalWhere.setOperator(SQLBinaryOperator.BooleanAnd);
        } else if (permissionConditions != null) {
            finalWhere = permissionConditions;
        }
        currentQuery.setWhere(finalWhere);


    }


}
