package cn.zhgliu.ezdp.helper;

/**
 * @author zhgliu
 */
public class DataPermHelper {

    static ThreadLocal<String> userId = new InheritableThreadLocal();

    public static void applyPermission(String userId) {
        DataPermHelper.userId.set(userId);
    }

    public static String getUserId() {
        return userId.get();
    }

    public static void clear() {
        userId.remove();
    }

}
