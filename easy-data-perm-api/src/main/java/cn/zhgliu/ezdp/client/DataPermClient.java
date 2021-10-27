package cn.zhgliu.ezdp.client;

public interface DataPermClient {
    /**
     * 这个方法是权限组织的核心方法，
     * 主要负责查询MatchingMode、rules，
     * 按照必要的逻辑判断后，决定是否调用SQLResolver，处理sql。
     * MatchingMode的判断不应带到SQLresolver中。
     *
     * @param sql
     * @param userId
     * @param queryId
     * @return
     */
    String addPermissionCondition(String sql, String userId, String operationIdentifier);
}
