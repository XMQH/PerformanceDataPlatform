package com.qqspeed.performancedataplatform.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3407032401140247250L;
    private String userAccount;
    private String userPassword;
}
