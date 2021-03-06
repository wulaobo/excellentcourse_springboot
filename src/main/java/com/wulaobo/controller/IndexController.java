package com.wulaobo.controller;

import com.wulaobo.bean.User;
import com.wulaobo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index() {
        return "forward:/getAllNews";
    }

    //由于springboot不支持通过a标签来直接访问html,所以通过这种方式来找到登陆页面
    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @GetMapping(value = "/toAbout")
    public String toAbout() {
        return "about";
    }


    @GetMapping(value = "/toUserHome")
    public String toUserHome() {
        return "frontPage/home";
    }

    @GetMapping(value = "/toUpdateUser")
    public String toUpdateUser(){
        return "frontPage/update";
    }

    @GetMapping(value = "/toAddTopicPage")
    public String addTopic() {
        return "frontPage/addTopic";
    }

    @GetMapping(value = "/toUserUploadPage")
    public String toUserUploadPage(@RequestParam("id") Integer id){
        User user =  userService.getUserById(id);
        if(user.getIsUpload().equals(1)){
            return "frontPage/toUserUploadPage";
        }else{
            return "frontPage/noAuth";
        }

    }

    //跳转到登录页面
    @GetMapping(value="/admin")
    public String toAdminLogin() {
        return "admin/adminLogin";
    }

   @GetMapping(value = "/toUpdateAdminPasswordPage")
    public String toUpdateAdminPasswordPage(){
        return "/admin/user/updateAdminPassword";
   }

   @GetMapping(value = "/toAddNewsPage")
   public String  toAddNewsPage() {
        return "admin/news/addNews";
   }

   //跳转到文件上传页
    @GetMapping(value = "/toSourceUploadPage")
    public String toSourceUploadPage() {
        return "admin/source/sourceUpload";
    }

    //跳转到视频上传页面
    @GetMapping(value = "/toVideoUploadPage")
    public String toVideoUploadPage() {
        return "admin/video/videoUpload";
    }

    //跳转到管理员主页
    @GetMapping(value = "/toAdminHome")
    public String toAdminHome() {
        return "admin/adminHome";
    }

}
