package com.qqspeed.performancedataplatform.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qqspeed.performancedataplatform.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口 增删改查具体实现方法 MyBatisPlus：继承BaseMapper父类，可以调用父类已经封装好的方法
 * </p>
 *
 * @author djiehuang
 * @since 2022-04-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    //数据层方法...CRUD 继承了BaseMapper，可不用在自定义方法。可以添加自定义方法

}
