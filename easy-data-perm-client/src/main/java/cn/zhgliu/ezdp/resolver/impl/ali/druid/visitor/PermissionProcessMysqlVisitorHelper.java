package cn.zhgliu.ezdp.resolver.impl.ali.druid.visitor;

import cn.hutool.core.bean.BeanUtil;
import cn.zhgliu.ezdp.consts.FieldType;
import cn.zhgliu.ezdp.consts.Relation;
import cn.zhgliu.ezdp.model.DataPermissionItem;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLCharExpr;
import com.alibaba.druid.sql.ast.expr.SQLNumberExpr;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class PermissionProcessMysqlVisitorHelper {


    static SQLBinaryOperator getOperator(String ecDpPermOp) {
        if (Relation.valueOf(ecDpPermOp) == Relation.EQUAL) {
            return SQLBinaryOperator.Equality;
        }
        if (Relation.valueOf(ecDpPermOp) == Relation.NOT_EQUAL) {
            return SQLBinaryOperator.NotEqual;
        }
        throw new RuntimeException("权限控制条件中，关系字段值有误。无法生成条件。");
    }

    static SQLExpr getValueExpr(DataPermissionItem ecCondition) {
        if (FieldType.valueOf(ecCondition.getFieldType()) == FieldType.STRING) {
            return new SQLCharExpr(ecCondition.getFieldValue());
        }
        if (FieldType.valueOf(ecCondition.getFieldType()) == FieldType.NUMBER) {
            Number value = null;
            try {
                value = Integer.parseInt(ecCondition.getFieldValue());
                return new SQLNumberExpr(value);
            } catch (NumberFormatException e) {
            }
            try {
                value = Double.parseDouble(ecCondition.getFieldValue());
                return new SQLNumberExpr(value);
            } catch (NumberFormatException e) {
            }
            try {
                value = Float.parseFloat(ecCondition.getFieldValue());
                return new SQLNumberExpr(value);
            } catch (NumberFormatException e) {
            }
        }

        throw new RuntimeException("权限控制条件中，字段类型与字段值不符合。无法生成条件。");
    }

    public static final String COMER = ",";

    static List<DataPermissionItem> splitRule(DataPermissionItem rule) {
        List<DataPermissionItem> ret = new LinkedList<>();
        String fieldValue = rule.getFieldValue();
        if (!fieldValue.contains(COMER)) {
            ret.add(rule);
        } else {
            Arrays.stream(fieldValue.split(COMER)).forEach(value -> {
                DataPermissionItem newItem = new DataPermissionItem();
                BeanUtil.copyProperties(rule, newItem);
                newItem.setFieldValue(value);
                ret.add(newItem);
            });
        }
        return ret;
    }
}
