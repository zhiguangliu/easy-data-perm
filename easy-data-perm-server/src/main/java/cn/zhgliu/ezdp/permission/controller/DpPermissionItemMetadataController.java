package cn.zhgliu.ezdp.permission.controller;


import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.permission.entity.DpPermissionItemMetadata;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@RestController
@RequestMapping({"/permission/dp-permission-item-metadata","rest/permission/dp-permission-item-metadata"})
public class DpPermissionItemMetadataController extends CommonController<DpPermissionItemMetadata> {

    public DpPermissionItemMetadataController(IService<DpPermissionItemMetadata> iService) {
        super(iService);
    }

    @GetMapping("/list")
    public List<DpPermissionItemMetadata> find(DpPermissionItemMetadata param) {
        if (param.getPermissionMetadataId() == null) {
            return new LinkedList<>();
        }
        List<DpPermissionItemMetadata> ret = iService.list(new QueryWrapper<>(param).orderByAsc("target_table_name","field_name"));
        return ret;
    }
}
