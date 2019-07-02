package com.wulaobo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wulaobo.bean.Answer;
import com.wulaobo.service.AnswerService;
import com.wulaobo.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping(value = "/addAnswer")
    public String addAnswer(Answer answer, ModelMap model) {

        answer.setPubtime2(DateUtil.getNowTime());
        boolean result = answerService.addAnswer(answer);
        if(result) {
            return "redirect:/getMessageTopicList";
        }
        return "topicFailed";
    }

    //网站后台
    @GetMapping(value = "/getAllAnswerByAdmin")
    public String getAllAnswerByAdmin(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                      ModelMap model) {
        PageHelper.startPage(pageNum,5);
        PageHelper.orderBy("pubtime2 desc");

        List<Answer> answerList = answerService.getAllAnswer();

        PageInfo pageInfo = new PageInfo(answerList);
        model.addAttribute("answerList",pageInfo);

        return "admin/bbs/answerList";
    }

    @GetMapping(value = "/deleteAnswerByIdAdmin")
    public String deleteAnswerByIdAdmin(Integer id) {

        boolean flag = answerService.deleteAnswerById(id);

        if(flag) {
            return "forward:/getAllAnswerByAdmin";
        }

       return "failed";
    }


    @GetMapping(value = "/updateAnswerStateByIdAdmin")
    public String updateAnswerStateByIdAdmin(Integer id) {
        Answer answer = answerService.getAnswerById(id);
        if(answer.getState2()==1){
            answer.setState2(2);
        }else{
            answer.setState2(1);
        }
        answerService.updateStateByAnswer(answer);

        return "forward:/getAllAnswerByAdmin";
    }

}
