package cn.zhgliu.ezdp.permission.controller;


import cn.zhgliu.ezdp.comm.CommonWebResult;
import cn.zhgliu.ezdp.permission.entity.DpPermissionItemMetadata;
import cn.zhgliu.ezdp.permission.service.IDpPermissionItemMetadataService;
import cn.zhgliu.ezdp.web.BatchData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@Controller
@RequestMapping({"/permission/dp-permission-item-metadata","rest/permission/dp-permission-item-metadata"})
public class DpPermissionItemMetadataController {

    @Resource
    IDpPermissionItemMetadataService iDpPermissionItemMetadataService;


    @GetMapping("/all")
    public List<DpPermissionItemMetadata> all() {
        List<DpPermissionItemMetadata> list = iDpPermissionItemMetadataService.list(null);
        return list;
    }

    @PostMapping("/data")
    @ResponseBody
    public CommonWebResult data(@RequestBody BatchData<DpPermissionItemMetadata> data) {
        if (data.getDel() != null && !data.getDel().isEmpty()) {
            data.getDel().stream().forEach(item -> {
                iDpPermissionItemMetadataService.remove(new QueryWrapper<>(item));
            });
        }
        if (data.getEdit() != null && !data.getEdit().isEmpty()) {
            data.getEdit().stream().forEach(item -> {
                iDpPermissionItemMetadataService.updateById(item);
            });
        }
        if (data.getAdd() != null && !data.getAdd().isEmpty()) {
            data.getAdd().stream().forEach(item -> {
                iDpPermissionItemMetadataService.save(item);
            });
        }

        return CommonWebResult.success();
    }

}
