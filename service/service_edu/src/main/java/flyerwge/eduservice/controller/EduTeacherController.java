package flyerwge.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import flyerwge.commonutils.Res;
import flyerwge.commonutils.exceptionhandler.SelfException;
import flyerwge.eduservice.entity.EduTeacher;
import flyerwge.eduservice.entity.query.TeacherQuery;
import flyerwge.eduservice.mapper.EduTeacherMapper;
import flyerwge.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author gw
 * @since 2021-05-30
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    /**
     * 查询讲师表中的数据
     * rest风格
     * @return:所有讲师列表
     */
    @GetMapping("findAll")
    public Res findAllTeacher(){
//        调用service进行增删查改
        List<EduTeacher> list = eduTeacherService.list(null);
        return Res.succeed().data("allTeacher",list);
    }

    /**
     * 讲师分页控制
     * @param current：当前页
     * @param limit：每页记录数
     * @return:分页结果
     */
    @GetMapping("eduTeacher/{current}/{limit}")
    public Res pageListTeacher(@PathVariable long current,@PathVariable long limit){
//        创建page对象
        Page<EduTeacher> eduTeacherPage = new Page<>(current,limit);
//        调用方法实现分页
//        调用方法时，底层封装，把分页所有数据封装到eduTeacherPage
        eduTeacherService.page(eduTeacherPage,null);

        long total = eduTeacherPage.getTotal(); //总记录数
        List<EduTeacher> records = eduTeacherPage.getRecords(); //数据list集合
        Map pageRes = new HashMap();
        pageRes.put("total",total);
        pageRes.put("rows",records);

        return Res.succeed().data(pageRes);
    }

    /**
     * 条件查询带分页方法
     * @param current：当前页
     * @param limit：每页记录数
     * @param teacherQuery：查询条件，对象封装
     * @return：条件查询结果
     */
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Res pageTeacherCondition(@PathVariable long current,
                                    @PathVariable long limit,
                                    @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> eduTeacherPageCondition = new Page<>(current,limit);
//        构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
//        调用方法实现条件查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String beginTime = teacherQuery.getBegin();
        String endTime = teacherQuery.getEnd();
//        判断条件是否为空
        if(name != null){
            wrapper.like("name",name);
        }
        if (level != null){
            wrapper.eq("level",level);
        }
        if (beginTime != null){
            wrapper.ge("gmt_create",beginTime);
        }
        if (endTime != null){
            wrapper.le("gmt_create",endTime);
        }

//        根据修改时间进行排序
        wrapper.orderByDesc("gmt_modified");
        eduTeacherService.page(eduTeacherPageCondition,wrapper);

        long total = eduTeacherPageCondition.getTotal(); //总记录数
        List<EduTeacher> records = eduTeacherPageCondition.getRecords(); //数据list集合
        Map pageResByCondition = new HashMap();
        pageResByCondition.put("total",total);
        pageResByCondition.put("rows",records);
        return Res.succeed().data(pageResByCondition);
    }

    /**
     * 逻辑删除讲师
     * @param id：通过路径传入id值
     * @return：统一格式的JSON数据
     */
    @DeleteMapping("delete/{id}")
    public Res removeTeacher(@PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag){
            return Res.succeed();
        }else {
            return Res.failed();
        }
    }

    /**
     * 添加讲师
     * @param eduTeacher：讲师json对象
     * @return
     */
    @PostMapping("addTeacher")
    public Res addTeacher(@RequestBody EduTeacher eduTeacher){
        System.out.println("edu:"+eduTeacher);
        boolean isAdded = eduTeacherService.save(eduTeacher);
        if (isAdded){
            return Res.succeed();
        }else{
            return Res.failed();
        }
    }

    /**
     * 根据讲师id查询讲师数据
     * @param id：前端传入id值
     * @return
     */
    @GetMapping("getTeacherInfoById/{id}")
    public Res getTeacherInfoById(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return Res.succeed().data("eduTeacher",eduTeacher);
    }

    /**
     * 修改讲师
     * @param eduTeacher：前端传入修改参数
     * @return
     */
    @PostMapping("updateTeacherInfo")
    public Res updateTeacherInfo(@RequestBody EduTeacher eduTeacher){
        boolean isUpdated = eduTeacherService.updateById(eduTeacher);
        if (isUpdated){
            return Res.succeed();
        }else{
            return Res.failed();
        }
    }

    /**
     * 将不同级别的讲师数目传给前端
     * @return
     */
    @GetMapping("getLevelTeachers")
    public Res getLevelTeachers(){
        Map<String,Integer> levelTeacherNum = eduTeacherService.getLevelTeacherNum();
        return Res.succeed().data("levelTeacherNum",levelTeacherNum);
    }

}

