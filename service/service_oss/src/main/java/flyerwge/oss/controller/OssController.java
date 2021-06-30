package flyerwge.oss.controller;

import flyerwge.commonutils.Res;
import flyerwge.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/eduoss/file")
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 上传头像方法
     * @param file
     * @return
     */
    @PostMapping("avatar")
    public Res uploadOssAvatar(@RequestParam("uploadAvatar") MultipartFile file){
        //获取上传文件，MultipartFile
        //返回上传oss的url
        System.out.println("file:"+file);
        String url = ossService.uploadFileAvatar(file);
        return Res.succeed().data("imgUrl",url);
    }

    @PostMapping("cover")
    public Res uploadOssCover(MultipartFile file){
        //获取上传文件，MultipartFile
        //返回上传oss的url
        System.out.println("file:"+file);
        String url = ossService.uploadFileAvatar(file);
        return Res.succeed().data("imgUrl",url);
    }
}
