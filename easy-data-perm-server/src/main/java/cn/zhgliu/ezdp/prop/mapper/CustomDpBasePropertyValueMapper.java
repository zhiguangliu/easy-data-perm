package cn.zhgliu.ezdp.prop.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
public interface CustomDpBasePropertyValueMapper {

    @Select("SELECT " +
            " c.value_code " +
            " FROM dp_role_user a " +
            "LEFT JOIN dp_role_property_relation b ON a.role_id = b.role_id " +
            "LEFT JOIN dp_base_property_value c ON b.property_value_id = c.id " +
            "WHERE " +
            "    a.sub_system_code = #{subsystemCode} " +
            "    AND a.user_id = #{userId} " +
            "    AND a.role_id = #{roleId} " +
            "    AND c.property_code = #{propertyCode} ")
    List<String> getProperties(@Param("subsystemCode") String subsystemCode,
                               @Param("userId") String userId,
                               @Param("roleId") Integer roleId,
                               @Param("propertyCode") String propertyCode);
}
