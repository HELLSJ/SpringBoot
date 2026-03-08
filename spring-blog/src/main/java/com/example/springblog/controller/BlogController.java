package com.example.springblog.controller;

import com.example.springblog.constants.Constants;
import com.example.springblog.mapper.BlogMapper;
import com.example.springblog.model.BlogInfo;
import com.example.springblog.service.BlogService;
import com.example.springblog.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RequestMapping("/blog")
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/getList")
    public List<BlogInfo> getList(){
        log.info("获取博客列表。。");
        return blogService.getList();
    }

    @RequestMapping("/getBlogDetail")
    public BlogInfo getBlogDetail(Integer blogId){
        log.info("getBlogDetail, blogId: {}", blogId);
        return blogService.getBlogDetail(blogId);
    }

    //使用json来请求
    @RequestMapping("/add")
    public Boolean addBook(@RequestBody BlogInfo blogInfo, HttpServletRequest request){
        log.info("添加图书,blogInfo:{}", blogInfo);
        //参数校验
        if(!StringUtils.hasLength(blogInfo.getTitle()) || !StringUtils.hasLength(blogInfo.getContent())){
            return false;
        }
        //获取登录用户
        //获取token
        String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
        Integer userId = JWTUtils.getIdByToken(token);
        blogService.insertBook(blogInfo);
        return true;
    }
}
