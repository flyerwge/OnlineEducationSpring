package flyerwge.eduservice.service;

import flyerwge.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import flyerwge.eduservice.entity.chapter.ChapterVideo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author gw
 * @since 2021-06-20
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVideo> getChapterVideoById(String courseId);

    boolean deleteChapterById(String chapterId);
}
