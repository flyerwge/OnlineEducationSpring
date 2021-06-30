package flyerwge.eduservice.service;

import flyerwge.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author gw
 * @since 2021-05-30
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Integer> getLevelTeacherNum();
}
