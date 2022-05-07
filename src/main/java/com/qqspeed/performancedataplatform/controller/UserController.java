package com.qqspeed.performancedataplatform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qqspeed.performancedataplatform.contant.result.Code;
import com.qqspeed.performancedataplatform.contant.result.Message;
import com.qqspeed.performancedataplatform.contant.result.Result;
import com.qqspeed.performancedataplatform.model.domain.User;
import com.qqspeed.performancedataplatform.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.qqspeed.performancedataplatform.contant.UserConstant.ADMIN_ROLE;
import static com.qqspeed.performancedataplatform.contant.UserConstant.USER_LOGIN_STATE;

/**
 * <p>
 *  用户管理
 * </p>
 *
 * @author djiehuang
 * @since 2022-04-30
 */
@RestController
@RequestMapping("/users")
public class UserController {


    /**
     *     GET（SELECT）：从服务器取出资源（一项或多项）。
     *     POST（CREATE）：在服务器新建一个资源。
     *     PUT（UPDATE）：在服务器更新资源（客户端提供完整资源数据）。
     *     PATCH（UPDATE）：在服务器更新资源（客户端提供需要修改的资源数据）。
     *     DELETE（DELETE）：从服务器删除资源。
     */
    @Resource
    private UserService userService;

//    @RequestMapping(method = RequestMethod.POST) //等价@PostMapping

    /**
     * 用户信息保存方法
     * @param user
     * @return
     */
    @PostMapping //POST请求
    public Result save(@RequestBody User user){
        boolean flag = userService.save(user);
        return new Result(flag ? Code.SAVE_SUCCESS : Code.SAVE_FAILED,flag, Message.SAVE_SUCCESS_MSG) ;// 传递给前端状态码
    }

    /**
     * 通过id查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/search/{id}")
    public Result searchUserById(@PathVariable Integer id){
        User user = userService.getById(id);
        Integer code= user != null ? Code.SELECT_SUCCESS : Code.SELECT_FAILED;
        String msg = user != null ? "" : Message.SELECT_FAILED_MSG;
        return new Result(code,user,msg);
    }

    /**
     * 通过用户名模糊查找用户
     * @param username
     * @return
     */
    @GetMapping("/search")
    public List<User> searchUser(String username, HttpServletRequest request){
        // 鉴权 仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user =(User) userObj;
        // 判断用户是否是普通用户
        if (user == null || user.getPermission() != ADMIN_ROLE){
            return new ArrayList<>();
        }
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        if (StringUtils.isAnyBlank(username)){
            queryWrapper.like("user_name",username);
        }
        return userService.list(queryWrapper);
    }


    /**
     * 通过id更新用户信息
     * @param user
     * @return
     */
    @PatchMapping
    public Result update(@RequestBody User user){
        boolean flag = userService.updateById(user);
        String msg = flag  ? "" : Message.UPDATE_SUCCESS_MSG;
        return new Result(flag ? Code.UPDATE_SUCCESS : Code.UPDATE_FAILED,flag);
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id,HttpServletRequest request){
        if (id <= 0){
            return new  Result(Code.DELETE_FAILED,null);
        }
        // 鉴权 仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user =(User) userObj;
        // 判断用户是否是普通用户
        if (user == null || user.getPermission() != ADMIN_ROLE){
            return new Result();
        }
        boolean flag = userService.removeById(id);
        String msg = flag  ? "" : Message.DELETE_SUCCESS_MSG;
        return new Result(flag ? Code.DELETE_SUCCESS : Code.DELETE_FAILED,flag,msg);
    }

}
