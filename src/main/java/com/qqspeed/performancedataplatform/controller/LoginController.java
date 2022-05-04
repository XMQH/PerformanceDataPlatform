package com.qqspeed.performancedataplatform.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qqspeed.performancedataplatform.controller.result.Code;
import com.qqspeed.performancedataplatform.controller.result.Message;
import com.qqspeed.performancedataplatform.controller.result.Result;
import com.qqspeed.performancedataplatform.entity.User;
import com.qqspeed.performancedataplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping("/logins")
    public Result login(@RequestBody User user){
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<User>();
        wrapper.eq(User::getName, user.getName())
               .eq(User::getPassword, user.getPassword());
        User loginUser = userService.getOne(wrapper);
        Integer code= loginUser != null ? Code.LOGIN_SUCCESS : Code.LOGIN_FAILED;
        String msg = loginUser != null ? Message.LOGIN_FAILED_MSG : Message.LOGIN_SUCCESS_MSG;
        return new Result(code,loginUser,msg);
    }
}
