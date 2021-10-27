package cn.zhgliu.ezdp.permission.mapper;

import cn.zhgliu.ezdp.permission.entity.WithRoleDpPermissionItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomDpPermissionItemMapper {

    List<WithRoleDpPermissionItem> selectItemsWithRoleId(@Param("subsystem") String subsystem,
                                                         @Param("operationIdentifier") String operationIdentifier,
                                                         @Param("userId") String userId);

}
