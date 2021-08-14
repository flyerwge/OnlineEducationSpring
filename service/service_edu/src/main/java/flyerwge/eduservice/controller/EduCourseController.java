package flyerwge.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import flyerwge.commonutils.Res;
import flyerwge.eduservice.entity.EduCourse;
import flyerwge.eduservice.entity.query.CourseInfoQuery;
import flyerwge.eduservice.entity.query.CoursePublishQuery;
import flyerwge.eduservice.entity.query.CourseQuery;
import flyerwge.eduservice.entity.subject.CourseNum;
import flyerwge.eduservice.mapper.EduCourseMapper;
import flyerwge.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @GetMapping("getCourseList")
    public Res getCourseList(){
        List<EduCourse> list = eduCourseService.list(null);
        return Res.succeed().data("list",list);
    }

    /**
     * 修改课程信息
     * @param courseInfoQuery
     * @return
     */
    @PostMapping("updateCourseInfo")
    public Res updateCourseInfo(@RequestBody CourseInfoQuery courseInfoQuery){
        eduCourseService.updateCourseInfo(courseInfoQuery);
        return Res.succeed();
    }

    /**
     * 根据id查询课程发布信息
     * @param id
     * @return
     */
    @GetMapping("getPublishCourseInfoById/{id}")
    public Res getPublishCourseInfo(@PathVariable String id){
        //@PathVariable:接收请求位置中占位符的值
        CoursePublishQuery coursePublishQuery = eduCourseService.getPublishCourseInfoById(id);
        return Res.succeed().data("publishCourseInfo",coursePublishQuery);
    }

    /**
     * 根据id发布课程
     * @param id
     * @return
     */
    @PostMapping("publishCourseById/{id}")
    public Res publishCourseById(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return Res.succeed();
    }

    /**
     * 根据id删除课程
     * @param courseId
     * @return
     */
    @DeleteMapping("deleteCourseById/{courseId}")
    public Res deleteCourseById(@PathVariable String courseId){
        eduCourseService.removeCourse(courseId);
        return Res.succeed();
    }

    /**
     * 分页查询
     * @param current
     * @param limit
     * @return
     */
    @GetMapping("getPageCourse/{current}{limit}")
    public Res getPageCourseById(@PathVariable Long current,
                                 @PathVariable Long limit){
        Page<EduCourse> pageCourse = new Page<>(current,limit);
        IPage<EduCourse> page = eduCourseService.page(pageCourse,null);
        List<EduCourse> records = page.getRecords();
        long total = page.getTotal();
        return Res.succeed().data("total",total).data("records",records);
    }

    /**
     * 条件查询并分页
     * @param current
     * @param limit
     * @param courseQuery
     * @return
     */
    @PostMapping("pageCourseCondition/{current}{limit}")
    public Res pageCourseCondition(@PathVariable Long current,
                                   @PathVariable long limit,
                                   @RequestBody(required = false) CourseQuery courseQuery){
        Page<EduCourse> pageCourseCondition = new Page<>(current,limit);

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        String status = courseQuery.getStatus();
        String title = courseQuery.getTitle();
        if(!title.isEmpty()){
            wrapper.like("title",title);
        }
        if(!status.isEmpty()){
            wrapper.eq("status",status);
        }

        wrapper.orderByDesc("gmt_create");

//        调用方法，实现分页查询
        IPage<EduCourse> page = eduCourseService.page(pageCourseCondition,wrapper);
        long total = page.getTotal();
        List<EduCourse> records = page.getRecords();
        HashMap<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        return Res.succeed().data(map);
    }
}

