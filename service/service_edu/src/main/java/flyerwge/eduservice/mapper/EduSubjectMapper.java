package flyerwge.eduservice.mapper;

import flyerwge.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author gw
 * @since 2021-06-16
 */
@Repository
public interface EduSubjectMapper extends BaseMapper<EduSubject> {

    public String selectSubjectTitleById(@Param("id") String id);

}
