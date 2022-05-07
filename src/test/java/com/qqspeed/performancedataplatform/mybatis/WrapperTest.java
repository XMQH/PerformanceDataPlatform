package com.qqspeed.performancedataplatform.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qqspeed.performancedataplatform.mapper.UserMapper;
import com.qqspeed.performancedataplatform.model.domain.User;
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
        wrapper.eq(User::getUserName, "admin")
               .eq(User::getUserPassword, "123456");
        User userInfo = userMapper.selectOne(wrapper);
    }
}
