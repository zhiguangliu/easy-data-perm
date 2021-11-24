package cn.zhgliu.ezdp.permission.controller;


import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.permission.entity.DpPermissionMetadata;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping({"/permission/dp-permission-metadata","/rest/permission/dp-permission-metadata"})
@Slf4j
public class DpPermissionMetadataController extends CommonController<DpPermissionMetadata> {

    public DpPermissionMetadataController(IService<DpPermissionMetadata> iService) {
        super(iService);
    }

    @Override
    protected QueryWrapper<DpPermissionMetadata> createCondition(DpPermissionMetadata dpPermissionMetadata) {
        QueryWrapper<DpPermissionMetadata> wrapper = new QueryWrapper<DpPermissionMetadata>();
        if (StringUtils.isNotEmpty(dpPermissionMetadata.getOperationIdentifier())) {
            wrapper = wrapper.like("operation_identifier", dpPermissionMetadata.getOperationIdentifier());
        }
        if (StringUtils.isNotBlank(dpPermissionMetadata.getOperationName())) {
            wrapper = wrapper.like("operation_name", dpPermissionMetadata.getOperationName());
        }
        return wrapper;
    }

}
