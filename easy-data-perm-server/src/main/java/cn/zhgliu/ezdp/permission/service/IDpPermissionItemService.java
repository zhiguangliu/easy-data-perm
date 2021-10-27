package cn.zhgliu.ezdp.permission.service;

import cn.zhgliu.ezdp.permission.entity.DpPermissionItem;
import cn.zhgliu.ezdp.permission.entity.WithRoleDpPermissionItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
public interface IDpPermissionItemService extends IService<DpPermissionItem> {

    List<WithRoleDpPermissionItem> findPermissionItems(String subsystem, String operationIdentifier, String userId);
}
