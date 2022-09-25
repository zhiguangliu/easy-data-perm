package cn.zhgliu.ezdp.prop.service.impl;

import cn.zhgliu.ezdp.prop.entity.DpBasePropertyValue;
import cn.zhgliu.ezdp.prop.mapper.CustomDpBasePropertyValueMapper;
import cn.zhgliu.ezdp.prop.mapper.DpBasePropertyValueMapper;
import cn.zhgliu.ezdp.prop.service.IDpBasePropertyValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@Service
public class DpBasePropertyValueServiceImpl extends ServiceImpl<DpBasePropertyValueMapper, DpBasePropertyValue> implements IDpBasePropertyValueService {

    @Resource
    CustomDpBasePropertyValueMapper customDpBasePropertyValueMapper;

    @Override
    public List<String> getProperties(String subsystemCode, String userId, Integer roleId, String propertyCode) {
        return customDpBasePropertyValueMapper.getProperties(subsystemCode, userId, roleId, propertyCode);
    }
}
