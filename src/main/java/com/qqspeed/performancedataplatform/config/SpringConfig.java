package com.qqspeed.performancedataplatform.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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

// 导入Jdbc、MybatisPlus配置类(导入之后，导入的配置类不用在加@Configuration注解)
@Import({JdbcConfig.class,MybatisPlusConfig.class})
@EnableAspectJAutoProxy //使用注解开发AOP
@EnableTransactionManagement //开启自动管理事务 业务接口加@Transactional开启业务事务管理
public class SpringConfig {
}
