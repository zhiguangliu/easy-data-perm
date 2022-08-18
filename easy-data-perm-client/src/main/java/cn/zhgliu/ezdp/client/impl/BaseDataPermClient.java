package cn.zhgliu.ezdp.client.impl;


import cn.zhgliu.ezdp.client.DataPermClient;
import cn.zhgliu.ezdp.consts.MatchingMode;
import cn.zhgliu.ezdp.exception.ComponentNotExistException;
import cn.zhgliu.ezdp.exception.DataPermRuleFetchException;
import cn.zhgliu.ezdp.exception.ResolveSqlFailException;
import cn.zhgliu.ezdp.finder.DataPermMatchingModeFinder;
import cn.zhgliu.ezdp.finder.DataPermRuleFinder;
import cn.zhgliu.ezdp.model.DataPermissionBaseInfo;
import cn.zhgliu.ezdp.model.DataPermissionItem;
import cn.zhgliu.ezdp.resolver.DataPermSqlResolver;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * @author zhgliu
 */
public class BaseDataPermClient implements DataPermClient {

    Logger log = LoggerFactory.getLogger(BaseDataPermClient.class);

    protected String subSystem;

    protected ThreadLocal<MatchingMode> currentMatchingMode = new ThreadLocal<>();

    protected MatchingMode defaultMatchingMode = MatchingMode.LENIENT;

    protected DataPermMatchingModeFinder dataPermMatchingModeFinder;

    protected DataPermRuleFinder dataPermRuleFinder;

    protected DataPermSqlResolver dataPermSqlResolver;


    public BaseDataPermClient(String subSystem) {
        this.subSystem = subSystem;
    }

    public BaseDataPermClient(DataPermMatchingModeFinder dataPermMatchingModeFinder,
                              DataPermRuleFinder dataPermRuleFinder, DataPermSqlResolver dataPermSqlResolver,
                              String subSystem) {
        this.dataPermMatchingModeFinder = dataPermMatchingModeFinder;
        this.dataPermRuleFinder = dataPermRuleFinder;
        this.dataPermSqlResolver = dataPermSqlResolver;
        this.subSystem = subSystem;
    }

    public BaseDataPermClient(MatchingMode defaultMatchingMode, DataPermMatchingModeFinder dataPermMatchingModeFinder,
                              DataPermRuleFinder dataPermRuleFinder, DataPermSqlResolver dataPermSqlResolver,
                              String subSystem) {
        this.defaultMatchingMode = defaultMatchingMode;
        this.dataPermMatchingModeFinder = dataPermMatchingModeFinder;
        this.dataPermRuleFinder = dataPermRuleFinder;
        this.dataPermSqlResolver = dataPermSqlResolver;
        this.subSystem = subSystem;
    }

    public BaseDataPermClient() {

    }

    @Override
    public String addPermissionCondition(String sql, String userId, String operationIdentifier) {
        // 检查依赖的组件状态
        if (dataPermMatchingModeFinder == null) {
            log.warn("dataPermMatchingModeFinder为空，可能需要检查配置文件确认原因");
        }
        if (dataPermRuleFinder == null) {
            throw new ComponentNotExistException("没有找到dataPermRuleFinder的实例，请检查配置文件");
        }
        if (dataPermSqlResolver == null) {
            throw new ComponentNotExistException("没有找到dataPermSqlResolver的实例，请检查配置文件");
        }

        // 最终返回的结果
        String ret = null;

        // 获取匹配模式。获取不到使用默认匹配模式
        try {
            DataPermissionBaseInfo dataPermissionBaseInfo = dataPermMatchingModeFinder.findMatchingMode(subSystem, operationIdentifier);
            currentMatchingMode.set(dataPermissionBaseInfo.getMatchingMode());
        } catch (Exception e) {
            log.warn("GETTING MATCHING MODE ERROR. DETAIL REASON IS : {}", e.getMessage());
            currentMatchingMode.set(defaultMatchingMode);
        }

        // 获取规则
        List<List<DataPermissionItem>> rules = null;
        try {
            rules = dataPermRuleFinder.findRoleGroupedRules(subSystem, userId, operationIdentifier);
        } catch (Exception e) {
            log.warn("FIND RULES FAIL.FAIL REASON:\n{}", ExceptionUtils.getStackTrace(e));
        }

        // 判断获取的规则是否可用。如果不可用，根据匹配模式进行相应处理
        if (rules == null || rules.size() == 0) {
            if (MatchingMode.STRICT == currentMatchingMode.get()) {
                throw new DataPermRuleFetchException("THE SYSTEM CONFIG THE MATCHINGMODE TO \"STRICT\","
                        + " BUT THERE IS NO RULE FOR CURRENT USER. THE USER ID IS:" + userId
                        + " AND THE OPERATION IDENTIFIER IS:" + operationIdentifier);
            }
            if (MatchingMode.LENIENT == currentMatchingMode.get()) {
                log.info("THE SYSTEM CONFIG THE MATCHINGMODE TO \"LENIENT\","
                        + " AND THERE IS NO RULE FOR CURRENT USER. THE USER ID IS:" + userId
                        + " AND THE OPERATION IDENTIFIER IS:" + operationIdentifier);
                return sql;
            }
        }

        try {
            ret = dataPermSqlResolver.resolveSqlWithGroupRule(sql, rules);
        } catch (Exception e) {
            log.debug(ExceptionUtils.getStackTrace(e));

            if (MatchingMode.LENIENT == currentMatchingMode.get()) {
                log.warn("SOME ERROR HAPPENED ON APPLY RULES TO THIS QUERY, WHILE THIS QUERY IS SET TO \"LENIENT\"" +
                        "THE SQL WILL BE RETURN AS IT PASSED IN! THE USER ID IS:" + userId
                        + " AND THE QUERY CODE IS:" + operationIdentifier);
                log.warn(ExceptionUtils.getStackTrace(e));
                return sql;
            } else {
                throw new ResolveSqlFailException("SOME ERROR HAPPENED ON APPLY RULES TO THIS QUERY," +
                        " WHILE THIS QUERY IS SET TO \"STRICT\"" +
                        "THE SQL WILL BE RETURN AS IT PASSED IN! THE USER ID IS:" + userId
                        + " AND THE QUERY CODE IS:" + operationIdentifier);
            }

        }
        return ret;
    }


    public DataPermMatchingModeFinder getDataPermMatchingModeFinder() {
        return dataPermMatchingModeFinder;
    }

    public void setDataPermMatchingModeFinder(DataPermMatchingModeFinder dataPermMatchingModeFinder) {
        this.dataPermMatchingModeFinder = dataPermMatchingModeFinder;
    }

    public DataPermRuleFinder getDataPermRuleFinder() {
        return dataPermRuleFinder;
    }

    public void setDataPermRuleFinder(DataPermRuleFinder dataPermRuleFinder) {
        this.dataPermRuleFinder = dataPermRuleFinder;
    }

    public DataPermSqlResolver getDataPermSqlResolver() {
        return dataPermSqlResolver;
    }

    public void setDataPermSqlResolver(DataPermSqlResolver dataPermSqlResolver) {
        this.dataPermSqlResolver = dataPermSqlResolver;
    }

    public MatchingMode getDefaultMatchingMode() {
        return defaultMatchingMode;
    }

    public void setDefaultMatchingMode(MatchingMode defaultMatchingMode) {
        this.defaultMatchingMode = defaultMatchingMode;
    }
}
