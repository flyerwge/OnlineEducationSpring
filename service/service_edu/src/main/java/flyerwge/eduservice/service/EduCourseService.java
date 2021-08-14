package flyerwge.eduservice.service;

import flyerwge.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import flyerwge.eduservice.entity.query.CourseInfoQuery;
import flyerwge.eduservice.entity.query.CoursePublishQuery;
import flyerwge.eduservice.entity.subject.CourseNum;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author gw
 * @since 2021-06-20
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoQuery courseInfoQuery);

    void removeCourse(String courseId);

    List<EduCourse> selectTopCourse(Integer nums);

    List<CourseNum> getCourseOfSubjectNum();

    void updateCourseInfo(CourseInfoQuery courseInfoQuery);

    CoursePublishQuery getPublishCourseInfoById(String id);
}
