package flyerwge.eduservice.controller;


import flyerwge.commonutils.Res;
import flyerwge.eduservice.entity.EduChapter;
import flyerwge.eduservice.entity.chapter.ChapterVideo;
import flyerwge.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("/eduservice/edu-chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    /**
     * 获取章节视频
     * @param courseId
     * @return
     */
    @GetMapping("getChapterVideo/{courseId}")
    public Res getChapterVideoById(@PathVariable String courseId){
        List<ChapterVideo> list = eduChapterService.getChapterVideoById(courseId);
        return Res.succeed().data("allChapterVideo",list);
    }

    /**
     * 添加课程章节
     * @param eduChapter
     * @return
     */
    @PostMapping("addChapter")
    public Res addChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return Res.succeed();
    }

    /**
     * 根据id查询课程章节信息
     * @param chapterId
     * @return
     */
    @GetMapping("getChapterInfoById/{chapterId}")
    public Res getChapterInfoById(@PathVariable String chapterId){
        EduChapter chapterInfo = eduChapterService.getById(chapterId);
        return Res.succeed().data("chapterInfo",chapterInfo);
    }

    /**
     * 修改课程章节
     * @param eduChapter
     * @return
     */
    @PostMapping("updateChapter")
    public Res updateChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return Res.succeed();
    }

    /**
     * 根据id删除课程章节
     * @param chapterId
     * @return
     */
    @DeleteMapping("deleteChapter/{chapterId}")
    public Res deleteChapterById(@PathVariable String chapterId){
        boolean result = eduChapterService.deleteChapterById(chapterId);
        return result ? Res.succeed() : Res.failed();
    }

}

