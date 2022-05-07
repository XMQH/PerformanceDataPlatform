package com.qqspeed.performancedataplatform.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qqspeed.performancedataplatform.controller.result.Code;
import com.qqspeed.performancedataplatform.controller.result.Message;
import com.qqspeed.performancedataplatform.controller.result.Result;
import com.qqspeed.performancedataplatform.model.domain.User;
import com.qqspeed.performancedataplatform.model.request.UserLoginRequest;
import com.qqspeed.performancedataplatform.model.request.UserRegisterRequest;
import com.qqspeed.performancedataplatform.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录接口
 */
@RestController
@CrossOrigin
@RequestMapping("/logins")
public class LoginController {
    @Resource
    private UserService userService;

//    @PostMapping("/login")
//    public Result login(@RequestBody User user){
//        //查询数据库中与前端输入 用户名和密码 相匹配的数据
//        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<User>();
//        wrapper.eq(User::getUserName, user.getUserName())
//                .eq(User::getUserPassword, user.getUserPassword());
//        User result = userService.getOne(wrapper);
//        Integer code= result != null
//                && result.getUserName().equals(user.getUserName())
//                && result.getUserPassword().equals(user.getUserPassword())
//                ? Code.LOGIN_SUCCESS : Code.LOGIN_FAILED;
//        String message = result != null ? Message.LOGIN_SUCCESS_MSG : Message.LOGIN_FAILED_MSG;
//        return new Result(code,result,message);
//    }

        @PostMapping("/register")
        public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
            if (userRegisterRequest == null){
                return null;
            }
            String userAccount = userRegisterRequest.getUserAccount();
            String userPassword = userRegisterRequest.getUserPassword();
            String checkPassword = userRegisterRequest.getCheckPassword();
            if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
                return null;
            }
            return userService.userRegister(userAccount, userPassword, checkPassword);
        }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if (userLoginRequest == null){
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        return userService.userLogin(userAccount, userPassword,request);
    }

}
