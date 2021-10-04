package cn.zhgliu.ezdp.common.controller;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


public class CommonController<S extends ServiceImpl,T> {
    protected S service;

    public Object saveOrUpdate(T t) {
        boolean b = service.saveOrUpdate(t);
        if (b) {
            return "success";
        } else {
            return "fail";
        }
    }

}
