package cn.zhgliu.ezdp.finder;


public interface DataPermMatchingModeFinder {
    /**
     * 此方法用于查找某个子系统的一个查询的匹配模式
     *
     * @param queryId
     * @return
     */
    String findMatchingMode(String subSystem, String queryId);
}
