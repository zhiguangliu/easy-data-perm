package cn.zhgliu.ezdp.prop.controller;


import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.prop.entity.DpBasePropertyValue;
import cn.zhgliu.ezdp.prop.entity.DpBasePropertyValueCheckedVo;
import cn.zhgliu.ezdp.role.entity.DpRole;
import cn.zhgliu.ezdp.role.entity.DpRolePropertyRelation;
import cn.zhgliu.ezdp.role.service.IDpRolePropertyRelationService;
import cn.zhgliu.ezdp.role.service.IDpRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@Controller
@RequestMapping({"/prop/dp-base-property-value", "rest/prop/dp-base-property-value"})
public class DpBasePropertyValueController extends CommonController<DpBasePropertyValue> {

    public DpBasePropertyValueController(IService<DpBasePropertyValue> iService) {
        super(iService);
    }

    @Override
    public List<DpBasePropertyValue> find(DpBasePropertyValue param, Boolean isAsc, String... column) {
        if (StringUtils.isEmpty(param.getPropertyCode())) {
            return new LinkedList<>();
        } else {
            return super.find(param, isAsc, column);
        }
    }


    @Resource
    IDpRoleService iDpRoleService;

    @Resource
    IDpRolePropertyRelationService iDpRolePropertyRelationService;

    @RequestMapping("/rolePropertyList")
    public List<DpBasePropertyValue> find(Integer roleId, String propertyCode) {
        if (roleId == null) {
            return new LinkedList<>();
        }
        DpRole role = iDpRoleService.getById(roleId);
        String subSystemCode = role.getSubSystemCode();

        DpBasePropertyValue param = new DpBasePropertyValue();
        param.setPropertyCode(propertyCode);
        param.setSubSystemCode(subSystemCode);
        List<DpBasePropertyValue> list = this.iService.list(new QueryWrapper<>(param));

        DpRolePropertyRelation rParam = new DpRolePropertyRelation();
        rParam.setRoleId(roleId);
        rParam.setSubSystemCode(subSystemCode);
        List<DpRolePropertyRelation> relations = iDpRolePropertyRelationService.list(new QueryWrapper<>(rParam));
        Set<Integer> relatedId = relations.stream().map(DpRolePropertyRelation::getPropertyValueId).collect(Collectors.toSet());


        List<DpBasePropertyValue> ret = list.stream().map(item -> {
            DpBasePropertyValueCheckedVo newItem = new DpBasePropertyValueCheckedVo();
            BeanUtils.copyProperties(item, newItem);
            newItem.setChecked(relatedId.contains(item.getId())?1:0);
            return newItem;
        }).collect(Collectors.toList());


        return ret;
    }
}
