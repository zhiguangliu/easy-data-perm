package cn.zhgliu.ezdp.permission.mapper;

import cn.zhgliu.ezdp.permission.entity.WithRoleDpPermissionItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomDpPermissionItemMapper {

    @Select("SELECT c.role_id,\n" +
            "       d.metadata_id,\n" +
            "       f.apply_method,\n" +
            "       g.target_table_name,\n" +
            "       g.field_name,\n" +
            "       g.field_type,\n" +
            "       e.relation,\n" +
            "       e.value_type,\n" +
            "       e.field_value\n" +
            "FROM dp_role_permission_relation c\n" +
            "         LEFT JOIN dp_permission d ON c.permission_id = d.id\n" +
            "         LEFT JOIN dp_permission_metadata f ON d.metadata_id = f.id\n" +
            "         LEFT JOIN dp_permission_item e ON e.permission_id = d.id\n" +
            "         LEFT JOIN dp_permission_item_metadata g on e.item_metadata_id = g.id\n" +
            "WHERE f.operation_identifier = #{operationIdentifier}\n" +
            "  and c.role_id IN (\n" +
            "    SELECT a.role_id\n" +
            "    FROM dp_role_user a\n" +
            "    WHERE a.sub_system_code = #{subsystem}\n" +
            "      AND a.user_id = #{userId}\n" +
            ")")
    List<WithRoleDpPermissionItem> selectItemsWithRoleId(@Param("subsystem") String subsystem,
                                                         @Param("operationIdentifier") String operationIdentifier,
                                                         @Param("userId") String userId);

}
