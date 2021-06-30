package flyerwge.eduservice.controller;


import flyerwge.commonutils.Res;
import flyerwge.eduservice.entity.EduCourse;
import flyerwge.eduservice.entity.query.CourseInfoQuery;
import flyerwge.eduservice.entity.subject.CourseNum;
import flyerwge.eduservice.mapper.EduCourseMapper;
import flyerwge.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author gw
 * @since 2021-06-20
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    /**
     * 添加课程信息
     * @param courseInfoQuery
     * @return
     */
    @PostMapping("addCourseInfo")
    public Res addCourseInfo(@RequestBody CourseInfoQuery courseInfoQuery){
        String id = eduCourseService.saveCourseInfo(courseInfoQuery);
        return Res.succeed().data("courseId",id);
    }

    /**
     * 根据id删除课程
     * @param courseId
     * @return
     */
    @DeleteMapping("removeCourse/{courseId}")
    public Res removeCourseById(@PathVariable String courseId) {
        eduCourseService.removeCourse(courseId);
        return Res.succeed();
    }

    /**
     * 查询热门课程
     * @param nums:返回的课程数目
     * @return
     */
    @GetMapping("getTopCourse/{nums}")
    public Res selectTopByViewCount(@PathVariable Integer nums){
        List<EduCourse> topCourse = eduCourseService.selectTopCourse(nums);
        return Res.succeed().data("topCourse",topCourse);
    }

    /**
     * 获得不同分类的课程数
     * @return :Map中 key:课程分类，value:课程数目
     */
    @GetMapping("getCourseNum")
    public Res getCourseNum(){
        List<CourseNum> courseOfSubjectNum = eduCourseService.getCourseOfSubjectNum();
        return Res.succeed().data("courseNum",courseOfSubjectNum);
    }
}

