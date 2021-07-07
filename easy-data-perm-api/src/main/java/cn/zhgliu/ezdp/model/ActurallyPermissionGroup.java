package cn.zhgliu.ezdp.model;

import cn.zhgliu.ezdp.consts.ApplyMode;
import cn.zhgliu.ezdp.consts.MatchingMode;

import java.util.List;

public class ActurallyPermissionGroup {
    ApplyMode applyMode;
    MatchingMode matchingMode;
    /**
     * 第一层是角色，每个角色对应一个项目
     * 第二层是每个角色的各个字段
     * 第三层是一个字段的多个值
     */
    List<List<List<DataPermissionItem>>> permissions;

    public ApplyMode getApplyMode() {
        return applyMode;
    }

    public void setApplyMode(ApplyMode applyMode) {
        this.applyMode = applyMode;
    }

    public MatchingMode getMatchingMode() {
        return matchingMode;
    }

    public void setMatchingMode(MatchingMode matchingMode) {
        this.matchingMode = matchingMode;
    }

    public List<List<List<DataPermissionItem>>> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<List<List<DataPermissionItem>>> permissions) {
        this.permissions = permissions;
    }
}
