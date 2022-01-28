package cn.zhgliu.ezdp.prop.controller;


import cn.zhgliu.ezdp.consts.ValueType;
import cn.zhgliu.ezdp.prop.entity.DpBasePropertyDefine;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping({"/prop/dp-base-property-define","/rest/prop/dp-base-property-define"})
public class DpBasePropertyDefineController {


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

}
