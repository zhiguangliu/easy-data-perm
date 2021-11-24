package cn.zhgliu.ezdp.subsystem.controller;


import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.subsystem.entity.DpSubSystem;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@RestController
@RequestMapping({"/DpSubSystem", "/rest/DpSubSystem"})
@Slf4j
public class DpSubSystemController extends CommonController<DpSubSystem> {

    public DpSubSystemController(IService<DpSubSystem> iService) {
        super(iService);
    }
}
