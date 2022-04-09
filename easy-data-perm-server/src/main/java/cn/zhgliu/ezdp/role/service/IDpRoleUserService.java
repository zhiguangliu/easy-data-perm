package cn.zhgliu.ezdp.role.service;

import cn.zhgliu.ezdp.web.Pagination;
import cn.zhgliu.ezdp.role.entity.DpRoleUser;
import cn.zhgliu.ezdp.user.vo.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhgliu
 * @since 2022-02-25
 */
public interface IDpRoleUserService extends IService<DpRoleUser> {

    /**
     * @param userInfo
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pagination<UserInfo> findUser(UserInfo userInfo, Integer pageNum, Integer pageSize,
                                  Boolean isAsc, String... column);

    /**
     * 此方法按照关键字搜索用户，无需具体区分登录名、显示名、手机号、电邮等
     * @param subSystemCode
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pagination<UserInfo> searchUser(String subSystemCode, String keyword, Integer pageNum, Integer pageSize,
                                    Boolean isAsc, String... column);

}
