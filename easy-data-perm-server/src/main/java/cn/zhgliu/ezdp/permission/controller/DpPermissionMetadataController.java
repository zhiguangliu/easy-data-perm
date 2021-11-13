package cn.zhgliu.ezdp.permission.controller;


import cn.zhgliu.ezdp.comm.CommonWebResult;
import cn.zhgliu.ezdp.easyui.Pagination;
import cn.zhgliu.ezdp.permission.entity.DpPermissionMetadata;
import cn.zhgliu.ezdp.permission.service.IDpPermissionMetadataService;
import cn.zhgliu.ezdp.web.BatchData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@Controller
@RequestMapping({"/permission/dp-permission-metadata","/rest/permission/dp-permission-metadata"})
public class DpPermissionMetadataController {

    @Resource
    IDpPermissionMetadataService iDpPermissionMetadataService;

    @GetMapping("/page")
    @ResponseBody
    public Pagination<DpPermissionMetadata> page(Integer page, Integer rows, String operationName, String operationIdentifier) {
        IPage qPage = new Page(page, rows);
        QueryWrapper<DpPermissionMetadata> wrapper = new QueryWrapper<DpPermissionMetadata>();
        if (StringUtils.isNotEmpty(operationIdentifier)) {
            wrapper = wrapper.like("operation_identifier", operationIdentifier);
        }
        if (StringUtils.isNotBlank(operationName)) {
            wrapper = wrapper.like("operation_name", operationName);
        }
        IPage iPage = iDpPermissionMetadataService.page(qPage, wrapper);
        Pagination result = new Pagination(iPage.getCurrent(), iPage.getSize(), iPage.getRecords());
        return result;
    }

    @PostMapping("/data")
    @ResponseBody
    public CommonWebResult data(@RequestBody  BatchData<DpPermissionMetadata> data) {
        if (data.getDel() != null && !data.getDel().isEmpty()) {
            data.getDel().stream().forEach(item -> {
                iDpPermissionMetadataService.remove(new QueryWrapper<>(item));
            });
        }
        if (data.getEdit() != null && !data.getEdit().isEmpty()) {
            data.getEdit().stream().forEach(item -> {
                iDpPermissionMetadataService.updateById(item);
            });
        }
        if (data.getAdd() != null && !data.getAdd().isEmpty()) {
            data.getAdd().stream().forEach(item -> {
                iDpPermissionMetadataService.save(item);
            });
        }

        return CommonWebResult.success();
    }

}
