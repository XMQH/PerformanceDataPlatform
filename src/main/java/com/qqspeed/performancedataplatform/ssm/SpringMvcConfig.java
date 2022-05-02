package com.qqspeed.performancedataplatform.ssm;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.qqspeed.performancedataplatform.controller","com.qqspeed.performancedataplatform.config"})
@EnableWebMvc
public class SpringMvcConfig {

}
