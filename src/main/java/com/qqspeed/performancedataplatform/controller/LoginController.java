package com.qqspeed.performancedataplatform.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qqspeed.performancedataplatform.controller.result.Code;
import com.qqspeed.performancedataplatform.controller.result.Message;
import com.qqspeed.performancedataplatform.controller.result.Result;
import com.qqspeed.performancedataplatform.entity.User;
import com.qqspeed.performancedataplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/logins")
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping
    public Result login(@RequestBody User user){
        System.out.println(user+"////////////////");
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<User>();
        wrapper.eq(User::getUsername, user.getUsername())
               .eq(User::getPassword, user.getPassword());
        User userInfo = userService.getOne(wrapper);
        Integer code= userInfo != null
                && userInfo.getUsername()==user.getUsername()
                && userInfo.getPassword() ==user.getPassword()
                ? Code.LOGIN_SUCCESS : Code.LOGIN_FAILED;
        String msg = userInfo != null ? Message.LOGIN_SUCCESS_MSG : Message.LOGIN_FAILED_MSG;
        return new Result(code,userInfo,msg);
    }
}
