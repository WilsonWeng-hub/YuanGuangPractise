package com.wilson.controller;


import com.wilson.common.lang.Result;
import com.wilson.entity.User;
import com.wilson.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wilson
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/index")
    @RequiresAuthentication
    public Result index(){
        User user = userService.getById(1L);
//        return Result.succ(200,"请求成功",user);
        Result res = new Result();
        return res.succ(user);
    }

    @PostMapping("/save")
    public Result save( @Validated @RequestBody User user){

        return Result.succ(user);
    }
}
