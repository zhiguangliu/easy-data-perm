package cn.zhgliu.ezdp.resolver;


import cn.zhgliu.ezdp.model.DpDataGroupListInClientVo;

import java.util.List;

/**
 * 根据传入的条件处理sql
 * @author zhgliu
 */
public interface DataPermSqlResolver {
    /**
     * 根据传入的sql和条件，将条件拼装在sql中。
     * 如果传入条件为空，则返回传入的sql
     * 匹配模式，不是resolver需要关心的，如果已经调用resolveSql方法的时候，
     * 是已经确认rules不为空的情况下的。如果真的传了空的rules过来，也只是说明这个sql不需要做什么处理，直接返回原sql就行了
     *
     * @param sql
     * @param rules
     * @return
     */
    String resolveSqlWithGroupRule(String sql, List<List<DpDataGroupListInClientVo>> rules);
}
