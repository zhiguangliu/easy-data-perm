package cn.zhgliu.ezdp.comm;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonWebResult {
    private Integer code;
    private String status;
    private Object data;

    public CommonWebResult() {
    }

    public CommonWebResult(Integer code, String status, Object data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public static CommonWebResult success() {
        return new CommonWebResult(200, "success", null);
    }
}
