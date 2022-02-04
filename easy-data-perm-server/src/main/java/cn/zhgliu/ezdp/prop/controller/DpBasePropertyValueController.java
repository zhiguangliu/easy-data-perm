package cn.zhgliu.ezdp.prop.controller;


import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.prop.entity.DpBasePropertyValue;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

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
}
