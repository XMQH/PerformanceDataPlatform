package com.qqspeed.performancedataplatform.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author djiehuang
 * @since 2022-04-30
 */

@Data //相当于同时使用了@ToString、@EqualsAndHashCode、@Getter、@Setter和@RequiredArgsConstrutor这些注解
@AllArgsConstructor //生成对应的有参构造方法
@NoArgsConstructor //生成对应的无参构造方法
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 性别
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 用户权限
     */
    @TableField("permission")
    private Integer permission;

    /**
     * 用户状态
     */
    @TableField("state")
    private Integer state;

    /**
     * 用户电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 用户备注
     */
    @TableField("note")
    private String note;

    /**
     * 创建时间
     */
    @TableField(value = "gtm_create",fill = FieldFill.INSERT) //fill = FieldFill.INSERT 插入数据是自动填充
    private LocalDateTime gtmCreate;

    /**
     * 更新时间
     *
     * 今天学习的MyBatis-Plus的乐观锁的时候踩了一个坑，就是在实现乐观锁后，自动注入的更新时间失效了。
     * 原因是为了实现乐观锁，我们在更新数据前，必须先查找数据，而即便我们修改了updateTime字段之外的值，然后提交修改，如果updateTime字段原先就有数据，那么此时数据不变，也就是自动注入失效了。
     * 其实这个问题可以说并不是乐观锁导致的自动注入失效，只是从数据库中查询回来的数据，更新是updateTime会使用原有数据。
     * 解决办法就是在注解上添加update = "now()"
     */
    @TableField(value = "gtm_modified",fill = FieldFill.INSERT_UPDATE,update = "now()") //fill = FieldFill.INSERT_UPDATE 更新时自动填充
    private LocalDateTime gtmModified;

    /**
     * 版本(乐观锁)
     */
    @TableField("version")
    @Version //乐观锁注解
    private Integer version;

    /**
     * 逻辑删除字段
     */
    @TableField("deleted")
    @TableLogic //逻辑删除
    private Integer deleted;


}
