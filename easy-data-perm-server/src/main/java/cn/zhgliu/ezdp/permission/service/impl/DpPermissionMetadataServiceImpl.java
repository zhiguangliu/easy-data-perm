package cn.zhgliu.ezdp.permission.service.impl;

import cn.zhgliu.ezdp.consts.ApplyMethod;
import cn.zhgliu.ezdp.consts.MatchingMode;
import cn.zhgliu.ezdp.model.DataPermissionBaseInfo;
import cn.zhgliu.ezdp.permission.entity.DpPermissionMetadata;
import cn.zhgliu.ezdp.permission.mapper.DpPermissionMetadataMapper;
import cn.zhgliu.ezdp.permission.service.IDpPermissionMetadataService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@Service
public class DpPermissionMetadataServiceImpl extends ServiceImpl<DpPermissionMetadataMapper, DpPermissionMetadata> implements IDpPermissionMetadataService {

    @Override
    public DataPermissionBaseInfo matchingMode(DpPermissionMetadata dpPermissionMetadata) {
        DpPermissionMetadata result = this.baseMapper.selectOne(new QueryWrapper<>(dpPermissionMetadata));
        MatchingMode matchingMode = MatchingMode.valueOf(result.getMatchingMode());
        ApplyMethod applyMethod = ApplyMethod.valueOf(result.getApplyMethod());
        return new DataPermissionBaseInfo(matchingMode, applyMethod);
    }

    public static void main(String[] args) {
        System.out.println(MatchingMode.valueOf("LENIENT"));
    }
}
