package cn.zhgliu.ezdp.permission.controller;


import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.permission.entity.DpPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@Controller
@RequestMapping("/permission/dp-permission")
public class DpPermissionController extends CommonController<DpPermission> {


    public DpPermissionController(IService<DpPermission> iService) {
        super(iService);
    }
}
