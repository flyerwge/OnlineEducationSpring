package flyerwge.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import flyerwge.eduservice.entity.EduSubject;
import flyerwge.eduservice.entity.excel.SubjectData;
import flyerwge.eduservice.entity.subject.OneSubject;
import flyerwge.eduservice.entity.subject.TwoSubject;
import flyerwge.eduservice.mapper.EduSubjectMapper;
import flyerwge.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import flyerwge.eduservice.utils.listener.SubjectExcelListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author gw
 * @since 2021-06-16
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveObject(MultipartFile file,EduSubjectService eduSubjectService) {
        //通过输入流向excel写文件
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllSubject(){
        //查询所有一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        //查询所有二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperOne.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //封装所有一级分类
        List<OneSubject> finalList = new ArrayList<>();
        for (int i = 0; i < oneSubjectList.size(); i++){
            EduSubject eduSubject = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);

            //封装所有二级分类
            ArrayList<TwoSubject> twoFinalList = new ArrayList<>();
            for(int j = 0; j < twoSubjectList.size(); j++){
                EduSubject eduSubjectTwo = twoSubjectList.get(j);
                if(eduSubject.getId().equals(eduSubjectTwo.getParentId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduSubjectTwo,twoSubject);
                    twoFinalList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalList);
            finalList.add(oneSubject);
        }
        return finalList;
    }
}
