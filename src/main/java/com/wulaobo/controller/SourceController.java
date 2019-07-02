package com.wulaobo.controller;

import com.wulaobo.bean.Source;
import com.wulaobo.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SourceController {


    @PostMapping(value = "/sourceUpload")
    public String sourceUpload(Source source, MultipartFile file) {

        source.setPubtime(DateUtil.getNowTime());

        return "";
    }

}
