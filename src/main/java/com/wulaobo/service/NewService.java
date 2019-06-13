package com.wulaobo.service;

import com.wulaobo.bean.News;

import java.util.List;

public interface NewService {
    List<News> getAllNews();

    News getNewsById(Integer id);
}
