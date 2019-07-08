package com.wulaobo.service.impl;

import com.wulaobo.bean.Video;
import com.wulaobo.mapper.VideoMapper;
import com.wulaobo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public boolean addVideo(Video video) {
        return videoMapper.addVideo(video);
    }

    @Override
    public List<Video> getVideoList() {
        return videoMapper.getVideoList();
    }

    @Override
    public Video getVideoById(Integer id) {
        return videoMapper.getVideoById(id);
    }

    @Override
    public boolean deleteVideoById(Integer id) {
        return videoMapper.deleteVideoById(id);
    }
}
