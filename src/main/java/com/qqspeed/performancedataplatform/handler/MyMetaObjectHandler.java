package com.qqspeed.performancedataplatform.handler;

/**
 * MyBatisPlus 处理类
 * **/

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component //添加到IOC容器
public class MyMetaObjectHandler implements MetaObjectHandler {
    //插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill..");
        this.strictInsertFill(metaObject, "gtmCreate", LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject, "gtmModified", LocalDateTime::now, LocalDateTime.class);
    }
    //更新时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start insert fill..");
        this.strictUpdateFill(metaObject, "gtmModified", LocalDateTime::now, LocalDateTime.class);
    }
}
