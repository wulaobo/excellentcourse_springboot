package com.wulaobo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
////指定扫描的mapper包名
@MapperScan("com.wulaobo.mapper")
public class ExcellentcourseSpringbootApplication {

    public static void main(String[] args) {
        System.out.println("重构毕业设计的工作开始了。。。");
        SpringApplication.run(ExcellentcourseSpringbootApplication.class, args);
    }

}
