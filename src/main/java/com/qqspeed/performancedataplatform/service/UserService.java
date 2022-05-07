package com.qqspeed.performancedataplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qqspeed.performancedataplatform.model.domain.User;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author djiehuang
 * @since 2022-04-28
 */
@Transactional //开启事务
public interface UserService extends IService<User> {

    /**
     * 用户注册方法
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */

    long userRegister(String userAccount,String userPassword,String checkPassword);

    /**
     * 用户登录方法
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 用户信息（脱敏）
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);
}
