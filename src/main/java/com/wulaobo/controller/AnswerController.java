package com.wulaobo.controller;

import com.wulaobo.bean.Answer;
import com.wulaobo.service.AnswerService;
import com.wulaobo.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;

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


}
