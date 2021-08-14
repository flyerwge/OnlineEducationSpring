package flyerwge.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import flyerwge.cms.entity.CrmBanner;
import flyerwge.cms.mapper.CrmBannerMapper;
import flyerwge.cms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author gw
 * @since 2021-07-22
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Cacheable(key = "'selectIndexList'",value = "banner")
    @Override
    public List<CrmBanner> selectAllBanner() {
        //根据id降序排列，显示排列之后的前两条记录
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        //list拼接sql语句
        queryWrapper.last("limit 2");

        List<CrmBanner> list = baseMapper.selectList(null);
        return list;
    }
}
