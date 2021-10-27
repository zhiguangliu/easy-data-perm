package cn.zhgliu.ezdp.permission.service.impl;

import cn.zhgliu.ezdp.consts.MatchingMode;
import cn.zhgliu.ezdp.permission.entity.DpPermissionMetadata;
import cn.zhgliu.ezdp.permission.mapper.DpPermissionMetadataMapper;
import cn.zhgliu.ezdp.permission.service.IDpPermissionMetadataService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhgliu
 * @since 2021-08-05
 */
@Service
public class DpPermissionMetadataServiceImpl extends ServiceImpl<DpPermissionMetadataMapper, DpPermissionMetadata> implements IDpPermissionMetadataService {

    @Override
    public MatchingMode matchingMode(DpPermissionMetadata dpPermissionMetadata) {
        DpPermissionMetadata result = this.baseMapper.selectOne(new QueryWrapper<>(dpPermissionMetadata));
        return result != null ? MatchingMode.valueOf(result.getMatchingMode()) : null;
    }

    public static void main(String[] args) {
        System.out.println(MatchingMode.valueOf("LENIENT"));
    }
}
