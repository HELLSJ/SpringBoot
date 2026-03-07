package com.example.springblog.service;

import com.example.springblog.mapper.BlogMapper;
import com.example.springblog.model.BlogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;

    public List<BlogInfo> getList() {
        return blogMapper.selectAll();
    }

    public BlogInfo getBlogDetail(Integer blogId) {
        return blogMapper.selectById(blogId);
    }
}
