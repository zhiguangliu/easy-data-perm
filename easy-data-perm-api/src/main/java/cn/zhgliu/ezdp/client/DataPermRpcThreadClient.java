package cn.zhgliu.ezdp.client;


import cn.zhgliu.ezdp.model.DpClientParam;

import java.util.concurrent.Callable;

/**
 * @author zhgliu
 */
public interface DataPermRpcThreadClient extends DataPermClient, Callable {
    //因为多线程无法用直接传参的方式实现参数传递，所以采用此方法将需要的参数传递到多线程内部。
    // todo 对于RPC模式如何将参数传递过来需要进一步验证。可能要针对不同的rpc框架针对性开发
    void prepare(DpClientParam dpClientParam);
}
