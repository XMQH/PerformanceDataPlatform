//package com.qqspeed.performancedataplatform.controller;
//
//import com.qqspeed.performancedataplatform.controller.result.Code;
//import com.qqspeed.performancedataplatform.controller.result.Result;
//import com.qqspeed.performancedataplatform.exception.BusinessException;
//import com.qqspeed.performancedataplatform.exception.SystemException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
///**
// * 异常处理类
// */
//
//@RestControllerAdvice
//public class ProjectExceptionAdvice {
//    /**
//     * 系统异常,AOP实现
//     * @param sex
//     * @return
//     */
//    @ExceptionHandler(SystemException.class)
//    public Result doSystemException(SystemException sex){
//        //记录日志
//
//        //发送异常信息给相关人员处理
//
//        return new Result(sex.getCode(),null,sex.getMessage());
//    }
//
//    /**
//     * 业务坑出现异常的地方，try catch
//     * @param sex
//     * @return
//     */
//    @ExceptionHandler(BusinessException.class)
//    public Result doBusinessException(BusinessException sex){
//        return new Result(sex.getCode(),null,sex.getMessage());
//    }
//
//    @ExceptionHandler(Exception.class)
//    public Result doException(Exception ex){
//        //异常处理
//        return new Result(Code.SYSTEM_UNKNOW_ERR,null,"系统繁忙，请稍后再试！");
//
//    }
//
//}
