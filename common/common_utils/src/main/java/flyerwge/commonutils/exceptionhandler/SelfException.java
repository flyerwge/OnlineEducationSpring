package flyerwge.commonutils.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //有参构造
@NoArgsConstructor //无参构造
public class SelfException extends RuntimeException{

    private Integer code; //状态码

    private String msg; //输出消息
}
