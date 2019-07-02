package com.wulaobo.service.impl;

import com.wulaobo.bean.Topic;
import com.wulaobo.mapper.TopicMapper;
import com.wulaobo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public boolean addTopic(Topic topic) {
        return topicMapper.addTopic(topic);
    }

    @Override
    public ArrayList<Topic> getAllTopic(String edituser) {
        return topicMapper.getAllTopic(edituser);
    }

    @Override
    public Topic getTopicById(Integer id) {
        return topicMapper.getTopicById(id);
    }

    @Override
    public boolean editTopic(Topic topic) {
        return topicMapper.editTopic(topic);
    }

    @Override
    public boolean deleteTopicById(Integer id) {
        return topicMapper.deleteTopicById(id);
    }

    @Override
    public List<Topic> getMessageTopicList() {
        return topicMapper.getMessageTopicList();
    }

    @Override
    public List<Topic> findTopicAnswerById(Integer id) {
        return topicMapper.findTopicAnswerById(id);
    }

    @Override
    public List<Topic> getTopicByName(String edituser) {
        return topicMapper.getTopicByName(edituser);
    }

    @Override
    public void updateStateByTopic(Topic topic) {
        topicMapper.updateStateByTopic(topic);
    }


}
