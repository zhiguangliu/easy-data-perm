package cn.zhgliu.ezdp.user.provider;

import cn.zhgliu.ezdp.user.vo.UserInfo;

import java.util.List;

public interface UserInfoProvider {

    Boolean support(String subsystemCode);

    UserInfo getUserInfoById();

    List<UserInfo> listUserInfo(UserInfo userInfo);

}
