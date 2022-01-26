package cn.zhgliu.ezdp.permission.controller;


import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.permission.entity.DpPermissionItem;
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
@RequestMapping({"/permission/dp-permission-item","/rest/permission/dp-permission-item"})
public class DpPermissionItemController extends CommonController<DpPermissionItem> {

    public DpPermissionItemController(IService<DpPermissionItem> iService) {
        super(iService);
    }

    @Override
    public List<DpPermissionItem> find(DpPermissionItem param, Boolean isAsc, String... column) {
        if (param.getPermissionId() == null) {
            return new LinkedList<>();
        }
        return super.find(param, isAsc, column);
    }

    



}
