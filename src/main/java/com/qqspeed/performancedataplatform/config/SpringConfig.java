package com.qqspeed.performancedataplatform.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 配置类
 * **/
// 配置类注解
@Configuration
// 自动扫描
@ComponentScan({"com.qqspeed.performancedataplatform.entity",
                "com.qqspeed.performancedataplatform.service",
                })
// 例：注解配置需要加载属性的配置文件 使用属性是用 @Value 注解
//@PropertySource({"classpath:jdbc.properties"})

// 导入Jdbc、MybatisPlus配置类
@Import({JdbcConfig.class})
public class SpringConfig {
}
