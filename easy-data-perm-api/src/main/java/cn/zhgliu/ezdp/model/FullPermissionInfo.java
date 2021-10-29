package cn.zhgliu.ezdp.model;

import cn.zhgliu.ezdp.consts.ApplyMethod;
import cn.zhgliu.ezdp.consts.MatchingMode;

import java.util.Collection;
import java.util.List;

/**
 * @author zhgliu
 */
public class FullPermissionInfo {
    ApplyMethod applyMethod;
    MatchingMode matchingMode;
    /**
     * 第一层是角色，每个角色对应一个项目
     * 第二层是每个角色的各个字段
     * 第三层是一个字段的多个值
     */
    Collection<List<DataPermissionItem>> permissions;

    public ApplyMethod getApplyMethod() {
        return applyMethod;
    }

    public FullPermissionInfo setApplyMethod(ApplyMethod applyMethod) {
        this.applyMethod = applyMethod;
        return this;
    }

    public MatchingMode getMatchingMode() {
        return matchingMode;
    }

    public void setMatchingMode(MatchingMode matchingMode) {
        this.matchingMode = matchingMode;
    }

    public Collection<List<DataPermissionItem>> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<List<DataPermissionItem>> permissions) {
        this.permissions = permissions;
    }
}
