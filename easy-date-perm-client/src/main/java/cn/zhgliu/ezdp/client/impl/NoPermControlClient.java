package cn.zhgliu.ezdp.client.impl;


import cn.zhgliu.ezdp.client.DataPermClient;

/**
 * @author zhgliu
 */
public class NoPermControlClient implements DataPermClient {

    @Override
    public String addPermissionCondition(String sql, String userId, String queryId) {
        return sql;
    }

}
