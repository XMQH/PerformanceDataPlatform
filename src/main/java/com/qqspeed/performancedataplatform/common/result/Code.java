package com.qqspeed.performancedataplatform.common.result;

/**
 * 状态码
 */
public class Code {
    /**
     * 成功状态码
     */
    public static final Integer LOGIN_SUCCESS = 10001; // 登录成功

    public static final Integer REGISTER_SUCCESS = 10011; // 注册成功
    public static final Integer SAVE_SUCCESS = 10021; // 保存成功
    public static final Integer UPDATE_SUCCESS = 10031; // 更新成功
    public static final Integer SELECT_SUCCESS = 10041; // 查询、搜索成功
    public static final Integer DELETE_SUCCESS = 10051; // 删除成功

    public static final Integer LOGOUT_SUCCESS = 10051; // 注销成功

    /**
     * 失败状态码
     */
    public static final Integer LOGIN_FAILED = 10000; // 登录失败

    public static final Integer REGISTER_FAILED = 10010; // 注册失败
    public static final Integer SAVE_FAILED = 10020; // 保存失败
    public static final Integer UPDATE_FAILED = 10030; // 更新失败
    public static final Integer SELECT_FAILED = 10040; // 查询、搜索失败
    public static final Integer DELETE_FAILED = 10050; // 删除失败
    public static final Integer LOGOUT_FAILED = 10050; // 注销失败

    /**
     * 系统异常状态码
     */
    public static final Integer SYSTEM_ERR = 50001; // 系统异常
    public static final Integer SYSTEM_UNKNOW_ERR = 59999; // 系统未知异常
    /**
     * 业务异常状态码
     */
    public static final Integer BUSINESS_ERR = 50002; // 业务异常 暂时写一个
    public static final Integer BUSINESS_LOGIN_ERR = 50003; // 业务异常 暂时写一个




}
