package flyerwge.eduservice.controller;

import flyerwge.commonutils.Res;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {
    /**
     * 登录
     * @return
     */
    @PostMapping("login")
    public Res Login(){
        return Res.succeed().data("token","admin");
    }

    /**
     * 获取信息
     * @return
     */
    @GetMapping("info")
    public Res Info(){
        return Res.succeed().data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    /**
     * 退出登录
     * @return
     */
    @PostMapping("logout")
    public Res Logout(){
        return Res.succeed();
    }
}
