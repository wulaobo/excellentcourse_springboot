package com.wulaobo;


import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;


////指定扫描的mapper包名
@MapperScan("com.wulaobo.mapper")
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@SpringBootApplication
public class ExcellentcourseSpringbootApplication {

    public static void main(String[] args) {
        System.out.println("重构毕业设计的工作开始了。。。");
        SpringApplication.run(ExcellentcourseSpringbootApplication.class, args);
    }

}
