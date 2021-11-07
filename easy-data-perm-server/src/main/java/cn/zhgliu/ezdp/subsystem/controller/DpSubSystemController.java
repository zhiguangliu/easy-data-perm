package cn.zhgliu.ezdp.subsystem.controller;


import cn.hutool.json.JSONUtil;
import cn.zhgliu.ezdp.comm.CommonWebResult;
import cn.zhgliu.ezdp.subsystem.entity.DpSubSystem;
import cn.zhgliu.ezdp.subsystem.service.IDpSubSystemService;
import cn.zhgliu.ezdp.web.BatchData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
public class DpSubSystemController {

    @Resource
    IDpSubSystemService iDpSubSystemService;

    @GetMapping("/all")
    public List<DpSubSystem> all() {
        List<DpSubSystem> list = iDpSubSystemService.list(null);
        return list;
    }

    @PostMapping("/data")
    @Transactional(rollbackFor = Exception.class)
    public CommonWebResult data(@RequestBody BatchData<DpSubSystem> data) {
        log.debug(JSONUtil.toJsonStr(data));
        if (data.getDel() != null && !data.getDel().isEmpty()) {
            data.getDel().stream().forEach(item -> {
                iDpSubSystemService.remove(new QueryWrapper<>(item));
            });
        }
        if (data.getEdit() != null && !data.getEdit().isEmpty()) {
            data.getEdit().stream().forEach(item -> {
                iDpSubSystemService.updateById(item);
            });
        }
        if (data.getAdd() != null && !data.getAdd().isEmpty()) {
            data.getAdd().stream().forEach(item -> {
                iDpSubSystemService.save(item);
            });
        }
        return CommonWebResult.success();
    }


}
