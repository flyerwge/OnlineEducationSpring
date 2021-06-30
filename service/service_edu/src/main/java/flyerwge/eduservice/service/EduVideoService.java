package flyerwge.eduservice.service;

import flyerwge.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author gw
 * @since 2021-06-20
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeByCourseId(String courseId);
}
