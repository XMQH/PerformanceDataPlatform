package com.qqspeed.performancedataplatform.mybatis;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qqspeed.performancedataplatform.entity.User;
import com.qqspeed.performancedataplatform.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
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
        user.setName("Test4");
        user.setAge(43);
        user.setEmail("6535345345@qq.com");
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
        user.setId(2L);
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

    //测试乐观锁 单线程
    @Test
    public void testOptimisticLocker1(){
        // 通过id查询用户信息
        User user = userMapper.selectById(6L);
        // 修改用户信息
        user.setName("王五");
        // 执行更新的操作
        userMapper.updateById(user);
    }
    //测试乐观锁 模拟多线程
    @Test
    public void testOptimisticLocker2(){
        // 通过id查询用户信息
        User user = userMapper.selectById(6L);
        // 修改用户信息
        user.setName("王五");

        // 模拟抢先更新，没使用多线程 备注 后面试一下
        User user1 = userMapper.selectById(6L);
        user1.setName("老六");
        userMapper.updateById(user1); //更新成功
        // 执行更新的操作
        userMapper.updateById(user); //更新失败 结果“老六” 此时版本号已被更新 版本不一致 不能修改
    }

    /**
     * 测试查询
     * **/
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1L); // 查询单个用户
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds(){
        List<User> user = userMapper.selectBatchIds(Arrays.asList(1,2,3)); // 查询多个用户
        user.forEach(System.out::println);
    }

    /**
     * 测试条件查询 map
     */
    @Test
    public void testSelectByMaps(){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("name","老六");
        List<User> users = userMapper.selectByMap(hashMap);// 查询多个用户
        users.forEach(System.out::println);
    }

    /**
     * 测试分页查询
     * **/
    @Test
    public void testPage(){
        Page<User> page =new Page<>(1,5);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
    }

    /**
     * 测试删除操作(配置了逻辑删除 逻辑删除字段deleted 被逻辑删除的数据被过滤查询不了 只针对自动生成的sql语句)
     * **/
     @Test
    public void testDelete(){
        userMapper.deleteById(10L);
//        userMapper.deleteBatchIds(Arrays.asList(2,3)); // 批量删除
//         userMapper.deleteByMap(); // 条件删除
     }



}
