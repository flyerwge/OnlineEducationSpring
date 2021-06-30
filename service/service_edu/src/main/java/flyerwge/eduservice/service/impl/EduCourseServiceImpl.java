package flyerwge.eduservice.service.impl;

import flyerwge.commonutils.exceptionhandler.SelfException;
import flyerwge.eduservice.entity.EduCourse;
import flyerwge.eduservice.entity.EduCourseDescription;
import flyerwge.eduservice.entity.EduSubject;
import flyerwge.eduservice.entity.query.CourseInfoQuery;
import flyerwge.eduservice.entity.subject.CourseNum;
import flyerwge.eduservice.mapper.EduCourseMapper;
import flyerwge.eduservice.mapper.EduSubjectMapper;
import flyerwge.eduservice.service.EduCourseDescriptionService;
import flyerwge.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import flyerwge.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author gw
 * @since 2021-06-20
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Autowired
    private EduSubjectMapper eduSubjectMapper;

    @Override
    public String saveCourseInfo(CourseInfoQuery courseInfoQuery) {
        //向课程表添加课程信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoQuery, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert <= 0){
            throw new SelfException(20001,"添加课程失败");
        }

        //获取保存后的id，与课程描述建立关系
        String CourseId = eduCourse.getId();
        //向课程简介添加课程简介
        EduCourseDescription description = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoQuery, description);
        description.setId(CourseId);
        eduCourseDescriptionService.save(description);

        return CourseId;
    }

    @Override
    public void removeCourse(String courseId) {
        //1. 根据课程id删除视频
        eduVideoService.removeByCourseId(courseId);
        //2. 根据课程id删除章节
        //3. 根据课程id删除课程描述
        //4. 根据课程id删除课程本身，须放在最后一步

    }

    @Override
    public List<EduCourse> selectTopCourse(Integer nums) {
        List<EduCourse> topCourse = eduCourseMapper.getTopCourse(nums);
        return topCourse;
    }

    /**
     * 获取不同分类所含课程数目
     * @return :List<CourseNum>
     */
    @Override
    public List<CourseNum> getCourseOfSubjectNum() {
        Map<String,Integer> resMap = new HashMap<>();
        List<EduCourse> allCourse = eduCourseMapper.getAllCourse();
        List<CourseNum> courseNum = new ArrayList<>();

        //遍历所有课程，统计课程数目
        for(EduCourse course : allCourse){
            String subjectTitle = eduSubjectMapper.selectSubjectTitleById(course.getSubjectParentId());
            if(subjectTitle != null){
                resMap.put(subjectTitle,resMap.getOrDefault(subjectTitle,0)+1);
            }
        }

        //遍历哈希表，将表中内容传递
        for(Map.Entry<String,Integer> entry : resMap.entrySet()){
            CourseNum course = new CourseNum();
            course.setName(entry.getKey());
            course.setValue(entry.getValue());
            courseNum.add(course);
        }
        return courseNum;
    }
}
