package com.qqspeed.performancedataplatform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qqspeed.performancedataplatform.common.result.Code;
import com.qqspeed.performancedataplatform.exception.BusinessException;
import com.qqspeed.performancedataplatform.mapper.UserMapper;
import com.qqspeed.performancedataplatform.model.domain.User;
import com.qqspeed.performancedataplatform.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qqspeed.performancedataplatform.constant.UserConstant.USER_LOGIN_STATE;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author djiehuang
 * @since 2022-04-28
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    /**
     * 盐值 混淆密码
     */
    private static final String SALT = "xmqh";

    @Resource
    private UserMapper userMapper;

    /**
     *  用户注册业务
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 返回用户id
     */
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //校验 引入commons-lang3 同时校验userAccount,userPassword,checkPassword是否为空
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            throw new BusinessException(Code.BUSINESS_ERR,"参数为空");
        }
        //校验用户账号和密码长度是否小于6位
        if (userAccount.length() < 6){
            throw new BusinessException(Code.BUSINESS_ERR,"账户长度小于6位");
        }
        if (userPassword.length() < 6 || checkPassword.length() < 6){
            throw new BusinessException(Code.BUSINESS_ERR,"密码长度小于6位");
        }
        // 校验用户名不能包含特殊字符
        String validPattern= "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            throw new BusinessException(Code.BUSINESS_ERR,"用户账户包含特殊字符");
        }
        // 密码和校验密码要相同
        if (!userPassword.equals(checkPassword)){
            throw new BusinessException(Code.BUSINESS_ERR,"两次输入密码不一致");
        }
        // 账号不能重复 查询数据库放到最后
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserAccount,userAccount);
        long count = userMapper.selectCount(lqw);
        if (count > 0){
            return -1;
        }
        // 密码加密
        String encryptPassword= DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        // 向数据库添加数据
        User user =new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if (!saveResult){
            return -1;
        }
        return user.getUserId();
    }


    @Override
    public User userLogin(String userAccount, String userPassword,HttpServletRequest request) {

        //校验 引入commons-lang3 同时校验userAccount,userPassword,checkPassword是否为空
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            throw new BusinessException(Code.BUSINESS_ERR,"登录信息参数为空");
        }
        //校验用户账号和密码长度是否小于6位
        if (userAccount.length() < 6){
            throw new BusinessException(Code.BUSINESS_ERR,"输入账号小于6位");
        }
        if (userPassword.length() < 6){
            throw new BusinessException(Code.BUSINESS_ERR,"输入密码小于6位");
        }
        // 校验用户名不能包含特殊字符
        String validPattern= "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            throw new BusinessException(Code.BUSINESS_ERR,"账户包含特殊字符");
        }
        // 密码加密
        String encryptPassword= DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        // 查询用户是否存在
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserAccount,userAccount)
           .eq(User::getUserPassword,encryptPassword);
        User loginUser = userMapper.selectOne(lqw);
        // 用户不存在
        if (loginUser == null){
            log.info("User login failed, userAccount cannot match userPassword");
            throw new BusinessException(Code.BUSINESS_ERR,"用户不存在");
        }
        // 用户脱敏
        User safetyUser = getSafetyUser(loginUser);
        // 记录用户登录状态
        request.getSession().setAttribute(USER_LOGIN_STATE,safetyUser);
        // 返回脱敏后的用户信息
        return safetyUser;
    }

    /**
     * 用户脱敏 去除密码等重要信息
     * @param loginUser 返回脱敏后的信息
     * @return
     */
    public User getSafetyUser(User loginUser){
        User safetyUser = new User();
        safetyUser.setUserId(loginUser.getUserId());
        safetyUser.setUserName(loginUser.getUserName());
        safetyUser.setUserAccount(loginUser.getUserAccount());
        safetyUser.setAvatarUrl(loginUser.getAvatarUrl());
        safetyUser.setGender(loginUser.getGender());
        safetyUser.setPhone(loginUser.getPhone());
        safetyUser.setEmail(loginUser.getEmail());
        safetyUser.setNickname(loginUser.getNickname());
        safetyUser.setPermission(loginUser.getPermission());
        safetyUser.setStatus(loginUser.getStatus());
        safetyUser.setCreateTime(loginUser.getCreateTime());
        safetyUser.setDescription(loginUser.getDescription());
        return safetyUser;
    }

    /**
     * 用户注销
     * @param request
     */
    public int userLogout(HttpServletRequest request) {
        // 移除登录状态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }
}
