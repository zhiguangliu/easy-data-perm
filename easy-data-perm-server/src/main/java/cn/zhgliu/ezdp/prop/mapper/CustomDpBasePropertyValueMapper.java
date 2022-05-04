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

@Select("SELECT\n" +
        "\tc.value_code\n" +
        "FROM\n" +
        "\tdp_role_user a\n" +
        "LEFT JOIN dp_role_property_relation b ON a.role_id = b.role_id\n" +
        "LEFT JOIN dp_base_property_value c ON b.property_value_id = c.id\n" +
        "WHERE\n" +
        "\ta.sub_system_code = #{subsystemCode}\n" +
        "AND a.user_id = #{userId}\n" +
        "AND c.property_code = #{propertyCode}")
    List<String> getProperties(@Param("subsystemCode") String subsystemCode,
                               @Param("userId") String userId,
                               @Param("propertyCode") String propertyCode);
}
