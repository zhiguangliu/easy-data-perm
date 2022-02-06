package cn.zhgliu.ezdp.prop.controller;


import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.consts.ValueType;
import cn.zhgliu.ezdp.easyui.Pagination;
import cn.zhgliu.ezdp.prop.entity.DpBasePropertyDefine;
import cn.zhgliu.ezdp.role.entity.DpRole;
import cn.zhgliu.ezdp.role.service.IDpRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@Controller
@RequestMapping({"/prop/dp-base-property-define", "/rest/prop/dp-base-property-define"})
public class DpBasePropertyDefineController extends CommonController<DpBasePropertyDefine> {


    public DpBasePropertyDefineController(IService<DpBasePropertyDefine> iService) {
        super(iService);
    }

    @RequestMapping("/getPropNameList")
    @ResponseBody
    public List<DpBasePropertyDefine> getPropNameList(String subsystemCode, String valueType) {
        if (ValueType.PROPERTY.toString().equals(valueType)) {
            List<DpBasePropertyDefine> objects = new LinkedList<>();
            DpBasePropertyDefine v = new DpBasePropertyDefine();
            v.setPropertyName("aaa");
            v.setPropertyCode("aaa");
            objects.add(v);
            v = new DpBasePropertyDefine();
            v.setPropertyName("bbb");
            v.setPropertyCode("bbb");
            objects.add(v);
            v = new DpBasePropertyDefine();
            v.setPropertyName("ccc");
            v.setPropertyCode("ccc");
            objects.add(v);


            return objects;
        } else {
            return new LinkedList();
        }
    }

    @Override
    public Pagination<DpBasePropertyDefine> page(Integer page, Integer rows, DpBasePropertyDefine param, Boolean isAsc, String... column) {
        if (StringUtils.isEmpty(param.getSubSystemCode())) {
            return new Pagination<>();
        } else {
            return super.page(page, rows, param, isAsc, column);
        }
    }

    @Resource
    IDpRoleService iDpRoleService;

    @RequestMapping("/rolePropertyDefine")
    @ResponseBody
    public List<DpBasePropertyDefine> getRolePropertyDefine(Integer roleId) {
        DpRole role = iDpRoleService.getById(roleId);
        String subSystemCode = role.getSubSystemCode();
        DpBasePropertyDefine param = new DpBasePropertyDefine();
        param.setSubSystemCode(subSystemCode);
        List<DpBasePropertyDefine> ret = this.iService.list(new QueryWrapper<>(param));
        return ret;
    }

}
