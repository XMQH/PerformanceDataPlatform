package com.qqspeed.performancedataplatform.ssm;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * 创建Web容器启动类，加载SpringMVC配置，并配置SpringMVC拦截路径
 * **/

public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //Post请求中文乱码处理

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return new Filter[]{filter};
    }


//    /**
//     * 创建Servlet容器时，加载SpringMVC对应的bean放入
//     * **/
//    @Override
//    protected WebApplicationContext createServletApplicationContext() {
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(SpringMvcConfig.class);
//        return ctx;
//    }
//
//    /**
//     * 设置SpringMVC对应的请求映射路径，设置“/”表示拦截所有请求，任意请求都将转入到SpringMVC管理
//     * **/
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"}; //所有请求用SpringMVC管理
//    }
//
//
//
//    /**
//     * 创建Servlet容器时，加载Spring对应的bean放入
//     * **/
//    @Override
//    protected WebApplicationContext createRootApplicationContext() {
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(SpringConfig.class);
//        return ctx;
//    }
}
