package com.qqspeed.performancedataplatform.controller;

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

    @Autowired
    private UserService userService;

    // TEST 记录下 后面删除
//    @RequestMapping(method = RequestMethod.POST)
    @PostMapping //POST请求
    public Result save(@RequestBody User user){
        boolean flag = userService.save(user);
        return new Result(flag ? Code.SAVE_SUCCESS : Code.SAVE_FAILED,flag) ;// 传递给前端状态码
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        User user = userService.getById(id);
        Integer code= user != null ? Code.SELECT_SUCCESS : Code.SELECT_FAILED;
        String msg = user != null ? "" : "用户信息查询失败！请重试！";
        return new Result(code,user,msg);
    }

}
