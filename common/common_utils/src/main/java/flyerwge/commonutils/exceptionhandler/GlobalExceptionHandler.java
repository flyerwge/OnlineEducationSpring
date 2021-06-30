package flyerwge.commonutils.exceptionhandler;

import flyerwge.commonutils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //    全部异常执行方法
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Res error(Exception e){
        e.printStackTrace();
        return Res.failed().message("执行了全局异常处理");
    }

    //    指定异常执行方法
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Res error(ArithmeticException e){
        e.printStackTrace();
        return Res.failed().message("方法执行ArithmeticException异常！");
    }

    //    自定义的异常处理
    @ExceptionHandler(SelfException.class)
    @ResponseBody
    public Res error(SelfException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Res.failed().code(e.getCode()).message(e.getMsg());
    }
}
