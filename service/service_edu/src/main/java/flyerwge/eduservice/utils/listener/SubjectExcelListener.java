package flyerwge.eduservice.utils.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import flyerwge.commonutils.exceptionhandler.SelfException;
import flyerwge.eduservice.entity.EduSubject;
import flyerwge.eduservice.entity.excel.SubjectData;
import flyerwge.eduservice.service.EduSubjectService;

import java.util.Map;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //不能交给spring管理，需要手动注入其他对象
    private EduSubjectService eduSubjectService;

    public SubjectExcelListener() {}
    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    //按行读取数据
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            throw new SelfException(20001,"文件数据为空");
        }

        //一级分类
        EduSubject oneSubject = this.existOneSubject(subjectData.getOneSubjectName());
        if (oneSubject == null){
            //如果数据库中没有相同一级分类，则进行添加
            oneSubject = new EduSubject();
            oneSubject.setTitle(subjectData.getOneSubjectName());
            oneSubject.setParentId("0");
            eduSubjectService.save(oneSubject);
        }

        //二级分类
        String parentId = oneSubject.getId();
        EduSubject twoSubject = this.existTwoSubject(subjectData.getTwoSubjectName(),parentId);
        if (twoSubject == null){
            //如果没有二级分类，则进行添加
            twoSubject = new EduSubject();
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            twoSubject.setParentId(parentId);
            eduSubjectService.save(twoSubject);
        }
    }

    /**
     * 判断是否为一级分类
     * @param name
     * @return
     */
    private EduSubject existOneSubject(String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = eduSubjectService.getOne(wrapper);
        return oneSubject;
    }

    /**
     * 判断是否为二级分类
     * @param name
     * @param parentId
     * @return
     */
    private EduSubject existTwoSubject(String name,String parentId){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",parentId);
        EduSubject twoSubject = eduSubjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
