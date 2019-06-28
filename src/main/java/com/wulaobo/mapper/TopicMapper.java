package com.wulaobo.mapper;

import com.wulaobo.bean.Topic;

import java.util.ArrayList;
import java.util.List;

public interface TopicMapper {

    boolean addTopic(Topic topic);

    ArrayList<Topic> getAllTopic(String edituser);

    Topic getTopicById(Integer id);

    boolean editTopic(Topic topic);

    boolean deleteTopicById(Integer id);

    List<Topic> getMessageTopicList(String edituser);

    List<Topic> findTopicAnswerById(Integer id);
}
