package cn.zhgliu.ezdp.web;

import java.util.List;

public class BatchData<T> {
    private List<T> add;
    private List<T> del;
    private List<T> edit;

    public List<T> getAdd() {
        return add;
    }

    public BatchData<T> setAdd(List<T> add) {
        this.add = add;
        return this;
    }

    public List<T> getDel() {
        return del;
    }

    public BatchData<T> setDel(List<T> del) {
        this.del = del;
        return this;
    }

    public List<T> getEdit() {
        return edit;
    }

    public BatchData<T> setEdit(List<T> edit) {
        this.edit = edit;
        return this;
    }
}
