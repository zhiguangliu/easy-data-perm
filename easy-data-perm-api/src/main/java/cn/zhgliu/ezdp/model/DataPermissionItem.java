package cn.zhgliu.ezdp.model;

import java.io.Serializable;

/**
 * @author zhgliu
 */
public class DataPermissionItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer metadataId;

    private String applyMethod;

    private String targetTableName;

    private String fieldName;

    private String fieldType;

    private String relation;

    private String valueType;

    private String fieldValue;

    public Integer getMetadataId() {
        return metadataId;
    }

    public DataPermissionItem setMetadataId(Integer metadataId) {
        this.metadataId = metadataId;
        return this;
    }

    public String getApplyMethod() {
        return applyMethod;
    }

    public DataPermissionItem setApplyMethod(String applyMethod) {
        this.applyMethod = applyMethod;
        return this;
    }

    public String getTargetTableName() {
        return targetTableName;
    }

    public DataPermissionItem setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
        return this;
    }

    public String getFieldName() {
        return fieldName;
    }

    public DataPermissionItem setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public String getFieldType() {
        return fieldType;
    }

    public DataPermissionItem setFieldType(String fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    public String getRelation() {
        return relation;
    }

    public DataPermissionItem setRelation(String relation) {
        this.relation = relation;
        return this;
    }

    public String getValueType() {
        return valueType;
    }

    public DataPermissionItem setValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public DataPermissionItem setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
        return this;
    }
}