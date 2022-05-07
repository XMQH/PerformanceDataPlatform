package com.qqspeed.performancedataplatform.service.impl;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qqspeed.performancedataplatform.mapper.UserMapper;
import com.qqspeed.performancedataplatform.model.domain.User;
import com.qqspeed.performancedataplatform.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qqspeed.performancedataplatform.contant.UserConstant.USER_LOGIN_STATE;

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
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //校验 引入commons-lang3 同时校验userAccount,userPassword,checkPassword是否为空
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return -1;
        }
        //校验用户账号和密码长度是否小于6位
        if (userAccount.length() < 6){
            return -1;
        }
        if (userPassword.length() < 6 || checkPassword.length() < 6){
            return -1;
        }
        // 校验用户名不能包含特殊字符
        String validPattern= "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            return -1;
        }
        // 密码和校验密码要相同
        if (!userPassword.equals(checkPassword)){
            return -1;
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

        return user.getId();
    }


    @Override
    public User userLogin(String userAccount, String userPassword,HttpServletRequest request) {

        //校验 引入commons-lang3 同时校验userAccount,userPassword,checkPassword是否为空
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        //校验用户账号和密码长度是否小于6位
        if (userAccount.length() < 6){
            return null;
        }
        if (userPassword.length() < 6){
            return null;
        }
        // 校验用户名不能包含特殊字符
        String validPattern= "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            return null;
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
            return null;
        }
        // 用户脱敏
        User safeUser = new User();
        safeUser.setId(loginUser.getId());
        safeUser.setUserName(loginUser.getUserName());
        safeUser.setUserAccount(loginUser.getUserAccount());
        safeUser.setAvatarUrl(loginUser.getAvatarUrl());
        safeUser.setGender(loginUser.getGender());
        safeUser.setPhone(loginUser.getPhone());
        safeUser.setEmail(loginUser.getEmail());
        safeUser.setNickname(loginUser.getNickname());
        safeUser.setPermission(loginUser.getPermission());
        safeUser.setStatus(loginUser.getStatus());
        safeUser.setCreateTime(loginUser.getCreateTime());
        safeUser.setDescription(loginUser.getDescription());

        // 记录用户登录状态
        request.getSession().setAttribute(USER_LOGIN_STATE,safeUser);

        // 返回脱敏后的用户信息
        return safeUser;
    }



}
