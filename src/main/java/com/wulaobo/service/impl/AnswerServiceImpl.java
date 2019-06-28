package com.wulaobo.service.impl;

import com.wulaobo.bean.Answer;
import com.wulaobo.mapper.AnswerMapper;
import com.wulaobo.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public int getReplyNumById(Integer id) {
        return answerMapper.getReplyNumById(id);
    }

    @Override
    public boolean addAnswer(Answer answer) {
        return answerMapper.addAnswer(answer);
    }
}
