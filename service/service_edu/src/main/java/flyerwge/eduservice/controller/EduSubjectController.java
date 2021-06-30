package flyerwge.eduservice.controller;


import flyerwge.commonutils.Res;
import flyerwge.eduservice.entity.subject.OneSubject;
import flyerwge.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author gw
 * @since 2021-06-16
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/edu-subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    //添加课程分类
    @PostMapping("addSubject")
    public Res addSubject(MultipartFile file){
        eduSubjectService.saveObject(file,eduSubjectService);
        return Res.succeed();
    }

    //获取所有课程分类
    @GetMapping("getAllSubject")
    public Res getAllSubject(){
        List<OneSubject> list = eduSubjectService.getAllSubject();
        return Res.succeed().data("list",list);
    }
}

