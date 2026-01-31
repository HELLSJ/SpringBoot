package com.bite.book;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookInfo {
    private Integer id;
    private String bookName;
    private String author;
    private Integer num;
    private BigDecimal price;
    private String publishName;
    private Integer status; //1-正常，2-不可借阅
    private String statusCN;
}
