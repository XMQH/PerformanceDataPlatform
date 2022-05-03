package com.qqspeed.performancedataplatform.controller;

import com.qqspeed.performancedataplatform.controller.result.Message;
import com.qqspeed.performancedataplatform.entity.User;
import com.qqspeed.performancedataplatform.controller.result.Code;
import com.qqspeed.performancedataplatform.controller.result.Result;
import com.qqspeed.performancedataplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
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
    @Autowired
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
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        User user = userService.getById(id);
        Integer code= user != null ? Code.SELECT_SUCCESS : Code.SELECT_FAILED;
        String msg = user != null ? "" : Message.SELECT_FAILED_MSG;
        return new Result(code,user,msg);
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
    public Result deleteById(@PathVariable Integer id){
        boolean flag = userService.removeById(id);
        String msg = flag  ? "" : Message.DELETE_SUCCESS_MSG;
        return new Result(flag ? Code.DELETE_SUCCESS : Code.DELETE_FAILED,flag,msg);
    }

}
