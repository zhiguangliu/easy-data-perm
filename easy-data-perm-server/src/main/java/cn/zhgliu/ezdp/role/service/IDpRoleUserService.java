package cn.zhgliu.ezdp.role.service;

import cn.zhgliu.ezdp.web.Pagination;
import cn.zhgliu.ezdp.role.entity.DpRoleUser;
import cn.zhgliu.ezdp.user.vo.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhgliu
 * @since 2022-02-25
 */
public interface IDpRoleUserService extends IService<DpRoleUser> {

    Pagination<UserInfo> findUser(UserInfo userInfo);

}
