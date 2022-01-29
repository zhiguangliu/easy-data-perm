package cn.zhgliu.ezdp.role.controller;


import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.role.entity.DpRoleUser;
import cn.zhgliu.ezdp.role.entity.DpRoleUserVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
@Controller
@RequestMapping({"/role/dp-role-user","/rest/role/dp-role-user"})
public class DpRoleUserController extends CommonController<DpRoleUser> {

    public DpRoleUserController(IService<DpRoleUser> iService) {
        super(iService);
    }

    @Override
    public List<DpRoleUser> find(DpRoleUser param, Boolean isAsc, String... column) {
        if (param.getRoleId() == null) {
            return new LinkedList<>();
        }
        List<DpRoleUser> dpRoleUsers = super.find(param, isAsc, column);
        DpRoleUserVo item;

//        dpRoleUsers.stream().map(DpRoleUser::get)
        return dpRoleUsers;
    }
}
