package com.qqspeed.performancedataplatform.util;


/* Mybatis Plus 代码生成器 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    private static final String projectPath = System.getProperty("user.dir");//获取项目路径
    private static final String url = "jdbc:mysql://localhost:3306/performancedata?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai";//jdbc 路径
    private static final String username = "root";//数据库账号
    private static final String password = "root";//数据库密码
    private static final String parentPackageName = "com.qqspeed.performancedataplatform";// 设置父包名
    private static final String moduleName = "generator";// 设置父包模块名
    private static final String writer = "djiehuang";// 设置作者
    private static final String outPath = projectPath + "\\src\\main\\java\\";//输出路径
    private static final String mapperPath = projectPath + "\\src\\main\\java\\com\\qqspeed\\performancedataplatform\\generator\\mapper\\";// 设置mapperXml 模板路径

    public static void main(String[] args) {
        String[] tableNames = {"user"};//可选择多个表生成
        CodeGenerator.execute(tableNames);
    }

    public static void execute(String[] tableNames) {
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(writer) // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outPath) // 指定输出目录
                            .disableOpenDir();//禁止打开输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackageName) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("serviceImpl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperPath)); // 设置mapperXml生成路径

                })

                .strategyConfig(builder -> {
                    builder.addInclude(tableNames) // 设置需要生成的表名
//                            .addTablePrefix("*_") // 设置过滤表前缀
                            //Service 策略配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService")//service类名 %s是适配 根据表名替换
                            .formatServiceImplFileName("%sServiceImpl")//格式化 service 实现类文件名称
                            //Entity 策略配置
                            .entityBuilder()
                            .enableLombok()
                            .enableChainModel()//开启链式模型
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()//开启生成实体时生成字段注解
                            //controller 策略配置
                            .controllerBuilder()
                            .enableRestStyle()//开启生成@RestController 控制器
                            //mapper策略配置
                            .mapperBuilder()
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()//@mapper开启
                            .formatXmlFileName("%sMapper");//xml名

                })
                .templateEngine(new FreemarkerTemplateEngine())// 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}

