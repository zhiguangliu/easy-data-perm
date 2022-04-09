package cn.zhgliu.ezdp.user.provider;

import cn.zhgliu.ezdp.user.vo.UserInfo;
import cn.zhgliu.ezdp.web.Pagination;

public interface UserInfoProvider {

    Boolean support(String subsystemCode);

    UserInfo getUserInfoById(String userId);

    Pagination<UserInfo> listUserInfoByPage(UserInfo userInfo, Integer pageNum, Integer pageSize,
                                            Boolean isAsc, String... column);

    Pagination<UserInfo> searchUserByKeywordByPage(String keyword, Integer pageNum, Integer pageSize,
                                                   Boolean isAsc, String... column);

}
