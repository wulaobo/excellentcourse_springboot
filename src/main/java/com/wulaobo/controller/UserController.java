package com.wulaobo.controller;

import com.wulaobo.bean.User;
import com.wulaobo.service.UserService;
import com.wulaobo.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    //用户登录
    @PostMapping("/login")
    public String login(String username, String password, HttpServletRequest request,ModelMap model) {

        password= MD5Utils.md5(password);
        User user = userService.login(username,password);

        if(user!=null){
            //登陆成功
            request.getSession().setAttribute("user",user);
            return "forward:/getAllNews";
        }else{
            //登陆失败
//            request.getSession().setAttribute("errorLogin","用户名或密码失败！");
            model.addAttribute("errorLogin","用户名或密码失败！");
            return "login";
        }


    }


    //用户注册
    @PostMapping(value = "/register")
    public String register(User user,ModelMap model) {
        User user1 = userService.getUserByUserName(user.getUsername());
        if(user1==null){
            String password = MD5Utils.md5(user.getPassword());
            user.setPassword(password);
            userService.save(user);
            model.addAttribute("success","注册成功,请登录！");
            return "login";
        }else{
            model.addAttribute("errorRegister","名称不能重复");
            return "register";
        }

    }


    //用户退出
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "forward:/getAllNews";
    }


    //修改用户
    @RequestMapping(value = "/updateUser")
    public String updateUser(@RequestParam(value = "username") String userName, @RequestParam(value = "password") String password,
                             @RequestParam(value = "email") String email, ModelMap model) {
        User user = userService.getUserByUserName(userName);
        if(user!=null) {
            String md5Pwd = MD5Utils.md5(password);
//            user.setPassword(md5Pwd);
//            userService.save(user);
            userService.updatePasswordByUserName(md5Pwd,userName);
            model.addAttribute("updateSuccess","修改成功，请重新登录！");
        }
        return "redirect:/toLogin";
    }






}
