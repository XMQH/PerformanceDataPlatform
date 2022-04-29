package com.qqspeed.performancedataplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qqspeed.performancedataplatform.entity.User;
import com.qqspeed.performancedataplatform.mapper.UserMapper;
import com.qqspeed.performancedataplatform.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author djiehuang
 * @since 2022-04-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}
