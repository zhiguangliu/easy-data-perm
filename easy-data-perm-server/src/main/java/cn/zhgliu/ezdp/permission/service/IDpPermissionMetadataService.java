package cn.zhgliu.ezdp.permission.service;

import cn.zhgliu.ezdp.model.DataPermissionBaseInfo;
import cn.zhgliu.ezdp.permission.entity.DpPermissionMetadata;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
public interface IDpPermissionMetadataService extends IService<DpPermissionMetadata> {

    DataPermissionBaseInfo matchingMode(DpPermissionMetadata dpPermissionMetadata);

}
