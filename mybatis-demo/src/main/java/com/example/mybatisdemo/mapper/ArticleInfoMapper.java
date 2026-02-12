package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleInfoMapper {

    ArticleInfo selectArticleById(Integer id);
    ArticleInfo selectArticleAndUserById(Integer id);
}
