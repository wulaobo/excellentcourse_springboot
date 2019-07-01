package com.wulaobo.service;

import com.wulaobo.bean.News;

import java.util.List;

public interface NewService {
    List<News> getAllNews();

    News getNewsById(Integer id);

    List<News> selectNewsByTitle(String title);

    void updateNews(News news);

    void deleteNewsById(Integer id);

    void addNews(News news);
}
