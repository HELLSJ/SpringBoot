package com.example.mybatisdemo.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleInfoMapperTest {

    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    @Test
    void selectArticleById() {
        System.out.println(articleInfoMapper.selectArticleById(1));
    }

    @Test
    void selectArticleAndUserById() {
        System.out.println(articleInfoMapper.selectArticleAndUserById(1));
    }
}