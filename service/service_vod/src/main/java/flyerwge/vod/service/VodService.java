package flyerwge.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService{
    String uploadVIdeo(MultipartFile file);

    void removeVideoById(String videoId);

    void removeAllVideo(List videoIdList);
}
