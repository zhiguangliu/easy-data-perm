package cn.zhgliu.ezdp.role.service.impl;

import cn.zhgliu.ezdp.role.entity.DpRoleUser;
import cn.zhgliu.ezdp.role.mapper.DpRoleUserMapper;
import cn.zhgliu.ezdp.role.service.IDpRoleUserService;
import cn.zhgliu.ezdp.user.provider.UserInfoProvider;
import cn.zhgliu.ezdp.user.vo.UserInfo;
import cn.zhgliu.ezdp.web.Pagination;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhgliu
 * @since 2022-02-25
 */
@Service
public class DpRoleUserServiceImpl extends ServiceImpl<DpRoleUserMapper, DpRoleUser> implements IDpRoleUserService {

    @Resource
    List<UserInfoProvider> userInfoProviderList;

    @Override
    public Pagination<UserInfo> findUser(UserInfo userInfo, Integer pageNum, Integer pageSize) {
        String subSystemCode = userInfo.getSubSystemCode();
        for (UserInfoProvider userInfoProvider : userInfoProviderList) {
            if (userInfoProvider.support(subSystemCode)) {
                return userInfoProvider.listUserInfoByPage(userInfo, pageNum, pageSize);
            }
        }
        throw new RuntimeException("没有对应的用户信息提供者！");
    }
}
