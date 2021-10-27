package cn.zhgliu.ezdp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhgliu
 */
public class DpDataGroupListInClientVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

//    private Integer dataGroupId;
    private Integer metadataId;

//    private Integer templateId;

    private Integer templateListId;

    private String subSystem;

    private String queryName;

    private String queryCode;

    private String applyMethod;

    private String targetTableName;

    private String filedName;

    private String filedType;

    private String relation;

    private String valueType;

    private String filedValue;

    private String status;

    private String memo;

    private Date createTime;

    private Integer createUserId;

    private String createUserName;

    private Date updateTime;

    private Integer updateUserId;

    private String updateUserName;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMetadataId() {
        return metadataId;
    }

    public DpDataGroupListInClientVo setMetadataId(Integer metadataId) {
        this.metadataId = metadataId;
        return this;
    }

    /**
     * 获取权限组主键
     *
     * @return data_group_id - 权限组主键
     */
//    public Integer getDataGroupId() {
//        return dataGroupId;
//    }

    /**
     * 设置权限组主键
     *
     * @param dataGroupId 权限组主键
     */
//    public void setDataGroupId(Integer dataGroupId) {
//        this.dataGroupId = dataGroupId;
//    }

    /**
     * 获取模板主键
     *
     * @return template_id - 模板主键
     */
//    public Integer getTemplateId() {
//        return templateId;
//    }

    /**
     * 设置模板主键
     *
     * @param templateId 模板主键
     */
//    public void setTemplateId(Integer templateId) {
//        this.templateId = templateId;
//    }

    /**
     * 获取模板列表主键
     *
     * @return template_list_id - 模板列表主键
     */
    public Integer getTemplateListId() {
        return templateListId;
    }

    /**
     * 设置模板列表主键
     *
     * @param templateListId 模板列表主键
     */
    public void setTemplateListId(Integer templateListId) {
        this.templateListId = templateListId;
    }

    /**
     * 获取所属子系统
     *
     * @return sub_system - 所属子系统
     */
    public String getSubSystem() {
        return subSystem;
    }

    /**
     * 设置所属子系统
     *
     * @param subSystem 所属子系统
     */
    public void setSubSystem(String subSystem) {
        this.subSystem = subSystem;
    }

    /**
     * 获取查询名称
     *
     * @return query_name - 查询名称
     */
    public String getQueryName() {
        return queryName;
    }

    /**
     * 设置查询名称
     *
     * @param queryName 查询名称
     */
    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    /**
     * 获取查询标识
     *
     * @return query_code - 查询标识
     */
    public String getQueryCode() {
        return queryCode;
    }

    /**
     * 设置查询标识
     *
     * @param queryCode 查询标识
     */
    public void setQueryCode(String queryCode) {
        this.queryCode = queryCode;
    }

    /**
     * 获取权限应用方式
            WRAPPER=包装式限制
            EMBBED=嵌入式限制
     *
     * @return apply_method - 权限应用方式
            WRAPPER=包装式限制
            EMBBED=嵌入式限制
     */
    public String getApplyMethod() {
        return applyMethod;
    }

    /**
     * 设置权限应用方式
            WRAPPER=包装式限制
            EMBBED=嵌入式限制
     *
     * @param applyMethod 权限应用方式
            WRAPPER=包装式限制
            EMBBED=嵌入式限制
     */
    public void setApplyMethod(String applyMethod) {
        this.applyMethod = applyMethod;
    }

    /**
     * @return target_table_name
     */
    public String getTargetTableName() {
        return targetTableName;
    }

    /**
     * @param targetTableName
     */
    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    /**
     * 获取字段名
     *
     * @return filed_name - 字段名
     */
    public String getFiledName() {
        return filedName;
    }

    /**
     * 设置字段名
     *
     * @param filedName 字段名
     */
    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    /**
     * 获取字段类型
     *
     * @return filed_type - 字段类型
     */
    public String getFiledType() {
        return filedType;
    }

    /**
     * 设置字段类型
     *
     * @param filedType 字段类型
     */
    public void setFiledType(String filedType) {
        this.filedType = filedType;
    }

    /**
     * 获取关系
     *
     * @return relation - 关系
     */
    public String getRelation() {
        return relation;
    }

    /**
     * 设置关系
     *
     * @param relation 关系
     */
    public void setRelation(String relation) {
        this.relation = relation;
    }

    /**
     * 获取取值类型
     *
     * @return value_type - 取值类型
     */
    public String getValueType() {
        return valueType;
    }

    /**
     * 设置取值类型
     *
     * @param valueType 取值类型
     */
    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    /**
     * 获取字段值
     *
     * @return filed_value - 字段值
     */
    public String getFiledValue() {
        return filedValue;
    }

    /**
     * 设置字段值
     *
     * @param filedValue 字段值
     */
    public void setFiledValue(String filedValue) {
        this.filedValue = filedValue;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取备注
     *
     * @return memo - 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置备注
     *
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人主键
     *
     * @return create_user_id - 创建人主键
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建人主键
     *
     * @param createUserId 创建人主键
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取创建人姓名
     *
     * @return create_user_name - 创建人姓名
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * 设置创建人姓名
     *
     * @param createUserName 创建人姓名
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取修改人主键
     *
     * @return update_user_id - 修改人主键
     */
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 设置修改人主键
     *
     * @param updateUserId 修改人主键
     */
    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取修改人姓名
     *
     * @return update_user_name - 修改人姓名
     */
    public String getUpdateUserName() {
        return updateUserName;
    }

    /**
     * 设置修改人姓名
     *
     * @param updateUserName 修改人姓名
     */
    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}