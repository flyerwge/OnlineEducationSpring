package flyerwge.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import flyerwge.cms.entity.CrmBanner;
import flyerwge.cms.service.CrmBannerService;
import flyerwge.commonutils.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 后台banner管理接口
 * </p>
 *
 * @author gw
 * @since 2021-07-22
 */
@RestController
@RequestMapping("/cms/crm-banner-admin")
@CrossOrigin
public class CrmBannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;

    /**
     * 分页查询
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("pageBanner/{page}/{limit}")
    public Res getPageBanner(@PathVariable long page, long limit){
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        crmBannerService.page(pageBanner,null);
        return Res.succeed().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    @PostMapping("addBanner")
    public Res addBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return Res.succeed();
    }

    //根据id获取Banner
    @GetMapping("getBanner/{id}")
    public Res getBannerById(@PathVariable String id) {
        CrmBanner banner = crmBannerService.getById(id);
        return Res.succeed().data("item", banner);
    }

    //修改Banner
    @PutMapping("update")
    public Res updateById(@RequestBody CrmBanner banner) {
        crmBannerService.updateById(banner);
        return Res.succeed();
    }

    //根据id删除Banner
    @DeleteMapping("remove/{id}")
    public Res remove(@PathVariable String id) {
        crmBannerService.removeById(id);
        return Res.succeed();
    }
}

