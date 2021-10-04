package cn.zhgliu.ezdp.role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DpRolePermissionRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer roleId;

    private Integer permissionId;

    private String memo;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人主键
     */
    private String createUserId;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人主键
     */
    private String updateUserId;

    /**
     * 修改人姓名
     */
    private String updateUserName;


}
