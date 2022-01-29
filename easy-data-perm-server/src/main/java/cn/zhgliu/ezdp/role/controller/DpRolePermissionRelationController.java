package cn.zhgliu.ezdp.role.controller;


import cn.hutool.json.JSONUtil;
import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.permission.entity.DpPermission;
import cn.zhgliu.ezdp.permission.entity.DpPermissionMetadata;
import cn.zhgliu.ezdp.permission.entity.DpPermissionVo;
import cn.zhgliu.ezdp.permission.service.IDpPermissionMetadataService;
import cn.zhgliu.ezdp.permission.service.IDpPermissionService;
import cn.zhgliu.ezdp.role.entity.DpRolePermissionRelation;
import cn.zhgliu.ezdp.role.entity.DpRolePermissionRelationVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@Controller
@RequestMapping({"/role/dp-role-permission-relation", "rest/role/dp-role-permission-relation"})
public class DpRolePermissionRelationController extends CommonController<DpRolePermissionRelation> {

    @Resource
    IDpPermissionService iDpPermissionService;

    @Resource
    IDpPermissionMetadataService iDpPermissionMetadataService;

    public DpRolePermissionRelationController(IService<DpRolePermissionRelation> iService) {
        super(iService);
    }

    @Override
    public List<DpRolePermissionRelation> find(DpRolePermissionRelation param, Boolean isAsc, String... column) {
        if (param.getRoleId() == null) {
            return new LinkedList<>();
        }
        List<DpRolePermissionRelation> relations = super.find(param, isAsc, column);
        List<Integer> permissionIdList = relations.stream().map(DpRolePermissionRelation::getPermissionId).collect(Collectors.toList());

        if (relations.size() == 0) {
            return relations;
        }

        Collection<DpPermission> dpPermissions = iDpPermissionService.listByIds(permissionIdList);
        Map<Integer, DpPermission> permissionMap = dpPermissions.stream().collect(Collectors.toMap(DpPermission::getId, item -> item));

        Set<Integer> metadataIdList = dpPermissions.stream().map(DpPermission::getMetadataId).collect(Collectors.toSet());
        Collection<DpPermissionMetadata> metadata = iDpPermissionMetadataService.listByIds(metadataIdList);
        Map<Integer, DpPermissionMetadata> metadataMap = metadata.stream().collect(Collectors.toMap(DpPermissionMetadata::getId, item -> item));

        List<DpRolePermissionRelation> collect = relations.stream().map(item -> {
            DpRolePermissionRelationVo newItem = new DpRolePermissionRelationVo();
            BeanUtils.copyProperties(item, newItem);
            DpPermission dpPermission = permissionMap.get(item.getPermissionId());
            newItem.setPermissionName(dpPermission.getPermissionName());

            DpPermissionMetadata dpPermissionMetadata = metadataMap.get(dpPermission.getMetadataId());
            newItem.setOperationName(dpPermissionMetadata.getOperationName());

            return newItem;
        }).collect(Collectors.toList());

        return collect;
    }


    @GetMapping("/unchecked")
    public List listUnchecked(Integer roleId, Integer metadataId) {
        return listItems(roleId, metadataId, false);
    }

    @GetMapping("/checked")
    public List listChecked(Integer roleId, Integer metadataId) {
        return listItems(roleId, metadataId, true);
    }

    private List listItems(Integer roleId, Integer metadataId, Boolean isChecked) {
        if (roleId == null || metadataId == null) {
            return new LinkedList();
        }
        DpRolePermissionRelation p1 = new DpRolePermissionRelation();
        p1.setRoleId(roleId);
        List<DpRolePermissionRelation> relations = this.iService.list(new QueryWrapper<>(p1));
        Set<Integer> myPermissions = relations.stream().map(DpRolePermissionRelation::getPermissionId).collect(Collectors.toSet());

        DpPermission p2 = new DpPermission();
        p2.setMetadataId(metadataId);
        List<DpPermission> permissions = iDpPermissionService.list(new QueryWrapper<>(p2));
        List<DpPermission> checkedItem = permissions.stream()
                .filter(item -> isChecked == myPermissions.contains(item.getId()))
                .map(item -> {
                    DpPermissionVo newItem = new DpPermissionVo();
                    BeanUtils.copyProperties(item, newItem);
                    newItem.setText(item.getPermissionName());
                    return newItem;
                })
                .collect(Collectors.toList());
        return checkedItem;
    }

    @PostMapping("/relation")
    public void relation(@RequestBody List<DpRolePermissionRelation> relation) {
        System.out.println(JSONUtil.toJsonStr(relation));
        iService.saveBatch(relation);
    }

    @PostMapping("/unrelation")
    public void unrelation(@RequestBody List<DpRolePermissionRelation> relation) {
        List<Integer> ids = new LinkedList<>();
        relation.stream()
                .forEach(item -> {
                    List<DpRolePermissionRelation> list = iService.list(new QueryWrapper<>(item));
                    List<Integer> idToDelete = list.stream().map(DpRolePermissionRelation::getId).collect(Collectors.toList());
                    ids.addAll(idToDelete);
                });

        iService.removeByIds(ids);
    }

}
