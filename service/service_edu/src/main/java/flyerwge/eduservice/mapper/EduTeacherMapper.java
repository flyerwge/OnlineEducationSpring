package flyerwge.eduservice.mapper;

import flyerwge.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author gw
 * @since 2021-05-30
 */
@Repository
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {
    public Integer updateById(@Param("id") String id);
    public Integer selectLevelTeacherNum(@Param("level") int level);

}
