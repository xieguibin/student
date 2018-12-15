package com.tt.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @作者：解贵斌
 * @时间：2018/11/15 19:20
 * @描述：页面控制
 */
@Controller
public class UserController {
    @GetMapping("/file_put")
    public String file_put() {
        return "file_put";
    }
    /**
     * @作者：matao
     * @时间：2018/11/15 0015 下午 2:37
     * @方法名：fileLoad
     * @描述：该方法，可以实现文件上传。注意事项如下
     * @温馨提示：
     *  1.请将页面表单提交方式改为post
     *  2.加入enctype设置为multipart/form-data
     *  3.参数@RequestParam("filePhoto")，要和前端文件控件相同
     */
    @PostMapping(value = "/fileLoad")
    public String fileLoad(HttpServletRequest request, @RequestParam("filePhoto") MultipartFile file) throws IOException {
        if (!file.isEmpty()){
            //1.path：为上传的目录，参数可以改为项目要求的(可改)
            String path = request.getServletContext().getRealPath("/upload/");
            //2.可以打印出，自己去检测是否正确(不可改)
            System.out.println(path);
            //3.查看原文件的名称(不可改)
            String fileName = file.getOriginalFilename();
            //4.根据目录和文件名，进行判断和创建(不可改)
            File filePath = new File(path,fileName);
            if (!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdirs();
            }
            //5.将前端接收的file文件，传入刚创建的目录文件中(不可改)
            file.transferTo(new File(path+ File.separator+fileName));
            //6.上传成功跳转的页面(可改)
            return "ok";
        }
        //7.上传失败跳转的页面(可改)
        return "no";
    }


}
