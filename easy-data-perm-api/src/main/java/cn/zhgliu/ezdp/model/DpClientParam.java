package cn.zhgliu.ezdp.model;

/**
 * @author zhgliu
 */
public class DpClientParam {
    private String sql;

    private String userId;

    private String queryId;

    public DpClientParam(String sql, String userId, String queryId) {
        this.sql = sql;
        this.userId = userId;
        this.queryId = queryId;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }
}
