package com.example.generator.mapper;

import com.example.generator.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleInfoMapper {

    ArticleInfo selectArticleById(Integer id);
    ArticleInfo selectArticleAndUserById(Integer id);
}
