package flyerwge.eduservice.entity.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseQuery implements Serializable {
    private static final long serialVersionUID = 1l;

    private String title;

    private String status;
}
