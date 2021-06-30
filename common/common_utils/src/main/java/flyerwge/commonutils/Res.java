package flyerwge.commonutils;

//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Res {
//    @ApiModelProperty(value = "是否成功")
    private Boolean success;

//    @ApiModelProperty(value = "返回码")
    private Integer code;

//    @ApiModelProperty(value = "返回消息")
    private String message;

//    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    //    构造器私有化
    private Res(){}

    //    成功静态方法
    public  static Res succeed(){
        Res res = new Res();
        res.setSuccess(true);
        res.setCode(ResultCode.SUCCESS);
        res.setMessage("成功");
        return res;
    }

    //    失败静态方法
    public static Res failed(){
        Res res = new Res();
        res.setSuccess(false);
        res.setCode(ResultCode.ERROR);
        res.setMessage("失败");
        return res;
    }

    public Res success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Res message(String message) {
        this.setMessage(message);
        return this;
    }

    public Res code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Res data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Res data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
