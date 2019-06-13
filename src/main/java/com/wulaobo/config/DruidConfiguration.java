package com.wulaobo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//配置druid数据源
@Configuration
public class DruidConfiguration {

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource druid() {
        return  new DruidDataSource();
    }


}
