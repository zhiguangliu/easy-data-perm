package cn.zhgliu.ezdp.subsystem.controller;


import cn.zhgliu.ezdp.comm.CommonWebResult;
import cn.zhgliu.ezdp.subsystem.entity.DpSubSystem;
import cn.zhgliu.ezdp.subsystem.service.IDpSubSystemService;
import cn.zhgliu.layui.model.LayuiPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@RestController
@RequestMapping({"/DpSubSystem","/rest/DpSubSystem"})
public class DpSubSystemController {

    @Autowired
    IDpSubSystemService iDpSubSystemService;


    public DpSubSystemController() {
        System.out.println("----------================DpSubSystemController");
    }



    @RequestMapping(value = "/findByPage",method = RequestMethod.GET)
    public LayuiPage findByPage(Integer page, Integer limit, DpSubSystem dpSubSystem) {
        System.out.println("VIEW======================");
        Page pageparam = new Page(page, limit);
        QueryWrapper<DpSubSystem> wrapper = new QueryWrapper<>(dpSubSystem);
//        wrapper.excludeColumns()
        IPage page1 = iDpSubSystemService.page(pageparam, wrapper);
        List records = page1.getRecords();
        return new LayuiPage(0,120,records,"hello world!");
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello world!";
    }


    public CommonWebResult save(DpSubSystem dpSubSystem) {
        iDpSubSystemService.save(dpSubSystem);
        return CommonWebResult.success();
    }

    public CommonWebResult update(DpSubSystem dpSubSystem) {
        //TODO 待完善
//        iDpSubSystemService.update(null);
        return CommonWebResult.success();
    }

    public CommonWebResult remove(DpSubSystem dpSubSystem) {
        //TODO 待完善
        iDpSubSystemService.remove(null);
        return CommonWebResult.success();
    }

    public CommonWebResult queryAll(DpSubSystem dpSubSystem) {
        //TODO 待完善
//        iDpSubSystemService.query();
        return CommonWebResult.success();
    }
    public CommonWebResult query(DpSubSystem dpSubSystem) {
        //TODO 待完善
//        iDpSubSystemService.getOne(new QueryWrapper<DpSubSystem>().eq())
        return CommonWebResult.success();
    }


}
