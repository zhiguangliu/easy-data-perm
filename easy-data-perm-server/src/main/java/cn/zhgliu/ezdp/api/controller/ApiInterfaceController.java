package cn.zhgliu.ezdp.api.controller;

import cn.zhgliu.ezdp.consts.MatchingMode;
import cn.zhgliu.ezdp.consts.ParamNames;
import cn.zhgliu.ezdp.consts.ValueType;
import cn.zhgliu.ezdp.model.DataPermissionBaseInfo;
import cn.zhgliu.ezdp.model.DataPermissionItem;
import cn.zhgliu.ezdp.model.FullPermissionInfo;
import cn.zhgliu.ezdp.permission.entity.DpPermissionMetadata;
import cn.zhgliu.ezdp.permission.entity.WithRoleDpPermissionItem;
import cn.zhgliu.ezdp.permission.service.IDpPermissionItemService;
import cn.zhgliu.ezdp.permission.service.IDpPermissionMetadataService;
import cn.zhgliu.ezdp.prop.service.IDpBasePropertyValueService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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
    public DataPermissionBaseInfo matchingMode(@RequestParam(ParamNames.SUBSYSTEM) String subsystem,
                                               @RequestParam(ParamNames.OPERATION_IDENTIFIER) String operationIdentifier) {
        DpPermissionMetadata param = new DpPermissionMetadata();
        param.setSubSystemCode(subsystem);
        param.setOperationIdentifier(operationIdentifier);
        return iDpPermissionMetadataService.matchingMode(param);
    }

    @Resource
    IDpPermissionItemService iDpPermissionItemService;

    @GetMapping("/permissions")
    public Collection<List<DataPermissionItem>> permissions(@RequestParam(ParamNames.SUBSYSTEM) String subsystem,
                                                            @RequestParam(ParamNames.OPERATION_IDENTIFIER) String operationIdentifier,
                                                            @RequestParam(ParamNames.USER_ID) String userId) {
        return queryPermissions(subsystem, operationIdentifier, userId);
    }

    private Collection<List<DataPermissionItem>> queryPermissions(String subsystem, String operationIdentifier, String userId) {
        List<WithRoleDpPermissionItem> ret = iDpPermissionItemService.findPermissionItems(subsystem, operationIdentifier, userId);
        if (ret.size() > 0) {
            Map<Integer, List<DataPermissionItem>> tempMap = new HashMap<>();
            ret.stream().forEach(item -> {
                DataPermissionItem target = new DataPermissionItem();
                BeanUtils.copyProperties(item, target);
                Integer roleId = item.getRoleId();
                if (ValueType.PROPERTY.toString().equals(item.getValueType())) {
                    target.setFieldValue(getPropertyValue(subsystem, userId, roleId, item.getFieldValue()));
                }
                if (ValueType.USER_ID.toString().equals(item.getValueType())) {
                    target.setFieldValue(userId);
                }

                if (!tempMap.containsKey(roleId)) {
                    tempMap.put(roleId, new LinkedList<>());
                }
                tempMap.get(roleId).add(target);
            });

            return tempMap.values();
        } else {
            return new LinkedList<>();
        }
    }


    @Resource
    IDpBasePropertyValueService iDpBasePropertyValueService;

    private String getPropertyValue(String subsystem, String userId, Integer roleId, String fieldValue) {
        List<String> properties = iDpBasePropertyValueService.getProperties(subsystem, userId, roleId, fieldValue);

        return properties.stream().collect(Collectors.joining(","));
    }

    @GetMapping("/fullPermissionInfo")
    public FullPermissionInfo matchingModeAndPermissions(@RequestParam(ParamNames.SUBSYSTEM) String subsystem,
                                                         @RequestParam(ParamNames.OPERATION_IDENTIFIER) String operationIdentifier,
                                                         @RequestParam(ParamNames.USER_ID) String userId) {
        FullPermissionInfo fullPermissionInfo = new FullPermissionInfo();
        DataPermissionBaseInfo info = matchingMode(subsystem, operationIdentifier);
        Collection<List<DataPermissionItem>> permissions = queryPermissions(subsystem, operationIdentifier, userId);
        fullPermissionInfo.setMatchingMode(info.getMatchingMode());
        fullPermissionInfo.setApplyMethod(info.getApplyMethod());
        fullPermissionInfo.setPermissions(permissions);
        return fullPermissionInfo;
    }


}
