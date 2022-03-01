package cn.zhgliu.ezdp.user.provider.impl;

import cn.zhgliu.ezdp.user.provider.UserInfoProvider;
import cn.zhgliu.ezdp.user.vo.UserInfo;
import cn.zhgliu.ezdp.web.Pagination;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhgliu
 */
@Component
public class DemoUserInfoProvider implements UserInfoProvider {
    @Override
    public Boolean support(String subsystemCode) {
        return true;
    }

    @Override
    public UserInfo getUserInfoById(String userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("1")
                .setUserName("总返回张三")
                .setEmail("zhangsan@zhgliu.cn")
                .setMobilePhone("18618457227");
        return userInfo;
    }

    @Override
    public Pagination<UserInfo> listUserInfoByPage(UserInfo userInfo, Integer pageNum, Integer pageSize) {
        Pagination ret = new Pagination();

        List<UserInfo> rows = new ArrayList<>(8);
        rows.add(new UserInfo().setUserId("3").setUserName("张三").setEmail("zhangsan@zhgliu.cn").setMobilePhone("18618457223"));
        rows.add(new UserInfo().setUserId("4").setUserName("李四").setEmail("lisi@zhgliu.cn").setMobilePhone("18618457224"));
        rows.add(new UserInfo().setUserId("5").setUserName("朱五").setEmail("zhuwu@zhgliu.cn").setMobilePhone("18618457225"));
        rows.add(new UserInfo().setUserId("6").setUserName("杨六").setEmail("yangliu@zhgliu.cn").setMobilePhone("18618457226"));
        rows.add(new UserInfo().setUserId("7").setUserName("牛七").setEmail("niuqi@zhgliu.cn").setMobilePhone("18618457227"));
        rows.add(new UserInfo().setUserId("8").setUserName("马八").setEmail("maba@zhgliu.cn").setMobilePhone("18618457228"));
        ret.setRows(rows);

        return ret;
    }
}
