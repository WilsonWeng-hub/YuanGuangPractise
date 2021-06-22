package com.wilson.service.impl;

import com.wilson.entity.Blog;
import com.wilson.mapper.BlogMapper;
import com.wilson.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wilson
 * @since 2021-06-16
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
