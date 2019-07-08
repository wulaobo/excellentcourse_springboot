package com.wulaobo.service;

import com.wulaobo.bean.Source;

import java.util.List;

public interface SourceService {
    boolean addSource(Source source);

    List<Source> getSourceList();

    boolean deleteSourceById(Integer id);

    Source getSourceById(Integer id);
}
