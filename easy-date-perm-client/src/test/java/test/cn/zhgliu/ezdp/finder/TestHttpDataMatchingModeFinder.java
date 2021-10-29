package test.cn.zhgliu.ezdp.finder;

import cn.zhgliu.ezdp.consts.ApplyMethod;
import cn.zhgliu.ezdp.consts.MatchingMode;
import cn.zhgliu.ezdp.finder.impl.http.HttpDataMatchingModeFinder;
import cn.zhgliu.ezdp.model.DataPermissionBaseInfo;
import org.junit.Assert;
import org.junit.Test;

public class TestHttpDataMatchingModeFinder {
    public static final String serverUrl = "http://localhost:8899/ezdp";

    @Test
    public void findMatchingModeTest() {
        HttpDataMatchingModeFinder finder = new HttpDataMatchingModeFinder(serverUrl);
        DataPermissionBaseInfo dataPermissionBaseInfo = finder.findMatchingMode("TEST", "OP-1");
        System.out.println(dataPermissionBaseInfo.getMatchingMode());
        System.out.println(dataPermissionBaseInfo.getApplyMethod());
        Assert.assertEquals(dataPermissionBaseInfo.getMatchingMode(), MatchingMode.LENIENT);
        Assert.assertEquals(dataPermissionBaseInfo.getApplyMethod(), ApplyMethod.EMBED);

    }



}
