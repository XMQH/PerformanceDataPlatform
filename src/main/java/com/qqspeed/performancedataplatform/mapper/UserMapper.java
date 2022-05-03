package com.qqspeed.performancedataplatform.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qqspeed.performancedataplatform.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口 增删改查具体实现方法 MyBatisPlus：继承BaseMapper父类，可以调用父类已经封装好的方法
 * </p>
 *
 * @author djiehuang
 * @since 2022-04-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    //数据层方法...CRUD 继承了BaseMapper，可不用在自定义方法。可以添加自定义方法


    @Override
    int insert(User entity);

    @Override
    int deleteById(Serializable id);

    @Override
    int deleteById(User entity);

    @Override
    int deleteByMap(Map<String, Object> columnMap);

    @Override
    int delete(Wrapper<User> queryWrapper);

    @Override
    int deleteBatchIds(Collection<?> idList);

    @Override
    int updateById(User entity);

    @Override
    int update(User entity, Wrapper<User> updateWrapper);

    @Override
    User selectById(Serializable id);

    @Override
    List<User> selectBatchIds(Collection<? extends Serializable> idList);

    @Override
    List<User> selectByMap(Map<String, Object> columnMap);

    @Override
    default User selectOne(Wrapper<User> queryWrapper) {
        return BaseMapper.super.selectOne(queryWrapper);
    }

    @Override
    default boolean exists(Wrapper<User> queryWrapper) {
        return BaseMapper.super.exists(queryWrapper);
    }

    @Override
    Long selectCount(Wrapper<User> queryWrapper);

    @Override
    List<User> selectList(Wrapper<User> queryWrapper);

    @Override
    List<Map<String, Object>> selectMaps(Wrapper<User> queryWrapper);

    @Override
    <P extends IPage<User>> P selectPage(P page, Wrapper<User> queryWrapper);

    @Override
    <P extends IPage<Map<String, Object>>> P selectMapsPage(P page, Wrapper<User> queryWrapper);
}
