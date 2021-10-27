package cn.zhgliu.ezdp.permission.service.impl;

import cn.zhgliu.ezdp.permission.entity.DpPermissionItem;
import cn.zhgliu.ezdp.permission.entity.WithRoleDpPermissionItem;
import cn.zhgliu.ezdp.permission.mapper.CustomDpPermissionItemMapper;
import cn.zhgliu.ezdp.permission.mapper.DpPermissionItemMapper;
import cn.zhgliu.ezdp.permission.service.IDpPermissionItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@Service
public class DpPermissionItemServiceImpl extends ServiceImpl<DpPermissionItemMapper, DpPermissionItem> implements IDpPermissionItemService {

    @Resource
    CustomDpPermissionItemMapper customDpPermissionItemMapper;

    @Override
    public List<WithRoleDpPermissionItem> findPermissionItems(String subsystem, String operationIdentifier, String userId) {
        List<WithRoleDpPermissionItem> ret = customDpPermissionItemMapper.selectItemsWithRoleId(subsystem, operationIdentifier, userId);
        return ret != null ? ret : new LinkedList<>();
    }

}
