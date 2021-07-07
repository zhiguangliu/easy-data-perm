package cn.zhgliu.ezdp.model;

import cn.zhgliu.ezdp.consts.FieldType;
import cn.zhgliu.ezdp.consts.Relation;
import cn.zhgliu.ezdp.consts.ValueType;

public class DataPermissionItem {
    private String targetTableName;
    private String fieldName;
    private FieldType fieldType;
    private Relation relation;
    private String fieldValue;
    private ValueType valueType;

}
