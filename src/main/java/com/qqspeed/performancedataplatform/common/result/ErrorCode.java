package com.qqspeed.performancedataplatform.common.result;

/**
 * 错误码
 *
 */
public enum ErrorCode {

    SUCCESS(0, "ok", ""),
    PARAMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "请求数据为空", ""),
    NOT_LOGIN(40100, "未登录", ""),
    NO_AUTH(40101, "无权限", ""),
    SYSTEM_ERROR(50000, "系统内部异常", ""),
    SYSTEM_UNKNOW_ERR(59999, "系统未知异常", ""),

    /**
     * 成功状态码
     */
    LOGIN_SUCCESS(10001, "登录成功", ""),// 登录成功
    REGISTER_SUCCESS(10011, "注册成功", ""),// 注册成功
    SAVE_SUCCESS(10021, "保存成功", ""),// 保存成功
    UPDATE_SUCCESS(10031, "更新成功", ""),// 更新成功
    SELECT_SUCCESS(10041, "查询成功", ""),// 查询成功
    DELETE_SUCCESS(10051, "删除成功", ""),// 删除成功
    LOGOUT_SUCCESS(10061, "注销成功", ""),// 注销成功

    /**
     * 失败状态码
     */
    LOGIN_FAILED(10000, "登录失败", ""),// 登录失败
    REGISTER_FAILED(10010, "注册失败", ""),// 注册失败
    SAVE_FAILED(10020, "保存失败", ""),// 保存失败
    UPDATE_FAILED(10030, "更新失败", ""),// 更新失败
    SELECT_FAILED(10040, "查询失败", ""),// 查询失败
    DELETE_FAILED(10050, "删除失败", ""),// 删除失败
    LOGOUT_FAILED(10060, "注销失败", "");// 注销失败


    private final Integer code;

    /**
     * 状态码信息
     */
    private final String message;

    /**
     * 状态码描述（详情）
     */
    private final String description;

    ErrorCode(Integer code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }



    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
