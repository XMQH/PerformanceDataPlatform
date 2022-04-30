package com.qqspeed.performancedataplatform.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qqspeed.performancedataplatform.entity.User;
import com.qqspeed.performancedataplatform.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        //测试查询年龄大于40的的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age",40);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);

    }
}
