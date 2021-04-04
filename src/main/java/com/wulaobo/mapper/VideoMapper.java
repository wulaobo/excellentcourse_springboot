package com.wulaobo.mapper;


import com.wulaobo.bean.Video;

import java.util.List;

public interface VideoMapper {

    boolean addVideo(Video video);

    List<Video> getVideoList();

    Video getVideoById(Integer id);

    int deleteVideoById(Integer id);
}
