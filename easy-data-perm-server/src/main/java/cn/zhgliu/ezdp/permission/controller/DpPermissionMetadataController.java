package cn.zhgliu.ezdp.permission.controller;


import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.easyui.Pagination;
import cn.zhgliu.ezdp.permission.entity.DpPermissionMetadata;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping({"/permission/dp-permission-metadata", "/rest/permission/dp-permission-metadata"})
@Slf4j
public class DpPermissionMetadataController extends CommonController<DpPermissionMetadata> {

    public DpPermissionMetadataController(IService<DpPermissionMetadata> iService) {
        super(iService);
    }

    @Override
    protected QueryWrapper<DpPermissionMetadata> createCondition(DpPermissionMetadata dpPermissionMetadata, Boolean isAsc, String... column) {
        QueryWrapper<DpPermissionMetadata> wrapper = new QueryWrapper<DpPermissionMetadata>();
        if (StringUtils.isNotEmpty(dpPermissionMetadata.getSubSystemCode())) {
            wrapper = wrapper.eq("sub_system_code", dpPermissionMetadata.getSubSystemCode());
        }
        if (StringUtils.isNotEmpty(dpPermissionMetadata.getOperationIdentifier())) {
            wrapper = wrapper.like("operation_identifier", dpPermissionMetadata.getOperationIdentifier());
        }
        if (StringUtils.isNotBlank(dpPermissionMetadata.getOperationName())) {
            wrapper = wrapper.like("operation_name", dpPermissionMetadata.getOperationName());
        }
        return wrapper;
    }

    @Override
    public Pagination<DpPermissionMetadata> page(Integer page, Integer rows, DpPermissionMetadata param, Boolean isAsc, String... column) {
        if (param.getSubSystemCode() == null) {
            return new Pagination<>();
        }
        return super.page(page, rows, param, isAsc, column);
    }

    @Override
    public List<DpPermissionMetadata> find(DpPermissionMetadata param, Boolean isAsc, String... column) {
        if (StringUtils.isBlank(param.getSubSystemCode())) {
            return new LinkedList<>();
        }
        QueryWrapper<DpPermissionMetadata> tQueryWrapper = new QueryWrapper<DpPermissionMetadata>();
        tQueryWrapper.like("sub_system_code", param.getSubSystemCode());
        if (StringUtils.isNotBlank(param.getOperationName())) {
            tQueryWrapper.like("operation_name", param.getOperationName());
        }

        if (column != null) {
            tQueryWrapper.orderBy(true, (isAsc != null ? isAsc : true), column);
        }
        List<DpPermissionMetadata> list = iService.list(tQueryWrapper);
        return list;
    }
}
