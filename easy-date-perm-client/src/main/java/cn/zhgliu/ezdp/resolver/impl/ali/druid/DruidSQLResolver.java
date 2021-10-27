package cn.zhgliu.ezdp.resolver.impl.ali.druid;


import cn.zhgliu.ezdp.model.DpDataGroupListInClientVo;
import cn.zhgliu.ezdp.resolver.DataPermSqlResolver;
import cn.zhgliu.ezdp.resolver.impl.ali.druid.visitor.PermissionProcessMysqlVisitor;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.util.JdbcConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author zhgliu
 */
public class DruidSQLResolver implements DataPermSqlResolver {

    Logger log = LoggerFactory.getLogger(DruidSQLResolver.class);

    @Override
    public String resolveSqlWithGroupRule(String sql, List<List<DpDataGroupListInClientVo>> rules) {
        if (rules == null || rules.size() == 0) {
            return sql;
        }
        log.debug("处理之前的SQL为:{}",sql);
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL);
        SQLStatement currentStatement = stmtList.get(0);

        PermissionProcessMysqlVisitor permissionProcessMysqlVisitor =  PermissionProcessMysqlVisitor.getInstanceWithGroupRules(rules);
        currentStatement.accept(permissionProcessMysqlVisitor);
        String newSql = SQLUtils.toSQLString(currentStatement, JdbcConstants.MYSQL);

        log.debug("处理之后的SQL为:{}",newSql);
        return newSql;
    }

}
