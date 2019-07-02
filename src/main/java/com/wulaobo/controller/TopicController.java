package com.wulaobo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wulaobo.bean.Topic;
import com.wulaobo.bean.User;
import com.wulaobo.service.AnswerService;
import com.wulaobo.service.TopicService;
import com.wulaobo.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private AnswerService answerService;


    @PostMapping(value = "/addTopic")
    public String addTopic(Topic topic, HttpServletRequest request) {
        String currentTime = DateUtil.getNowTime();
        topic.setPubtime(currentTime);
        User loginUser = (User) request.getSession().getAttribute("user");
        String editUser = loginUser.getUsername();
        topic.setEdituser(editUser);
        boolean result = topicService.addTopic(topic);
        if (result) {
            return "frontPage/topicSuccess";
        }
        return "frontPage/topicFailed";
    }

    @RequestMapping(value = "/getAllTopic")
    public String getAllTopic(String edituser, @RequestParam(required = false, value = "pageNum", defaultValue = "1") Integer pageNum,
                              ModelMap model) {
        PageHelper.startPage(pageNum, 5);
        ArrayList<Topic> list = topicService.getAllTopic(edituser);


        PageInfo pageInfo = new PageInfo(list,5);
        model.addAttribute("topicList", pageInfo);

        return "frontPage/topicList";
    }


    @GetMapping(value = "/getTopicById")
    public String getTopicById(Integer id, ModelMap model) {
        Topic topic = topicService.getTopicById(id);
        model.addAttribute("topic", topic);
        return "frontPage/editTopic";
    }

    @PostMapping(value = "/editTopic")
    public String editTopic(Topic topic, ModelMap model) {

        topic.setPubtime(DateUtil.getNowTime());
        boolean result = topicService.editTopic(topic);

        if (result) {
            return "forward:getAllTopic";
        }

        return "frontPage/topicFailed";
    }


    @GetMapping(value = "/deleteTopicById")
    public String deleteTopicById(Integer id, HttpServletRequest request) {
        boolean result = topicService.deleteTopicById(id);
        if (result) {
            User user = (User) request.getSession().getAttribute("user");
            String edituser = user.getUsername();
            return "redirect:getAllTopic?edituser=" + edituser;
        }
        return "frontPage/topicFailed";
    }

    //留言板获取其他用户发表的帖子
    @GetMapping(value = "/getMessageTopicList")
    public String getMessageTopicList(HttpServletRequest request,
                                      @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, ModelMap model) {

        PageHelper.startPage(pageNum,5);
        List<Topic> list =topicService.getMessageTopicList();
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("topicLists",pageInfo);
        return "frontPage/messageList";
    }


    @GetMapping(value = "/getAllTopicByAdmin")
    public String getAllTopicByAdmin(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                     ModelMap model) {
        PageHelper.startPage(pageNum,5);
        PageHelper.orderBy("pubtime desc");

        List<Topic> list = topicService.getMessageTopicList();
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("topicList",pageInfo);
        return "admin/bbs/topicList";
    }

    //根据id查看具体留言
    @GetMapping(value = "/findTopicAnswerById")
    public String findTopicAnswerById(Integer id,ModelMap model) {

        //1.根据id获取留言数
        int replyNum = answerService.getReplyNumById(id);


        //2.根据id获取具体帖子信息
        Topic topic = topicService.getTopicById(id);

        List<Topic> topicList = topicService.findTopicAnswerById(id);

        model.addAttribute("replyNum",replyNum);
        model.addAttribute("topic",topic);
        model.addAttribute("topicList",topicList);

        return "frontPage/topicDetail";
    }


    @GetMapping(value = "/getTopicByName")
    public String getTopicByName(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                 String edituser,ModelMap model) {
        PageHelper.startPage(pageNum,5);
        PageHelper.orderBy("pubtime desc");
        List<Topic> list = topicService.getTopicByName(edituser);

        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("topicList",pageInfo);
        return "admin/bbs/topicList";
    }


    //网站后台
    @GetMapping(value = "/updateStateByIdAdmin")
    public String updateStateByIdAdmin(Integer id) {

        Topic topic = topicService.getTopicById(id);
        if(topic.getState()==1){
            topic.setState(2);
        }else{
            topic.setState(1);
        }
        topicService.updateStateByTopic(topic);
//        model.addAttribute("topic",topic);

        return "forward:/getAllTopicByAdmin";
    }

    //网站后台，根据id删除帖子

    @GetMapping(value = "/deleteTopicByIdAdmin")
    public String deleteTopicByIdAdmin(Integer id) {
        topicService.deleteTopicById(id);
        return "forward:/getAllTopicByAdmin";
    }


}
