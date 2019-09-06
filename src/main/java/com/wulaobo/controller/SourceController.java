package com.wulaobo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wulaobo.bean.Source;
import com.wulaobo.service.SourceService;
import com.wulaobo.utils.DateUtil;
import com.wulaobo.utils.FastDFSClient;
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
//          String path = "D:/upload/source/";
//        source.setPubtime(DateUtil.getNowTime());
        String filename = file.getOriginalFilename();
        source.setFilename(filename);

        //上传文件的关键两行代码
        String str = FastDFSClient.uploadFile(file);
        String filepath = FastDFSClient.getResAccessUrl(str);

        source.setPubtime(DateUtil.getNowTime());

        source.setFilepath(filepath);
        boolean result = sourceService.addSource(source);
        if (result) {
            try {
//                file.transferTo(fileDir);
                return "admin/uploadSuccess";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "failed";
    }


    @GetMapping(value = "/getSourceListByUser")
    public String getSourceListByUser(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      ModelMap model) {
        PageHelper.startPage(pageNum, 5);
        PageHelper.orderBy("pubtime desc");

        List<Source> sourceList = sourceService.getSourceList();

        PageInfo pageInfo = new PageInfo(sourceList);

        model.addAttribute("sourceList", pageInfo);
        return "frontPage/sourceList";
    }

    /**
     * 管理员
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping(value = "/getSourceList")
    public String getSourceList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                ModelMap model) {

        PageHelper.startPage(pageNum, 5);
        PageHelper.orderBy("pubtime desc");

        List<Source> sourceList = sourceService.getSourceList();

        PageInfo pageInfo = new PageInfo(sourceList);

        model.addAttribute("sourceList", pageInfo);
        return "admin/source/sourceList";
    }

    @GetMapping(value = "/deleteSourceById")
    public String deleteSourceById(Integer id, ModelMap model) {

        Source source = sourceService.getSourceById(id);
        if (source != null) {
            //先从fastdfs文件服务器删除文件，在删除数据库中的数据
            boolean result = FastDFSClient.deleteFile(source.getFilepath());
            if (result) {
                sourceService.deleteSourceById(id);
                return "forward:/getSourceList";
            }
            return "failed";
        }

        return "failed";
    }

    //http://114.55.93.197/group1/M00/00/00/rBAmt11xuwqAEBxFAANalRLpucE09.docx
    @GetMapping(value = "/downloadSourceById")
    public String downloadSourceById(Integer id, ModelMap model) throws IOException {

        Source source = sourceService.getSourceById(id);

        File file = new File("D:\\down\\source\\" + source.getFilename());
        boolean result = FastDFSClient.downloadFile(source.getFilepath(), file);
        if (result) {
            System.out.println("下载文件：" + file.getName() + " 成功");
            model.addAttribute("msgfile","下载成功");
        } else {
            System.out.println("下载失败！");
            model.addAttribute("msgfile","下载失败");
        }
        return "forward:/getSourceListByUser";

//        try {
//            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(source.getFilepath(), "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        FileInputStream fis = new FileInputStream(source.getFilepath());
//        OutputStream fos =  null;
//        try {
//            fos = response.getOutputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        byte[] buffer = new byte[1024];
//        int len = 0;
//
//        while((len = fis.read(buffer))>0) {
//            fos.write(buffer,0,len);
//        }
//
//        fis.close();
//        fos.close();

    }


}
