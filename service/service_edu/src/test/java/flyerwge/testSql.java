package flyerwge;


import flyerwge.eduservice.mapper.EduTeacherMapper;
import flyerwge.eduservice.service.EduTeacherService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class testSql {
//    @Autowired
//    private EduTeacherService eduTeacherService;

    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    @Test
    public void deleteById(){
        String id = "1";
        System.out.println("delete:"+eduTeacherMapper);
        int delete = eduTeacherMapper.deleteById(id);
        System.out.println("delete:"+delete);
    }
}
