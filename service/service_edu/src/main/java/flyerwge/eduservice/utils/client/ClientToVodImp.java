package flyerwge.eduservice.utils.client;

import flyerwge.commonutils.Res;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientToVodImp implements ClientToVod{
    @Override
    public Res removeVodById(String videoId) {
        return Res.failed().message("删除视频出错！");
    }

    @Override
    public Res deleteBatch(List<String> videoIdList) {
        return Res.failed().message("删除多个视频出错！");
    }
}
