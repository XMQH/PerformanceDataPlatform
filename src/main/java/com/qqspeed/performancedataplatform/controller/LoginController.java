package com.qqspeed.performancedataplatform.controller;

import com.qqspeed.performancedataplatform.common.result.Code;
import com.qqspeed.performancedataplatform.common.result.Message;
import com.qqspeed.performancedataplatform.common.result.Result;
import com.qqspeed.performancedataplatform.exception.BusinessException;
import com.qqspeed.performancedataplatform.model.domain.User;
import com.qqspeed.performancedataplatform.model.request.UserLoginRequest;
import com.qqspeed.performancedataplatform.model.request.UserRegisterRequest;
import com.qqspeed.performancedataplatform.service.UserService;
import org.apache.commons.lang3.StringUtils;
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


        @PostMapping("/register")
        public Result userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
            if (userRegisterRequest == null){
                throw new BusinessException(Code.BUSINESS_ERR);
            }
            String userAccount = userRegisterRequest.getUserAccount();
            String userPassword = userRegisterRequest.getUserPassword();
            String checkPassword = userRegisterRequest.getCheckPassword();
            if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
                throw new BusinessException(Code.BUSINESS_ERR);
            }
            long result = userService.userRegister(userAccount, userPassword, checkPassword);
            return new Result(Code.REGISTER_SUCCESS,result, Message.REGISTER_SUCCESS_MSG);
        }

    @PostMapping("/login")
    public Result userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if (userLoginRequest == null){
            throw new BusinessException(Code.BUSINESS_ERR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            throw new BusinessException(Code.BUSINESS_ERR);
        }
        User result = userService.userLogin(userAccount, userPassword, request);
        return new Result(Code.REGISTER_SUCCESS,result, Message.REGISTER_SUCCESS_MSG);
    }


    @PostMapping("/logout")
    public Result userLogout(HttpServletRequest request){
        if (request == null){
            throw new BusinessException(Code.BUSINESS_ERR);
        }
        int result = userService.userLogout(request);
        return new Result(Code.LOGOUT_SUCCESS,result,Message.LOGOUT_SUCCESS_MSG);
    }
}
