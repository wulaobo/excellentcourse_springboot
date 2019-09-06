package com.wulaobo;

import com.wulaobo.bean.Video;
import com.wulaobo.service.VideoService;
import com.wulaobo.utils.FastDFSClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcellentcourseSpringbootApplicationTests {

    @Autowired
    private VideoService videoService;

    @Test
    public void contextLoads() {
        //String fileUrl = this.getClass().getResource("C:\\Users\\15256\\Pictures\\xiaozhu.jpg").getPath();
        File file = new File("C:\\Users\\15256\\Pictures\\gaoqing(3)\\aaa.jpg");
        String str = FastDFSClient.uploadFile(file);
        FastDFSClient.getResAccessUrl(str);

    }

    @Test
    public void download() {
        Video video = videoService.getVideoById(7);
        File file = new File("D:\\down\\video\\"+video.getTitle()+".mp4");
        boolean result = FastDFSClient.downloadFile(video.getPath(),file);
        if(result) {
            System.out.println("下载文件："+file.getName()+" 成功");
        }else{
            System.out.println("下载失败！");
        }
    }

    @Test
    public void Delete() {
        FastDFSClient.deleteFile("group1/M00/00/00/rBAmt11woi6AMFZgAAAcR6eHEc4208.jpg");
    }

}
