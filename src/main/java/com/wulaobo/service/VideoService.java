package com.wulaobo.service;

import com.wulaobo.bean.Video;

import java.util.List;

public interface VideoService {
    boolean addVideo(Video video);

    List<Video> getVideoList();

    Video getVideoById(Integer id);

    boolean deleteVideoById(Integer id);
}
