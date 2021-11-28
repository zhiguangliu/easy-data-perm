package cn.zhgliu.ezdp.permission.controller;


import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.permission.entity.DpPermissionItemMetadata;
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

    @Override
    @GetMapping("/list")
    public List<DpPermissionItemMetadata> find(DpPermissionItemMetadata param, Boolean isAsc, String... column) {
        if (param.getPermissionMetadataId() == null) {
            return new LinkedList<>();
        }
        List<DpPermissionItemMetadata> ret = super.find(param, true, "target_table_name", "field_name");
        return ret;
    }
}
