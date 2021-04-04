package com.wulaobo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wulaobo.bean.Source;
import com.wulaobo.service.SourceService;
import com.wulaobo.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class SourceController {

    @Autowired
    private SourceService sourceService;

    @PostMapping(value = "/sourceUpload")
    public String sourceUpload(MultipartFile file, HttpServletRequest request) {
        Source source = new Source();
//          String path = request.getServletContext().getRealPath("source");
        String path = "D:/upload/source/";
//        source.setPubtime(DateUtil.getNowTime());
        String filename = file.getOriginalFilename();
        source.setFilename(filename);

        source.setPubtime(DateUtil.getNowTime());

        File fileDir = new File(path,filename);
        //检测是否存在目录
        if(!fileDir.getParentFile().exists()) {
            fileDir.getParentFile().mkdirs();
        }
        source.setFilepath(path+filename);
        boolean result = sourceService.addSource(source);
        if(result) {
            try {
                file.transferTo(fileDir);
                return "admin/uploadSuccess";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "failed";
    }


    @GetMapping(value = "/getSourceListByUser")
    public String getSourceListByUser(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                      ModelMap model){
        PageHelper.startPage(pageNum,5);
        PageHelper.orderBy("pubtime desc");

        List<Source> sourceList = sourceService.getSourceList();

        PageInfo pageInfo = new PageInfo(sourceList);

        model.addAttribute("sourceList",pageInfo);
        return "frontPage/sourceList";
    }


    @GetMapping(value = "/getSourceList")
    public String getSourceList(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                ModelMap model) {

        PageHelper.startPage(pageNum,5);
        PageHelper.orderBy("pubtime desc");

        List<Source> sourceList = sourceService.getSourceList();

        PageInfo pageInfo = new PageInfo(sourceList);

        model.addAttribute("sourceList",pageInfo);
        return "admin/source/sourceList";
    }

    @GetMapping(value = "/deleteSourceById")
    public String deleteSourceById(Integer id,ModelMap model) {


        Source source = sourceService.getSourceById(id);

        if(source!=null) {

            boolean result = sourceService.deleteSourceById(id);
            if(result){
                //文件从数据库删除后，将本地磁盘中的文件删除
                File file = new File(source.getFilepath());
                if(file.exists()){
                    file.delete();
                }
                return "forward:/getSourceList";
            }
            return "failed";
        }

        return "failed";
    }


    @GetMapping(value = "/downloadSourceById")
    public void downloadSourceById(Integer id, HttpServletResponse response) throws IOException {

        Source source = sourceService.getSourceById(id);


        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(source.getFilepath(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileInputStream fis = new FileInputStream(source.getFilepath());
        OutputStream fos =  null;
        try {
            fos = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] buffer = new byte[1024];
        int len = 0;

        while((len = fis.read(buffer))>0) {
            fos.write(buffer,0,len);
        }

        fis.close();
        fos.close();

    }


}
