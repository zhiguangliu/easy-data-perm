package cn.zhgliu.ezdp.role.controller;


import cn.zhgliu.ezdp.comm.CommonWebResult;
import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.role.entity.DpRolePropertyRelation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@Controller
@RequestMapping({"/role/dp-role-property-relation","/rest/role/dp-role-property-relation"})
public class DpRolePropertyRelationController extends CommonController<DpRolePropertyRelation> {


    public DpRolePropertyRelationController(IService<DpRolePropertyRelation> iService) {
        super(iService);
    }

    @PostMapping("/relate")
    public CommonWebResult relate(@RequestBody DpRolePropertyRelation dpRolePropertyRelation) {
        iService.save(dpRolePropertyRelation);
        return new CommonWebResult();
    }


    @PostMapping("/release")
    public CommonWebResult release(@RequestBody DpRolePropertyRelation dpRolePropertyRelation) {
        iService.remove(new QueryWrapper<>(dpRolePropertyRelation));
        return new CommonWebResult();

    }
}
