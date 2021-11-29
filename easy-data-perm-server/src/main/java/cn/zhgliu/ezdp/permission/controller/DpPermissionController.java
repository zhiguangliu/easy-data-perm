package cn.zhgliu.ezdp.permission.controller;


import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.permission.entity.DpPermission;
import cn.zhgliu.ezdp.permission.service.IDpPermissionService;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@Controller
@RequestMapping({"/permission/dp-permission","/rest/permission/dp-permission"})
public class DpPermissionController extends CommonController<DpPermission> {

    @Resource
    IDpPermissionService iDpPermissionService;

    public DpPermissionController(IService<DpPermission> iService) {
        super(iService);
    }


    @PostMapping("/permission")
    public DpPermission add(@RequestBody DpPermission dpPermission) {

        DpPermission ret = iDpPermissionService.addPermission(dpPermission);
        return ret;
    }

}
