package flyerwge.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import flyerwge.commonutils.Res;
import flyerwge.eduservice.entity.EduCourse;
import flyerwge.eduservice.entity.EduTeacher;
import flyerwge.eduservice.service.EduCourseService;
import flyerwge.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/eduservice/index-front")
public class IndexFrontController {
    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("hotTeachers")
    public Res getHotTeacher(){
        // 查询前四个名师
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");
        List<EduTeacher> teacherList = eduTeacherService.list(teacherQueryWrapper);

        return Res.succeed().data("teacherList",teacherList);
    }

    @GetMapping("hotCourses")
    public Res getHotCourse(){
        // 查询前八节热门课程
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");
        List<EduCourse> courseList = eduCourseService.list(courseQueryWrapper);

        return Res.succeed().data("courseList",courseList);
    }
}
