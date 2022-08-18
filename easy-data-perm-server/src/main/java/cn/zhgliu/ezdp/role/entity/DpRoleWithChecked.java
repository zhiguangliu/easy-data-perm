package cn.zhgliu.ezdp.role.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@Data
public class DpRoleWithChecked implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userId;

    private Integer roleId;

    private String roleName;

    private Integer checked;

    private String subSystemCode;


}
