package com.qqspeed.performancedataplatform.controller;

import com.qqspeed.performancedataplatform.common.result.*;
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
        public BaseResponse userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
            if (userRegisterRequest == null){
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
            String userAccount = userRegisterRequest.getUserAccount();
            String userPassword = userRegisterRequest.getUserPassword();
            String checkPassword = userRegisterRequest.getCheckPassword();
            if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
                return ResultUtils.error(ErrorCode.NULL_ERROR);
            }
            long result = userService.userRegister(userAccount, userPassword, checkPassword);
            return ResultUtils.success(Code.REGISTER_SUCCESS,result,Message.REGISTER_SUCCESS_MSG);
        }

    @PostMapping("/login")
    public BaseResponse userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if (userLoginRequest == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        User result = userService.userLogin(userAccount, userPassword, request);
        System.out.println(result);
        if (result==null) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.success(Code.LOGIN_SUCCESS,result,Message.LOGIN_SUCCESS_MSG);
    }


    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request){
        if (request == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(Code.LOGOUT_SUCCESS,result,Message.LOGOUT_SUCCESS_MSG);
    }
}
