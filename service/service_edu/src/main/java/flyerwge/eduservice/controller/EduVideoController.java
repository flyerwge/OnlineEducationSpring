package flyerwge.eduservice.controller;


import flyerwge.commonutils.Res;
import flyerwge.commonutils.exceptionhandler.SelfException;
import flyerwge.eduservice.entity.EduVideo;
import flyerwge.eduservice.service.EduVideoService;
import flyerwge.eduservice.utils.client.ClientToVod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author gw
 * @since 2021-06-20
 */
@RestController
@RequestMapping("/eduservice/edu-video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private ClientToVod clientToVod;

    /**
     * 添加小节
     * @param eduVideo
     * @return
     */
    @PostMapping("addVideo")
    public Res addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return Res.succeed();
    }

    /**
     * 删除小节，同时将视频删除
     * @param id： 小节id
     * @return
     */
    @DeleteMapping("deleteVideo/{id}")
    public Res deleteVideoById(@PathVariable String id){
        EduVideo eduVideoById = eduVideoService.getById(id);
        String videoSourceId = eduVideoById.getVideoSourceId();

        //删除视频
        if(!videoSourceId.isEmpty()){
            //远程调用vod删除视频
            Res result = clientToVod.removeVodById(videoSourceId);
            if (result.getCode() == 20000){
                throw new SelfException(20000,"删除视频,rong");
            }
            clientToVod.removeVodById(videoSourceId);
        }

        //删除小节
        eduVideoService.removeById(id);
        return Res.succeed();
    }

}

