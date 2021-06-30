package flyerwge.vod.controller;

import flyerwge.commonutils.Res;
import flyerwge.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vodService/video")
public class VodController {

    @Autowired
    private VodService vodService;

    /**
     * 上传视频
     * @param file
     * @return
     */
    @PostMapping("uploadVideo")
    public Res uploadVideo(MultipartFile file){
//        postman测试时key == file 才成功上传，暂且不知道为什么  TODO
        String videoId = vodService.uploadVIdeo(file);
        return Res.succeed().data("videoId",videoId);
    }

    /**
     * 删除视频
     * @param videoId
     * @return
     */
    @DeleteMapping("removeVod/{id}")
    public Res removeVodById(@PathVariable String videoId){
        System.out.println(videoId);
        vodService.removeVideoById(videoId);
        return Res.succeed();
    }

    /**
     * 删除多个阿里云视频
     * @param videoIdList
     * @return
     */
    @DeleteMapping("deleteBatch")
    public Res deleteBatch(@RequestParam List<String> videoIdList) {
        vodService.removeAllVideo(videoIdList);
        return Res.succeed();
    }
}
