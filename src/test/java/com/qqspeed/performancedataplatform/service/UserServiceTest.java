package com.qqspeed.performancedataplatform.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class UserServiceTest {

    @Resource
    UserService userService;
    @Test
    void userRegister() {
        String userAccount = "122123";
        String userPassword="32432423w";
        String checkPassword ="32432423w";
        long l = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertTrue(l>0);
    }
}