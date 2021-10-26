package cn.zhgliu.ezdp.helper;

/**
 * @author zhgliu
 */
public class DataPermHelper {

    static ThreadLocal<String> userId = new InheritableThreadLocal<String>();
    static ThreadLocal<String> statementCode = new InheritableThreadLocal<String>();


    public static void applyPermission(String userId, String statementCode) {
        DataPermHelper.userId.set(userId);
        DataPermHelper.statementCode.set(statementCode);
    }

    public static String getUserId() {
        return userId.get();
    }

    public static String getStatementCode() {
        return statementCode.get();
    }
}
