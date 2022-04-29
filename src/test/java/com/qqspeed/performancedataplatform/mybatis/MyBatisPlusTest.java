package com.qqspeed.performancedataplatform.mybatis;

import com.qqspeed.performancedataplatform.entity.User;
import com.qqspeed.performancedataplatform.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyBatisPlusTest {

    //继承了BaseMapper，所有方法来自父类，可自己添加编写扩展方法
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        //测试查询全部的用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

    }
    //测试数据插入(会自动插入id)
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("Test3");
        user.setAge(35);
        user.setEmail("655454545@qq.com");
        user.setNickname("李四");
        user.setPassword("123456");
        user.setPhone("13455523365");
        user.setPermission(1);
        user.setSex(2);
        user.setState(0);
        user.setNote("我最帅");
        int insert = userMapper.insert(user);
        System.out.println(insert);

    }

    //测试更新 有问题
    @Test
    public void testUpdate(){
        User user = new User();
        user.setName("Test2");
        user.setAge(3);
        user.setEmail("6565656@qq.com");
        user.setNickname("张三");
        user.setPassword("123456");
        user.setPhone("12345678910");
        user.setPermission(1);
        user.setSex(2);
        user.setState(0);
        // 该方法会有更新字段验证策略，需配置：field-strategy字段更新插入策略属性
        userMapper.updateById(user);
    }
}
