package test.cn.zhgliu.ezdp.finder;

import cn.zhgliu.ezdp.finder.impl.http.HttpDataPermRuleFinder;
import org.junit.Test;

public class TestHttpDataPermRuleFinder {
    public static final String serverUrl = "http://localhost:8899/ezdp";

    @Test
    public void findRoleGroupedRulesTest() {
        HttpDataPermRuleFinder finder = new HttpDataPermRuleFinder(serverUrl);
//        List<List<DataPermissionItem>> test = finder.findRoleGroupedRules("TEST", "3", "OP-1");
//        System.out.println(JSONUtil.toJsonStr(test));
//        System.out.println(dataPermissionBaseInfo.getMatchingMode());
//        System.out.println(dataPermissionBaseInfo.getApplyMethod());
//        Assert.assertEquals(dataPermissionBaseInfo.getMatchingMode(), MatchingMode.LENIENT);
//        Assert.assertEquals(dataPermissionBaseInfo.getApplyMethod(), ApplyMethod.EMBED);

    }



}
