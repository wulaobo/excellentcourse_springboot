package com.wulaobo.mapper;

import com.wulaobo.bean.Answer;

import java.util.List;

public interface AnswerMapper {

    int getReplyNumById(Integer id);

    boolean addAnswer(Answer answer);

    List<Answer> getAllAnswer();

    boolean deleteAnswerById(Integer id);

    Answer getAnswerById(Integer id);

    void updateStateByAnswer(Answer answer);
}
