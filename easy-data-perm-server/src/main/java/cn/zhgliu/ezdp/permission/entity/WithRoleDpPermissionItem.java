package cn.zhgliu.ezdp.permission.entity;

import lombok.Data;

/**
 * @author zhgliu
 */
@Data
public class WithRoleDpPermissionItem extends DpPermissionItem {
    private Integer roleId;
    private String fieldName;
    private String fieldType;
    private String applyMethod;
    private String targetTableName;
    private Integer metadataId;


}
