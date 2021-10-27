package cn.zhgliu.ezdp.comm;

/**
 * @author zhgliu
 */
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

    public Integer getCode() {
        return code;
    }

    public CommonWebResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public CommonWebResult setStatus(String status) {
        this.status = status;
        return this;
    }

    public Object getData() {
        return data;
    }

    public CommonWebResult setData(Object data) {
        this.data = data;
        return this;
    }
}
