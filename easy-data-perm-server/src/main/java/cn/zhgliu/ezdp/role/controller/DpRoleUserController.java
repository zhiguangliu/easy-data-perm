package cn.zhgliu.ezdp.role.controller;


import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.role.entity.DpRoleUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhgliu
 * @since 2022-02-25
 */
@Controller
@RequestMapping("/role/dp-role-user")
public class DpRoleUserController extends CommonController<DpRoleUser> {

    public DpRoleUserController(IService<DpRoleUser> iService) {
        super(iService);
    }




}
