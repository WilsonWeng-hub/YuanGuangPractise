package com.wilson.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wilson.common.dto.LoginDto;
import com.wilson.common.lang.Result;
import com.wilson.entity.User;
import com.wilson.service.UserService;
import com.wilson.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * FileName:   vuelog
 * Author:     Ir-verng
 * Date:       2021/6/22   11:37
 * Description:login & logout
 */
@RestController
public class AccountController {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse){
        User user = userService.getOne(new QueryWrapper<User>().eq("username",loginDto.getUsername()));
//      return  IllegalArgumentException 添加异常处理
        Assert.notNull(user,"用户不存在");
//      登录成功，返回Token
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            return Result.fail("密码错误");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        httpServletResponse.setHeader("Authorization",jwt);
        httpServletResponse.setHeader("Access-control-Expose-Headers","Authorization");
        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("userName", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
}
