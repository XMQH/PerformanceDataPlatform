package com.qqspeed.performancedataplatform.contant.result;

/**
 * 状态码
 */
public class Code {
    //成功
    public static final Integer LOGIN_SUCCESS = 10001;
    public static final Integer SAVE_SUCCESS = 10011;
    public static final Integer UPDATE_SUCCESS = 10021;
    public static final Integer SELECT_SUCCESS = 10031;
    public static final Integer DELETE_SUCCESS = 10041;
    //失败
    public static final Integer LOGIN_FAILED = 10000;
    public static final Integer SAVE_FAILED = 10010;
    public static final Integer UPDATE_FAILED = 10020;
    public static final Integer SELECT_FAILED = 10030;
    public static final Integer DELETE_FAILED = 10040;

    //系统异常状态码
    public static final Integer SYSTEM_ERR = 50001;
    public static final Integer SYSTEM_UNKNOW_ERR = 59999;
    //业务异常状态码
    public static final Integer BUSINESS_ERR = 50002;




}
