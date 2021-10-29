package cn.zhgliu.ezdp.finder;


import cn.zhgliu.ezdp.model.DataPermissionBaseInfo;

/**
 * @author zhgliu
 */
public interface DataPermMatchingModeFinder {
    /**
     * 此方法用于查找某个子系统的一个查询的匹配模式
     * @return
     */
    DataPermissionBaseInfo findMatchingMode(String subSystem, String operationIdentifier);
}
