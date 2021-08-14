package flyerwge.cms.controller;


import flyerwge.cms.entity.CrmBanner;
import flyerwge.cms.service.CrmBannerService;
import flyerwge.commonutils.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前台banner显示
 * </p>
 *
 * @author gw
 * @since 2021-07-22
 */
@RestController
@RequestMapping("/cms/crm-banner-front")
@CrossOrigin
public class CrmBannerFrontController {

    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("getAllBanner")
    public Res getAllBanner(){
        List<CrmBanner> list = crmBannerService.selectAllBanner();
        return Res.succeed().data("list",list);
    }
}

