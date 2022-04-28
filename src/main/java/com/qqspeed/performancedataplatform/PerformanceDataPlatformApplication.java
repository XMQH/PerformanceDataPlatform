package com.qqspeed.performancedataplatform;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class PerformanceDataPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerformanceDataPlatformApplication.class, args);
    }
}
