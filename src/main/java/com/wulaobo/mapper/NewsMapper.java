package com.wulaobo.mapper;


import com.wulaobo.bean.News;
import java.util.List;

public interface NewsMapper {

    List<News> getAllNews();

    News getNewsById(Integer id);
}
