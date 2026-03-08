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
        log.info("获取博客列表..");
        return blogService.getList();
    }

    @RequestMapping("/getBlogDetail")
    public BlogInfo getBlogDetail(Integer blogId, HttpServletRequest request){
        log.info("getBlogDetail, blogId: {}", blogId);
        BlogInfo blogDetail = blogService.getBlogDetail(blogId);
        //获取token
        String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
        //从token中获取登录用户ID
        Integer userId = JWTUtils.getIdByToken(token);
        //判断作者是否为登录用户
        if(userId != null && userId == blogDetail.getUserId()){
            blogDetail.setLoginUser(true);
        }else{
            blogDetail.setLoginUser(false);
        }
        return blogDetail;
    }

    //使用json来请求
    @RequestMapping("/add")
    public Boolean addBook(@RequestBody BlogInfo blogInfo, HttpServletRequest request){
        log.info("添加博客,blogInfo:{}", blogInfo);
        //参数校验
        if(!StringUtils.hasLength(blogInfo.getTitle()) || !StringUtils.hasLength(blogInfo.getContent())){
            return false;
        }
        //获取登录用户
        //获取token
        String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
        Integer userId = JWTUtils.getIdByToken(token);
        if(userId == null || userId<0){
            return false;
        }
        //设置用户ID
        blogInfo.setUserId(userId);
        //设置创建时间和更新时间
        blogInfo.setCreateTime(new java.util.Date());
        blogInfo.setUpdateTime(new java.util.Date());
        blogService.insertBook(blogInfo);
        return true;
    }
    @RequestMapping("/update")
    public Boolean update(BlogInfo blogInfo){
        log.info("更新博客, blogInfo:{}", blogInfo);
        //参数校验
        if(blogInfo.getId()==null
                ||!StringUtils.hasLength(blogInfo.getTitle())
                ||!StringUtils.hasLength(blogInfo.getContent())){
            return false;
        }
        blogService.update(blogInfo);
        return true;
    }

    @RequestMapping("/delete")
    public Boolean delete(Integer blogId){
        log.info("删除博客, blogId:{}", blogId);
        if(blogId<=0){
            return false;
        }
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setId(blogId);
        blogInfo.setDeleteFlag(1);//删除
        blogService.update(blogInfo);
        return true;
    }
}
