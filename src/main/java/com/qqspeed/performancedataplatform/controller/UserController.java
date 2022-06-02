package com.qqspeed.performancedataplatform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qqspeed.performancedataplatform.common.result.*;
import com.qqspeed.performancedataplatform.exception.BusinessException;
import com.qqspeed.performancedataplatform.model.domain.User;
import com.qqspeed.performancedataplatform.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.qqspeed.performancedataplatform.constant.UserConstant.ADMIN_ROLE;
import static com.qqspeed.performancedataplatform.constant.UserConstant.USER_LOGIN_STATE;

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
    public BaseResponse<Boolean> save(@RequestBody User user){
        boolean flag = userService.save(user);
        return ResultUtils.success(flag ? Code.SAVE_SUCCESS : Code.SAVE_FAILED,flag, Message.SAVE_SUCCESS_MSG);// 传递给前端状态码
    }

    /**
     * 通过id查询用户信息
     * @param user1
     * @return
     */
    @PostMapping("/info")
    public BaseResponse<User> searchUserInfo(@RequestBody User user1){
        if (user1 == null){
            return null;
        }
        Long userId = user1.getUserId();
        User user = userService.getUserInfo(userId);
        Integer code= user != null ? Code.SELECT_SUCCESS : Code.SELECT_FAILED;
        String msg = user != null ? "" : Message.SELECT_FAILED_MSG;
        return ResultUtils.success(code,user,msg);
    }


    /**
     * 通过用户名模糊查找用户
     * @param username
     * @return
     */
    @GetMapping("/search")
    public BaseResponse<List<User>> searchUser(@RequestBody String username, HttpServletRequest request){
        // 鉴权 仅管理员可查询
        if (!isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        // 通过用户名模糊查询
        if (StringUtils.isAnyBlank(username)){
            queryWrapper.like("username",username);
        }
        List<User> userList = userService.list(queryWrapper);
        // 信息脱敏
        List<User> result = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(Code.SELECT_SUCCESS,result,Message.SELECT_SUCCESS_MSG);
    }


    /**
     * 通过id更新用户信息
     * @param user
     * @return
     */
    @PatchMapping
    public BaseResponse<String> update(@RequestBody User user){
        if (user == null){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        boolean flag = userService.updateById(user);
        Integer code =flag ?Code.UPDATE_SUCCESS : Code.UPDATE_FAILED;
        String msg = flag ? Message.UPDATE_SUCCESS_MSG : Message.UPDATE_FAILED_MSG;
        return ResultUtils.success(code,msg);
    }

    /**
     * 根据id删除用户
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    public BaseResponse<String> deleteById(@PathVariable Long userId, HttpServletRequest request){
        if (userId <= 0){
            return ResultUtils.success(Code.DELETE_FAILED,Message.DELETE_FAILED_MSG);
        }
        // 鉴权 仅管理员可查询
        if (!isAdmin(request)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = userService.removeById(userId);
        return ResultUtils.success(Code.DELETE_SUCCESS,Message.DELETE_SUCCESS_MSG);
    }

//    @GetMapping("/current")
//    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
//        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
//        User currentUser = (User) userObj;
//        if (currentUser == null) {
//            throw new BusinessException(ErrorCode.NOT_LOGIN);
//        }
//        long userId = currentUser.getUserId();
//        // TODO 校验用户是否合法
//        User user = userService.getById(userId);
//        User safetyUser = userService.getSafetyUser(user);
//        return ResultUtils.success(safetyUser);
//    }
//
//    @GetMapping("/search")
//    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
//        if (!isAdmin(request)) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        if (StringUtils.isNotBlank(username)) {
//            queryWrapper.like("username", username);
//        }
//        List<User> userList = userService.list(queryWrapper);
//        List<User> list = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
//        return ResultUtils.success(list);
//    }
//
//    @PostMapping("/delete")
//    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
//        if (!isAdmin(request)) {
//            throw new BusinessException(ErrorCode.NO_AUTH);
//        }
//        if (id <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        boolean b = userService.removeById(id);
//        return ResultUtils.success(b);
//    }
//
//


    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        // 仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getPermission() == ADMIN_ROLE;
    }


}

