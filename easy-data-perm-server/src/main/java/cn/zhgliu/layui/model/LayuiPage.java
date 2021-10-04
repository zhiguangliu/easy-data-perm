package cn.zhgliu.layui.model;

import java.io.Serializable;
import java.util.List;

public class LayuiPage implements Serializable {
    private static final long serialVersionUID = 4042896059445051621L;
    Integer code;
    Integer count;
    List data;
    String msg;

    public LayuiPage() {
    }

    public LayuiPage(Integer code, Integer count, List data, String msg) {
        this.code = code;
        this.count = count;
        this.data = data;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public LayuiPage setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public LayuiPage setCount(Integer count) {
        this.count = count;
        return this;
    }

    public List getData() {
        return data;
    }

    public LayuiPage setData(List data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public LayuiPage setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
