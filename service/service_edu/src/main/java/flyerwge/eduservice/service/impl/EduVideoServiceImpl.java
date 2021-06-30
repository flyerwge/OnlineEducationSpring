package flyerwge.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import flyerwge.commonutils.Res;
import flyerwge.commonutils.exceptionhandler.SelfException;
import flyerwge.eduservice.entity.EduVideo;
import flyerwge.eduservice.mapper.EduVideoMapper;
import flyerwge.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import flyerwge.eduservice.utils.client.ClientToVod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author gw
 * @since 2021-06-20
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private ClientToVod clientToVod;

    @Override
    public void removeByCourseId(String courseId) {
        //根据课程id查出所有视频的id
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(wrapper);

        //封装video_source_id  1,2,3
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < eduVideos.size(); i++) {
            EduVideo eduVideo = eduVideos.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if (!videoSourceId.isEmpty()) {
                list.add(videoSourceId);
            }
        }

        //list不为空
        if (list.size() > 0) {
            //删除小节里的所有视频
            Res result = clientToVod.deleteBatch(list);
            if (result.getCode() == 20001) {
                throw new SelfException(20001, "删除视频失败，熔断器...");
            }
        }
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        baseMapper.delete(queryWrapper);

    }
}
