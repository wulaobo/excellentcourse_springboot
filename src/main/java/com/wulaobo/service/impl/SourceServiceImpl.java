package com.wulaobo.service.impl;

import com.wulaobo.bean.Source;
import com.wulaobo.mapper.SourceMapper;
import com.wulaobo.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    private SourceMapper sourceMapper;

    @Override
    public boolean addSource(Source source) {
        return sourceMapper.addSource(source);
    }

    @Override
    public List<Source> getSourceList() {
        return sourceMapper.getSourceList();
    }

    @Override
    public boolean deleteSourceById(Integer id) {
        return sourceMapper.deleteSourceById(id);
    }

    @Override
    public Source getSourceById(Integer id) {
        return sourceMapper.getSourceById(id);
    }
}
