package com.qqspeed.performancedataplatform.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author djiehuang
 * @since 2022-05-07
 */
@Data //相当于同时使用了@ToString、@EqualsAndHashCode、@Getter、@Setter和@RequiredArgsConstrutor这些注解
@AllArgsConstructor //生成对应的有参构造方法
@NoArgsConstructor //生成对应的无参构造方法
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 姓名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 账号
     */
    @TableField("user_account")
    private String userAccount;

    /**
     * 密码
     */
    @TableField("user_password")
    private String userPassword;

    /**
     * 头像
     */
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * 性别
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 电话号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 用户权限 0-普通用户 1-管理员
     */
    @TableField("permission")
    private Integer permission;

    /**
     * 用户状态 0-正常
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT) //fill = FieldFill.INSERT 插入数据是自动填充
    private LocalDateTime createTime;

    /**
     * 用户描述
     */
    @TableField("description")
    private String description;

    /**
     * 更新时间
     *
     * 今天学习的MyBatis-Plus的乐观锁的时候踩了一个坑，就是在实现乐观锁后，自动注入的更新时间失效了。
     * 原因是为了实现乐观锁，我们在更新数据前，必须先查找数据，而即便我们修改了updateTime字段之外的值，然后提交修改，如果updateTime字段原先就有数据，那么此时数据不变，也就是自动注入失效了。
     * 其实这个问题可以说并不是乐观锁导致的自动注入失效，只是从数据库中查询回来的数据，更新是updateTime会使用原有数据。
     * 解决办法就是在注解上添加update = "now()"
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE,update = "now()") //fill = FieldFill.INSERT_UPDATE 更新时自动填充
    private LocalDateTime updateTime;

    /**
     * 乐观锁
     */
    @TableField("version")
    @Version //乐观锁注解
    private Integer version;

    /**
     * 逻辑删除字段
     */
    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
