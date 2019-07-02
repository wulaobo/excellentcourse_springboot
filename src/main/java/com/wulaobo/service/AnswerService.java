package com.wulaobo.service;

import com.wulaobo.bean.Answer;

import java.util.List;

public interface AnswerService {
    int getReplyNumById(Integer id);

    boolean addAnswer(Answer answer);

    List<Answer> getAllAnswer();

    boolean deleteAnswerById(Integer id);

    Answer getAnswerById(Integer id);

    void updateStateByAnswer(Answer answer);
}
