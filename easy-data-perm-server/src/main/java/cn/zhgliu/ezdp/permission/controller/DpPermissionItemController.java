package cn.zhgliu.ezdp.permission.controller;


import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.permission.entity.DpPermissionItem;
import cn.zhgliu.ezdp.permission.entity.DpPermissionItemMetadata;
import cn.zhgliu.ezdp.permission.entity.DpPermissionItemVo;
import cn.zhgliu.ezdp.permission.service.IDpPermissionItemMetadataService;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@Controller
@RequestMapping({"/permission/dp-permission-item", "/rest/permission/dp-permission-item"})
public class DpPermissionItemController extends CommonController<DpPermissionItem> {

    @Resource
    IDpPermissionItemMetadataService iDpPermissionItemMetadataService;

    public DpPermissionItemController(IService<DpPermissionItem> iService) {
        super(iService);
    }

    @Override
    public List<DpPermissionItem> find(DpPermissionItem param, Boolean isAsc, String... column) {
        if (param.getPermissionId() == null) {
            return new LinkedList<>();
        }
        List<DpPermissionItem> dpPermissionItems = super.find(param, isAsc, column);

        List<Integer> itemMetadataIdList = dpPermissionItems.stream().map(DpPermissionItem::getItemMetadataId).collect(Collectors.toList());
        Collection<DpPermissionItemMetadata> dpPermissionItemMetadata = iDpPermissionItemMetadataService.listByIds(itemMetadataIdList);

        Map<Integer, String> idNameMap = dpPermissionItemMetadata.stream().collect(Collectors.toMap(DpPermissionItemMetadata::getId, DpPermissionItemMetadata::getFieldName));

        List<DpPermissionItem> collect = dpPermissionItems.stream()
                .map(item -> {
                    DpPermissionItemVo ret = new DpPermissionItemVo();
                    BeanUtils.copyProperties(item, ret);
                    ret.setItemName(idNameMap.get(item.getItemMetadataId()));
                    return ret;
                })
                .collect(Collectors.toList());

        return collect;
    }


}
