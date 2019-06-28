package com.wulaobo.service;

import com.wulaobo.bean.Answer;

public interface AnswerService {
    int getReplyNumById(Integer id);

    boolean addAnswer(Answer answer);
}
