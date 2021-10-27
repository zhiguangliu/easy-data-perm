package cn.zhgliu.ezdp.finder;


import cn.zhgliu.ezdp.model.DpDataGroupListInClientVo;

import java.util.List;

/**
 * @author zhgliu
 */
public interface DataPermRuleFinder {
    /**
     * 此方法不应返回空值，如果没有规则，需要返回空的List。
     * @param subSystem 子系统编码
     * @param userId 用户标识
     * @param queryId 操作标识
     * @return 按角色分组的双层权限列表。第一层的List是按角色划分的，之间是或的关系；第二层的List是角色内的所有权限，将这些权限按filedName分组，分组间是且的关系，分组内是或的关系
     */
    List<List<DpDataGroupListInClientVo>> findRoleGroupedRules(String subSystem, String userId, String operationIdentifier);
}
