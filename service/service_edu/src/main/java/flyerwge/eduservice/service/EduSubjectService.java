package flyerwge.eduservice.service;

import flyerwge.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import flyerwge.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author gw
 * @since 2021-06-16
 */
public interface EduSubjectService extends IService<EduSubject> {
    void saveObject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getAllSubject();

}
