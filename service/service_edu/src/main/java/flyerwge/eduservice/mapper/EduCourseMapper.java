package flyerwge.eduservice.mapper;

import flyerwge.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author gw
 * @since 2021-06-20
 */
@Repository
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public List<EduCourse> getTopCourse(@Param("nums") Integer nums);
    public List<EduCourse> getAllCourse();

}
