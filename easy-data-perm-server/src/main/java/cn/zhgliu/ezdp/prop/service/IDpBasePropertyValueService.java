package cn.zhgliu.ezdp.prop.service;

import cn.zhgliu.ezdp.prop.entity.DpBasePropertyValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
public interface IDpBasePropertyValueService extends IService<DpBasePropertyValue> {

    List<String> getProperties(String subsystemCode, String userId,String propertyCode);

}
