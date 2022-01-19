package cn.zhgliu.ezdp.permission.service.impl;

import cn.zhgliu.ezdp.consts.Relation;
import cn.zhgliu.ezdp.permission.entity.DpPermission;
import cn.zhgliu.ezdp.permission.entity.DpPermissionItem;
import cn.zhgliu.ezdp.permission.entity.DpPermissionItemMetadata;
import cn.zhgliu.ezdp.permission.entity.DpPermissionMetadata;
import cn.zhgliu.ezdp.permission.mapper.DpPermissionMapper;
import cn.zhgliu.ezdp.permission.service.IDpPermissionItemMetadataService;
import cn.zhgliu.ezdp.permission.service.IDpPermissionItemService;
import cn.zhgliu.ezdp.permission.service.IDpPermissionMetadataService;
import cn.zhgliu.ezdp.permission.service.IDpPermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@Service
public class DpPermissionServiceImpl extends ServiceImpl<DpPermissionMapper, DpPermission> implements IDpPermissionService {

//    LoggerFactory.getlogger(DpPermissionServiceImpl.class);


    @Resource
    IDpPermissionItemMetadataService itemMetadataService;

    @Resource
    IDpPermissionMetadataService metadataService;

    @Resource
    IDpPermissionItemService itemService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DpPermission addPermission(Integer metadataId) {

        DpPermissionMetadata currentMeta = metadataService.getById(metadataId);

        DpPermission toInsertPermission = new DpPermission();
        toInsertPermission.setMetadataId(metadataId);
        toInsertPermission.setStatus("OK");
        toInsertPermission.setPermissionName(currentMeta.getOperationName()+"-"+ LocalDateTime.now().toString());
        this.baseMapper.insert(toInsertPermission);

        DpPermissionItemMetadata param = new DpPermissionItemMetadata();
        param.setPermissionMetadataId(metadataId);
        List<DpPermissionItemMetadata> list = itemMetadataService.list(new QueryWrapper<>(param));

        list.stream().forEach(itemMetaData->{
            DpPermissionItem item = new DpPermissionItem();
            BeanUtils.copyProperties(itemMetaData, item);
            item.setPermissionId(toInsertPermission.getId());
            item.setItemMetadataId(itemMetaData.getId());
            item.setRelation(Relation.NOT_CONTROL.toString());
            itemService.save(item);
        });

        System.out.println("===========================");
        System.out.println(toInsertPermission.getId());
        System.out.println("===========================");

        return null;
    }
}
