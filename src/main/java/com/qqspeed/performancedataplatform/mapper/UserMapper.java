package com.qqspeed.performancedataplatform.mapper;

import com.qqspeed.performancedataplatform.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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


}
