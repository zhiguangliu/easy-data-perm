package test.cn.zhgliu.ezdp.helper;

import cn.zhgliu.ezdp.helper.DataPermHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestDataPermHelper {

    private static final String USER_ID = "userId-Test";
    private static final String STATEMENT_CODE = "statementCode-Test";

    @Test
    public void multiThreadEmptyTest() throws InterruptedException {
        int threadCount = 2;

        DataPermHelper.applyPermission(null, null);

        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                String statementCode = DataPermHelper.getStatementCode();
                String userId = DataPermHelper.getUserId();
                System.out.println(Thread.currentThread().getName() + ":" + userId + " OF " + statementCode);
                Assert.assertNull(userId);
                Assert.assertNull(statementCode);
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await(5, TimeUnit.SECONDS);
    }
    @Test
    public void multiThreadTest() throws InterruptedException {
        int threadCount = 2;

        DataPermHelper.applyPermission(USER_ID, STATEMENT_CODE);

        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                String statementCode = DataPermHelper.getStatementCode();
                String userId = DataPermHelper.getUserId();
                System.out.println(Thread.currentThread().getName() + ":" + userId + " OF " + statementCode);
                Assert.assertEquals(userId, USER_ID);
                Assert.assertEquals(statementCode, STATEMENT_CODE);
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await(5, TimeUnit.SECONDS);
    }



}
