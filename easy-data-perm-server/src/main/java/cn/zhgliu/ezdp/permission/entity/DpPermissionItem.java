package cn.zhgliu.ezdp.permission.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhgliu
 * @since 2021-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dp_permission_item")
public class DpPermissionItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer permissionId;

    private Integer metadataId;

    private String relation;

    private String valueType;

    private String fieldValue;

    private String applyMethod;

    private String targetTableName;

    private String fieldName;

    private String fieldType;

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
