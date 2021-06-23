package com.wilson.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wilson.common.lang.Result;
import com.wilson.entity.Blog;
import com.wilson.service.BlogService;
import com.wilson.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wilson
 * @since 2021-06-16
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage,5);
        Page pageData = blogService.page(page,new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.succ(pageData);
    }


    @GetMapping("/blog/{id}")
    public Result detail(@RequestParam(defaultValue = "id") Long id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客已经被删除");
        return Result.succ(blog);
    }


    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated  @RequestBody Blog blog){
        Blog tmp = null;
        if (blog.getId() != null){
//            处于编辑状态,自能编辑自己的文章
            tmp = blogService.getById(blog.getId());
            //抛出IllegalArgumentException
            Assert.isTrue(tmp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(),"你没有权限编辑");
        }else {
            tmp = new Blog();
            tmp.setUserId(ShiroUtil.getProfile().getId());
            tmp.setCreated(LocalDateTime.now());
            tmp.setStatus(0);
        }
//        保存到数据库
        BeanUtil.copyProperties(blog, tmp, "id","userId","created","status");
        blogService.saveOrUpdate(tmp);
        return Result.succ(null);
    }
}
