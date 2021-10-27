package cn.zhgliu.ezdp.api.controller;

import cn.zhgliu.ezdp.comm.CommonWebResult;
import cn.zhgliu.ezdp.consts.MatchingMode;
import cn.zhgliu.ezdp.model.DpDataGroupListInClientVo;
import cn.zhgliu.ezdp.permission.entity.DpPermissionMetadata;
import cn.zhgliu.ezdp.permission.entity.WithRoleDpPermissionItem;
import cn.zhgliu.ezdp.permission.service.IDpPermissionItemService;
import cn.zhgliu.ezdp.permission.service.IDpPermissionMetadataService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author zhgliu
 */
@RestController
@RequestMapping("/api")
public class ApiInterfaceController {

    @Resource
    IDpPermissionMetadataService iDpPermissionMetadataService;

    @Value("${defaultMatchingMode:LENIENT}")
    MatchingMode defaultMatchingMode;

    @GetMapping("/matchingMode")
    public CommonWebResult matchingMode(@RequestParam String subsystem, @RequestParam String operationIdentifier) {
        DpPermissionMetadata param = new DpPermissionMetadata();
        param.setSubSystemCode(subsystem);
        param.setOperationIdentifier(operationIdentifier);
        MatchingMode result = iDpPermissionMetadataService.matchingMode(param);
        return CommonWebResult.success().setData(result != null ? result : defaultMatchingMode);
    }

    @Resource
    IDpPermissionItemService iDpPermissionItemService;

    @GetMapping("/permissions")
    public Collection<List<DpDataGroupListInClientVo>> permissions(@RequestParam String subsystem, @RequestParam String operationIdentifier, @RequestParam String userId) {
        List<WithRoleDpPermissionItem> ret = iDpPermissionItemService.findPermissionItems(subsystem, operationIdentifier, userId);
        Map<Integer, List<DpDataGroupListInClientVo>> tempMap = new HashMap<>();
        ret.stream().forEach(item->{
            DpDataGroupListInClientVo target = new DpDataGroupListInClientVo();
            BeanUtils.copyProperties(item, target);
            if (!tempMap.containsKey(item.getRoleId())) {
                tempMap.put(item.getRoleId(), new LinkedList<>());
            }
            tempMap.get(item.getRoleId()).add(target);
        });

        Collection<List<DpDataGroupListInClientVo>> values = tempMap.values();
        return values;
    }

    @GetMapping("/matchingModeAndPermissions")
    public Object matchingModeAndPermissions(@RequestParam String subsystem, @RequestParam String statementCode) {
        return "success";
    }


}
