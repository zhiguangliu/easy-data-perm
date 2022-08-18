package cn.zhgliu.ezdp.role.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.zhgliu.ezdp.comm.CommonWebResult;
import cn.zhgliu.ezdp.comm.controller.CommonController;
import cn.zhgliu.ezdp.role.entity.DpRole;
import cn.zhgliu.ezdp.role.entity.DpRoleUser;
import cn.zhgliu.ezdp.role.entity.DpRoleWithChecked;
import cn.zhgliu.ezdp.role.service.IDpRoleService;
import cn.zhgliu.ezdp.role.service.IDpRoleUserService;
import cn.zhgliu.ezdp.user.vo.UserInfo;
import cn.zhgliu.ezdp.web.Pagination;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhgliu
 * @since 2022-02-25
 */
@Controller
@RequestMapping({"/role/dp-role-user", "/rest/role/dp-role-user"})
public class DpRoleUserController extends CommonController<DpRoleUser> {

    public DpRoleUserController(IService<DpRoleUser> iService) {
        super(iService);
    }

    @Override
    public Pagination<DpRoleUser> page(Integer page, Integer rows, DpRoleUser param, Boolean isAsc, String... column) {
        if (StringUtils.isEmpty(param.getSubSystemCode())) {
            return new Pagination<>();
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(param.getUserName());
        userInfo.setEmail(param.getEmail());
        userInfo.setMobilePhone(param.getMobilePhone());
        Pagination<UserInfo> user = ((IDpRoleUserService) this.iService).findUser(userInfo, page, rows, isAsc, column);

        return createDpRoleUserPagination(user);
    }

    @RequestMapping("/searchUserByPage")
    public Pagination<DpRoleUser> searchUserByPage(Integer page, Integer rows,
                                                   String subSystemCode, String keyword,
                                                   Boolean isAsc, String... column) {
        if (StringUtils.isEmpty(subSystemCode)) {
            return new Pagination<>();
        }

        Pagination<UserInfo> user = ((IDpRoleUserService) this.iService).searchUser(subSystemCode, keyword, page, rows, isAsc, column);

        return createDpRoleUserPagination(user);
    }

    private Pagination<DpRoleUser> createDpRoleUserPagination(Pagination<UserInfo> user) {
        Pagination<DpRoleUser> ret = new Pagination<>();
        ret.setPageNum(user.getPageNum());
        ret.setPageSize(user.getPageSize());
        ret.setRows(user.getRows().stream().map(item -> {
            DpRoleUser tempUser = new DpRoleUser();
            BeanUtil.copyProperties(item, tempUser);
            return tempUser;
        }).collect(Collectors.toList()));
        return ret;
    }


    @Resource
    IDpRoleService iDpRoleService;

    @RequestMapping("/listRoleWithCheckInfo")
    public List<DpRoleWithChecked> listRoleWithCheckInfo(String subSystemCode, String userId) {
        if (StringUtils.isBlank(subSystemCode) || StringUtils.isBlank(userId)) {
            return new LinkedList<>();
        }

        DpRole param = new DpRole();
        param.setSubSystemCode(subSystemCode);

        // 查询所有角色
        List<DpRole> list = iDpRoleService.list(new QueryWrapper<>(param));
        // 收集所有角色的id
        List<Integer> roleIdList = list.stream().map(DpRole::getId).collect(Collectors.toList());
        // 查询所选用于与所有角色的关联关系
        List<DpRoleUser> roleUsers = this.iService.list(new QueryWrapper<DpRoleUser>().eq("user_id", userId).in("role_id", roleIdList));

        // key是角色id，value是用户角色关系数据记录id
        Map<Integer, Integer> roleUserMap = new HashMap<>();
        roleUsers.stream().forEach(item -> {
            roleUserMap.put(item.getRoleId(), item.getId());
        });

        List<DpRoleWithChecked> ret = new LinkedList<>();
        list.stream().forEach(item -> {
            DpRoleWithChecked temp = new DpRoleWithChecked();
            temp.setId(roleUserMap.get(item.getId()));
            temp.setChecked(roleUserMap.containsKey(item.getId()) ? 1 : 0);
            temp.setRoleId(item.getId());
            temp.setUserId(userId);
            temp.setRoleName(item.getRoleName());
            temp.setSubSystemCode(subSystemCode);
            ret.add(temp);
        });


        return ret;

    }

    @RequestMapping("/check")
    public CommonWebResult check(@RequestBody DpRoleUser dpRoleUser) {
        this.iService.save(dpRoleUser);
        return CommonWebResult.success(dpRoleUser);
    }

    @RequestMapping("/uncheck")
    public CommonWebResult uncheck(@RequestBody DpRoleUser dpRoleUser) {
        this.iService.removeById(dpRoleUser.getId());
        return CommonWebResult.success();
    }

}
