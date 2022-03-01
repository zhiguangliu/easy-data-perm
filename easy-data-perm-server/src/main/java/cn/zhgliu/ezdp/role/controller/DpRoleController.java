package cn.zhgliu.ezdp.role.controller;


import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.web.Pagination;
import cn.zhgliu.ezdp.role.entity.DpRole;
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
@RequestMapping({"/role/dp-role","/rest/role/dp-role"})
public class DpRoleController extends CommonController<DpRole> {

    public DpRoleController(IService<DpRole> iService) {
        super(iService);
    }

    @Override
    public Pagination<DpRole> page(Integer page, Integer rows, DpRole param, Boolean isAsc, String... column) {
        if (param.getSubSystemCode() == null) {
            return new Pagination<>();
        }
        return super.page(page, rows, param, isAsc, column);
    }
}
