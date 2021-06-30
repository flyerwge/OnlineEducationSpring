package flyerwge.eduservice.service.impl;

import flyerwge.eduservice.entity.EduTeacher;
import flyerwge.eduservice.mapper.EduTeacherMapper;
import flyerwge.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author gw
 * @since 2021-05-30
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    @Override
    public Map<String, Integer> getLevelTeacherNum() {
        Map<String,Integer> levelTeacherNum = new HashMap<>();
        levelTeacherNum.put("levelOneNum",eduTeacherMapper.selectLevelTeacherNum(1));
        levelTeacherNum.put("levelTwoNum",eduTeacherMapper.selectLevelTeacherNum(2));
        return levelTeacherNum;
    }


}
