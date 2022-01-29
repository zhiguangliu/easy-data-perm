package cn.zhgliu.ezdp.role.entity;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
public class DpRolePermissionRelationVo  extends DpRolePermissionRelation {

    private static final long serialVersionUID = 1L;


    private String operationName;

    private String permissionName;

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
