package com.example.springblog.mapper;

import com.example.springblog.model.BlogInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BlogMapperTest {
    @Autowired
    private BlogMapper blogMapper;

    @Test
    void selectAll() {
        System.out.println(blogMapper.selectAll());
    }

    @Test
    void selectById() {
        System.out.println(blogMapper.selectById(1));
    }

    @Test
    void updateBlog() {
        BlogInfo blogInfo = new BlogInfo();
        //更新博客
        blogInfo.setTitle("333goodtest");
        blogInfo.setContent("232141test***");
        blogInfo.setUserId(2);
        //删除博客，将delete_flag设置为1
        blogInfo.setId(3);
        blogInfo.setDeleteFlag(1);
        blogMapper.updateBlog(blogInfo);
    }

    @Test
    void insertBlog() {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setTitle("goodtest");
        blogInfo.setContent("test***");
        blogInfo.setUserId(1);
        blogMapper.insertBlog(blogInfo);
    }
}