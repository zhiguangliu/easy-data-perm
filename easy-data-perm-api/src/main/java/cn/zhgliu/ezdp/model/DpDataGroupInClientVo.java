package cn.zhgliu.ezdp.model;

import java.io.Serializable;
import java.util.Date;

public class DpDataGroupInClientVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer templateId;

    private String subSystem;

    private String queryName;

    private String queryCode;

    private String groupName;

    private String groupSign;

    private String applyMethod;

    private String status;

    private String memo;

    private String isEffective;

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

    /**
     * 获取模板主键
     *
     * @return template_id - 模板主键
     */
    public Integer getTemplateId() {
        return templateId;
    }

    /**
     * 设置模板主键
     *
     * @param templateId 模板主键
     */
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
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
     * 获取权限组名称
     *
     * @return group_name - 权限组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置权限组名称
     *
     * @param groupName 权限组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取权限组标识
     *
     * @return group_sign - 权限组标识
     */
    public String getGroupSign() {
        return groupSign;
    }

    /**
     * 设置权限组标识
     *
     * @param groupSign 权限组标识
     */
    public void setGroupSign(String groupSign) {
        this.groupSign = groupSign;
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
     * 获取是否生效
     *
     * @return is_effective - 是否生效
     */
    public String getIsEffective() {
        return isEffective;
    }

    /**
     * 设置是否生效
     *
     * @param isEffective 是否生效
     */
    public void setIsEffective(String isEffective) {
        this.isEffective = isEffective;
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