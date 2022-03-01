package cn.zhgliu.ezdp.comm.controller;

import cn.zhgliu.ezdp.comm.CommonWebResult;
import cn.zhgliu.ezdp.web.Pagination;
import cn.zhgliu.ezdp.web.BatchData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhgliu
 */
@RestController
public abstract class CommonController<T> {

    protected IService<T> iService;

    public CommonController(IService<T> iService) {
        this.iService = iService;
    }

    @GetMapping("/all")
    public List<T> all(Boolean isAsc, String... column) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        if (column != null) {
            wrapper.orderBy(true, (isAsc != null ? isAsc : true), column);
        }
        List<T> list = iService.list(wrapper);
        return list;
    }


    @GetMapping("/list")
    public List<T> find(T param, Boolean isAsc, String... column) {
        QueryWrapper<T> tQueryWrapper = new QueryWrapper<>(param);
        if (column != null) {
            tQueryWrapper.orderBy(true, (isAsc != null ? isAsc : true), column);
        }
        List<T> ret = iService.list(tQueryWrapper);
        return ret;
    }


    protected QueryWrapper<T> createCondition(T t, Boolean isAsc, String... column) {
        QueryWrapper<T> wrapper = new QueryWrapper<T>(t);
        if (column != null) {
            wrapper.orderBy(true, (isAsc != null ? isAsc : true), column);
        }
        return wrapper;
    }

    @GetMapping("/page")
    @ResponseBody
    public Pagination<T> page(Integer page, Integer rows, T param, Boolean isAsc, String... column) {
        IPage qPage = new Page(page, rows);
        IPage iPage = iService.page(qPage, createCondition(param, isAsc, column));
        Pagination result = new Pagination(iPage.getCurrent(), iPage.getSize(), iPage.getRecords());
        return result;
    }

    @PostMapping("/data")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public CommonWebResult data(@RequestBody BatchData<T> data) {
        if (data.getDel() != null && !data.getDel().isEmpty()) {
            data.getDel().stream().forEach(item -> {
                iService.remove(new QueryWrapper<>(item));
            });
        }
        if (data.getEdit() != null && !data.getEdit().isEmpty()) {
            data.getEdit().stream().forEach(item -> {
                iService.updateById(item);
            });
        }
        if (data.getAdd() != null && !data.getAdd().isEmpty()) {
            data.getAdd().stream().forEach(item -> {
                iService.save(item);
            });
        }

        return CommonWebResult.success();
    }

}
