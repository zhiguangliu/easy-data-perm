package cn.zhgliu.ezdp.permission.entity;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
public class DpPermissionVo extends DpPermission{

    private static final long serialVersionUID = 1L;

    private String text;

    public String getText() {
        return text;
    }

    public DpPermissionVo setText(String text) {
        this.text = text;
        return this;
    }
}
