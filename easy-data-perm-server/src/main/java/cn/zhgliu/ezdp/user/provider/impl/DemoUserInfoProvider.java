package cn.zhgliu.ezdp.user.provider.impl;

import cn.zhgliu.ezdp.user.provider.UserInfoProvider;
import cn.zhgliu.ezdp.user.vo.UserInfo;
import cn.zhgliu.ezdp.web.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhgliu
 */
@Component
public class DemoUserInfoProvider implements UserInfoProvider {

    static List<UserInfo> rows = new ArrayList<>(8);

    static {
        rows.add(new UserInfo().setUserId("1").setUserName("赢总").setEmail("liuda@zhgliu.cn").setMobilePhone("18618457220"));
        rows.add(new UserInfo().setUserId("2").setUserName("刘大").setEmail("liuda@zhgliu.cn").setMobilePhone("18618457221"));
        rows.add(new UserInfo().setUserId("3").setUserName("关二").setEmail("guaner@zhgliu.cn").setMobilePhone("18618457222"));
        rows.add(new UserInfo().setUserId("4").setUserName("张三").setEmail("zhangsan@zhgliu.cn").setMobilePhone("18618457223"));
        rows.add(new UserInfo().setUserId("5").setUserName("李四").setEmail("lisi@zhgliu.cn").setMobilePhone("18618457224"));
        rows.add(new UserInfo().setUserId("6").setUserName("朱五").setEmail("zhuwu@zhgliu.cn").setMobilePhone("18618457225"));
        rows.add(new UserInfo().setUserId("7").setUserName("杨六").setEmail("yangliu@zhgliu.cn").setMobilePhone("18618457226"));
        rows.add(new UserInfo().setUserId("8").setUserName("牛七").setEmail("niuqi@zhgliu.cn").setMobilePhone("18618457227"));
        rows.add(new UserInfo().setUserId("9").setUserName("马八").setEmail("maba@zhgliu.cn").setMobilePhone("18618457228"));
        rows.add(new UserInfo().setUserId("10").setUserName("侯九").setEmail("houjiu@zhgliu.cn").setMobilePhone("18618457229"));
    }

    @Override
    public Boolean support(String subsystemCode) {
        return "SELLER_SYSTEM".equals(subsystemCode);
    }

    @Override
    public UserInfo getUserInfoById(String userId) {
        List<UserInfo> collect = rows.stream()
                .filter(userInfo -> userInfo.getUserId().equals(userId))
                .collect(Collectors.toList());
        return collect.get(0);
    }

    @Override
    public Pagination<UserInfo> listUserInfoByPage(UserInfo userInfo, Integer pageNum, Integer pageSize,
                                                   Boolean isAsc, String... column) {
        Pagination ret = new Pagination();
        ret.setRows(rows);
        return ret;
    }

    @Override
    public Pagination<UserInfo> searchUserByKeywordByPage(String keyword, Integer pageNum, Integer pageSize, Boolean isAsc, String... column) {

        Pagination<UserInfo> userInfoPagination = listUserInfoByPage(null, null, null, null, "");

        if (StringUtils.isNotEmpty(keyword)) {
            List<UserInfo> collect = userInfoPagination.getRows().stream()
                    .filter(item -> StringUtils.contains(item.getEmail(), keyword))
                    .collect(Collectors.toList());
            userInfoPagination.setRows(collect);
        }

        return userInfoPagination;


    }


}
