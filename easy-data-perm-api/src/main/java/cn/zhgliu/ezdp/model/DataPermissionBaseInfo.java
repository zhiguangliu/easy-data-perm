package cn.zhgliu.ezdp.model;

import cn.zhgliu.ezdp.consts.ApplyMethod;
import cn.zhgliu.ezdp.consts.MatchingMode;

/**
 * @author zhgliu
 */
public class DataPermissionBaseInfo {
    private MatchingMode matchingMode;
    private ApplyMethod applyMethod;

    public DataPermissionBaseInfo(MatchingMode matchingMode, ApplyMethod applyMethod) {
        this.matchingMode = matchingMode;
        this.applyMethod = applyMethod;
    }

    public MatchingMode getMatchingMode() {
        return matchingMode;
    }

    public DataPermissionBaseInfo setMatchingMode(MatchingMode matchingMode) {
        this.matchingMode = matchingMode;
        return this;
    }

    public ApplyMethod getApplyMethod() {
        return applyMethod;
    }

    public DataPermissionBaseInfo setApplyMethod(ApplyMethod applyMethod) {
        this.applyMethod = applyMethod;
        return this;
    }
}
