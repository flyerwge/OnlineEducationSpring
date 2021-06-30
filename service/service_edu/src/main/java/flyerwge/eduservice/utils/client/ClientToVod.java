package flyerwge.eduservice.utils.client;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import flyerwge.commonutils.Res;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
//@FeignClient(name = "service-vod",fallback = ClientToVodImp.class)
//Sentinel熔断器注解实现
@SentinelResource(value = "service-vod",fallback = "ClientToVodImp.class")
public interface ClientToVod {

    @DeleteMapping(value = "/vodService/video/removeVod/{id}")
    public Res removeVodById(@PathVariable("videoId") String videoId);

    @DeleteMapping(value = "/vodService/video/deleteBatch")
    public Res deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
