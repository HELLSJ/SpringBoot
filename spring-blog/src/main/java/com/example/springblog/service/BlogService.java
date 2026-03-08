package com.example.springblog.service;

import com.example.springblog.config.ExceptionHandle;
import com.example.springblog.mapper.BlogMapper;
import com.example.springblog.model.BlogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
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

    public Boolean insertBook(BlogInfo blogInfo) {
        try{
            Integer result = blogMapper.insertBlog(blogInfo);
            if(result==1){
                return true;
            }
        }catch (Exception e){
            log.error("添加图书失败, e:{}", e);
        }
        return false;
    }

    public Integer update(BlogInfo blogInfo) {
        return blogMapper.updateBlog(blogInfo);//需要异常捕获
    }
}
