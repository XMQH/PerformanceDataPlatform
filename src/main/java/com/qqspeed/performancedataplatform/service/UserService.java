package com.qqspeed.performancedataplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qqspeed.performancedataplatform.entity.User;
import org.springframework.transaction.annotation.Transactional;

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
    // 业务层方法

}
