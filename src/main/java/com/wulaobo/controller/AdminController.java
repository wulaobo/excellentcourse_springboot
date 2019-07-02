package com.wulaobo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wulaobo.bean.User;
import com.wulaobo.service.AdminService;
import com.wulaobo.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    //管理员登录
    @PostMapping(value = "/adminLogin")
    public String adminLogin(String username, String password, HttpServletRequest request) {

        password = MD5Utils.md5(password);
        User admin = adminService.adminLogin(username,password);

        if(admin!=null) {
            request.getSession().setAttribute("admin",admin);
            //登录成功
            return "admin/adminHome";
        }
//        model.addAttribute("","");
        return "redirect:/admin";
    }

    //用户的模糊查询
    @GetMapping(value = "/selectByName")
    public String selectByName(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                               ModelMap model,String username) {
        PageHelper.startPage(pageNum,5);
        List<User> list = adminService.selectByName(username);

        PageInfo pageInfo = new PageInfo(list);

        model.addAttribute("userList",pageInfo);
        return "admin/user/allUser";
    }

    //根据id删除用户
    @GetMapping(value = "/deleteUserById")
    public String deleteUserById(Integer id) {

        boolean result = adminService.deleteUserById(id);

        if(result) {
            return "forward:/getAllUser";
        }

        return "failed";
    }

    @GetMapping(value = "/changeStateById")
    public String changeStateById(Integer id) {

        User user = adminService.getUserById(id);
        if(user.getState()==1){
            user.setState(2);
        }else{
            user.setState(1);
        }
        boolean result = adminService.updateStateByUser(user);

        if(result) {
            return "forward:/getAllUser";
        }

        return "failed";
    }

    @GetMapping(value = "/changeRoleById")
    public String changeRoleById(Integer id) {
        User user = adminService.getUserById(id);

        if(user.getRoleId()==1){
            user.setRoleId(2);
        }else{
            user.setRoleId(1);
        }

        boolean result = adminService.changeRoleByUser(user);
        if(result) {
            return "forward:/getAllUser";
        }

        return "failed";
    }


    //查询所有用户
    @RequestMapping(value = "/getAllUser")
    public String getAllUser(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                             ModelMap model) {

        PageHelper.startPage(pageNum,5);
        List<User> list = adminService.getAllUser();

        PageInfo pageInfo = new PageInfo(list);

        model.addAttribute("userList",pageInfo);

        return "admin/user/allUser";
    }


    //管理员退出登录
    @GetMapping(value = "/adminLogout")
    public String adminLogout(HttpServletRequest request) {

        request.getSession().invalidate();
            return "redirect:/getAllNews";
    }

    //管理员修改密码
    @RequestMapping(value = "/updateAdminPassword")
    public String updateAdminPassword(User admin,ModelMap model){
        String password = MD5Utils.md5(admin.getPassword());
        admin.setPassword(password);
        boolean result = adminService.updateAdminPassword(admin);

        if(result) {
            model.addAttribute("msg","修改密码成功，请重新登录");
            return "redirect:/admin";
        }
        return "failed";
    }


}
