package flyerwge.eduservice.service.impl;

import flyerwge.eduservice.entity.EduChapter;
import flyerwge.eduservice.entity.chapter.ChapterVideo;
import flyerwge.eduservice.mapper.EduChapterMapper;
import flyerwge.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author gw
 * @since 2021-06-20
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Override
    public List<ChapterVideo> getChapterVideoById(String courseId) {
        return null;
    }

    @Override
    public boolean deleteChapterById(String chapterId) {
        return false;
    }
}
