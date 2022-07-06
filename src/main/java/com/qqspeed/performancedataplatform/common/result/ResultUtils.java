package com.qqspeed.performancedataplatform.common.result;

/**
 * 返回工具类
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 成功
     * @param code
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(Integer code,T data,String message) {
        return new BaseResponse<>(code, data, message);
    }

    /**
     * 成功
     * @param code
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(Integer code,T data) {
        return new BaseResponse<>(code, data);
    }

    /**
     * 成功
     * @param code
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(ErrorCode code,T data) {
        return new BaseResponse(code.getCode(), data, code.getMessage(), code.getDescription());
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @param description
     * @return
     */
    public static BaseResponse error(Integer code, String message, String description) {
        return new BaseResponse(code, null, message, description);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse(errorCode.getCode(), null, message, description);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String description) {
        return new BaseResponse(errorCode.getCode(), errorCode.getMessage(), description);
    }
}
