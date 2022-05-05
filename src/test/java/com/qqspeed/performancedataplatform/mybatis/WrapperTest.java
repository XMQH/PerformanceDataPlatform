package com.qqspeed.performancedataplatform.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
        //测试查询年龄大于20的的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age",20);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectOne(){
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, "admin")
               .eq(User::getPassword, "123456");
        User userInfo = userMapper.selectOne(wrapper);
    }
}
