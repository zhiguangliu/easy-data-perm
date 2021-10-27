package cn.zhgliu.ezdp.resolver.impl.ali.druid.visitor;

import cn.zhgliu.ezdp.consts.FieldType;
import cn.zhgliu.ezdp.consts.Relation;
import cn.zhgliu.ezdp.model.DpDataGroupListInClientVo;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLCharExpr;
import com.alibaba.druid.sql.ast.expr.SQLNumberExpr;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

class PermissionProcessMysqlVisitorHelper {


    static SQLBinaryOperator getOperator(String ecDpPermOp) {
        if (Relation.valueOf(ecDpPermOp)==Relation.EQUAL) {
            return SQLBinaryOperator.Equality;
        }
        if (Relation.valueOf(ecDpPermOp)==Relation.NOT_EQUAL) {
            return SQLBinaryOperator.NotEqual;
        }
        throw new RuntimeException("权限控制条件中，关系字段值有误。无法生成条件。");
    }

    static SQLExpr getValueExpr(DpDataGroupListInClientVo ecCondition) {
        if (FieldType.valueOf(ecCondition.getFiledType())==FieldType.STRING) {
            return new SQLCharExpr(ecCondition.getFiledValue());
        }
        if (FieldType.valueOf(ecCondition.getFiledType())==FieldType.NUMBER) {
            Number value = null;
            try {
                value = Integer.parseInt(ecCondition.getFiledValue());
                return new SQLNumberExpr(value);
            } catch (NumberFormatException e) {
            }
            try {
                value = Double.parseDouble(ecCondition.getFiledValue());
                return new SQLNumberExpr(value);
            } catch (NumberFormatException e) {
            }
            try {
                value = Float.parseFloat(ecCondition.getFiledValue());
                return new SQLNumberExpr(value);
            } catch (NumberFormatException e) {
            }
        }

        throw new RuntimeException("权限控制条件中，字段类型与字段值不符合。无法生成条件。");
    }

    static List<DpDataGroupListInClientVo> processRule(DpDataGroupListInClientVo rule) {
        List<DpDataGroupListInClientVo> ret = new LinkedList<>();
        String fieldValue = rule.getFiledValue();
        fieldValue = StringUtils.replace(fieldValue, "，", ",");
        fieldValue = RegExUtils.removePattern(fieldValue, "\\s");
        if (!fieldValue.contains(",")) {
            ret.add(rule);
        } else {
            String[] values = fieldValue.split(",");
            for (String value : values) {
                DpDataGroupListInClientVo oneRule = new DpDataGroupListInClientVo();
                oneRule.setApplyMethod(rule.getApplyMethod());
                oneRule.setTargetTableName(rule.getTargetTableName());
                oneRule.setFiledName(rule.getFiledName());
                oneRule.setFiledType(rule.getFiledType());
                oneRule.setRelation(rule.getRelation());
                oneRule.setValueType(rule.getValueType());
                oneRule.setFiledValue(value);
                ret.add(oneRule);
            }
        }
        return ret;
    }
}
