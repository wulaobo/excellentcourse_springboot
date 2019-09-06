package com.wulaobo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wulaobo.bean.Video;
import com.wulaobo.service.VideoService;
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
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;


@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping(value = "/videoUpload")
    public String videoUpload(String title, MultipartFile file, HttpServletRequest request) throws IOException {
        Video video = new Video();

        String str = FastDFSClient.uploadFile(file);
        FastDFSClient.getResAccessUrl(str);

        String videoName = file.getOriginalFilename();  //获取上传后的文件名
        File uploadFile = new File(FastDFSClient.getResAccessUrl(str));
        video.setPath(FastDFSClient.getResAccessUrl(str));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        video.setUploadTime(timestamp);
        video.setTitle(title);
        video.setSize(this.getSize(uploadFile));
        video.setType(this.getFileExt(videoName));
        boolean result = videoService.addVideo(video);
        if (result) {
            return "admin/uploadSuccess";
        }

        return "failed";
    }

    //管理员
    @GetMapping(value = "/getVideoList")
    public String getVideoList(@RequestParam(value = "pageNum", defaultValue = "1") Integer num, ModelMap model) {
        PageHelper.startPage(num, 5);
        PageHelper.orderBy("uploadTime desc");
        List<Video> videoList = videoService.getVideoList();

        PageInfo pageInfo = new PageInfo(videoList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/video/videoList";
    }

    //普通用户
    @GetMapping(value = "/getVideoListByUser")
    public String getVideoListByUser(@RequestParam(value = "pageNum", defaultValue = "1") Integer num, ModelMap model) {
        PageHelper.startPage(num, 5);
        PageHelper.orderBy("uploadTime desc");
        List<Video> videoList = videoService.getVideoList();

        PageInfo pageInfo = new PageInfo(videoList);
        model.addAttribute("pageInfo", pageInfo);
        return "frontPage/videoList";
    }


    //管理员点击播放按钮，开始播放视频
    @GetMapping(value = "/videoPlayByIdAndAdmin")
    public String videoPlayByIdAndAdmin(Integer id, ModelMap model) {
        Video video = videoService.getVideoById(id);
        model.addAttribute("title", video.getTitle());
        model.addAttribute("path", video.getPath());
        return "videoPlay";
    }

    @GetMapping(value = "/deleteVideoById")
    public String deleteVideoById(Integer id, ModelMap model) {

        Video video = videoService.getVideoById(id);
        if (video != null) {

            try{
                boolean result = FastDFSClient.deleteFile(video.getPath());
                if (result) {
                    videoService.deleteVideoById(id);
//                    File file = new File(video.getPath());
//                    if (file.exists()) {
//                        file.delete();
                        return "forward:/getVideoList";
//                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return "failed";

        }
        return "failed";
    }

    @GetMapping(value = "/downloadVideoById")
    public String downloadVideoById(Integer id, ModelMap model) throws IOException {

        Video video = videoService.getVideoById(id);
        File file = new File("D:\\down\\video\\"+video.getTitle()+".mp4");
        boolean result = FastDFSClient.downloadFile(video.getPath(),file);
        if(result) {
            System.out.println("下载文件："+file.getName()+" 成功");
            model.addAttribute("msg","下载成功");
        }else{
            System.out.println("下载失败！");
            model.addAttribute("msg","下载失败");
        }
        return "forward:/getVideoList";
//        String dict = "D:/upload/";
//        File file = new File(dict, video.getPath());
//
//        try {
//            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(video.getPath(), "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        FileInputStream fis = null;
//
//        OutputStream fos = response.getOutputStream();
//
//        if (file.exists()) {
//            fis = new FileInputStream(file);
//            byte[] buffer = new byte[1024];
//            int len = 0;
//            while ((len = fis.read(buffer)) > 0) {
//                fos.write(buffer, 0, len);
//            }
//
//        }
//        fis.close();
//        fos.close();

    }


    /**
     * 获取文件扩展名
     *
     * @return string
     */
    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 依据原始文件名生成新文件名
     *
     * @return
     */
    private String getName(String fileName) {
        Random random = new Random();
        return "" + random.nextInt(10000) + System.currentTimeMillis();

    }

    /**
     * 文件大小，返回kb.mb
     *
     * @return
     */
    private String getSize(File file) {
        String size = "";
        long fileLength = file.length();
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileLength < 1024) {
            size = df.format((double) fileLength) + "BT";
        } else if (fileLength < 1048576) {
            size = df.format((double) fileLength / 1024) + "KB";
        } else if (fileLength < 1073741824) {
            size = df.format((double) fileLength / 1048576) + "MB";
        } else {
            size = df.format((double) fileLength / 1073741824) + "GB";
        }

        return size;

    }
}
