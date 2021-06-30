package flyerwge.eduservice.entity.query;

//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TeacherQuery {
//    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;

//    @ApiModelProperty(value = "讲师头衔")
    private Integer level;

//    @ApiModelProperty(value = "查询开始时间")
    private String begin;

//    @ApiModelProperty(value = "查询结束时间")
    private String end;

}
