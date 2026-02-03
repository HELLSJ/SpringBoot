package com.bite.book.dao;

import com.bite.book.BookInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public List<BookInfo> mockData(){
        List<BookInfo> bookInfos = new ArrayList<>();
        //mock数据（模拟数据）
        for (int i = 1; i <= 15; i++) {
            BookInfo bookInfo = new BookInfo();
            bookInfo.setId(i);
            bookInfo.setBookName("图书"+i);
            bookInfo.setAuthor("作者"+i);
            bookInfo.setNum(i*2+1);
            bookInfo.setPrice(new BigDecimal(i*3));
            bookInfo.setPublishName("出版社"+i);
            if(i%5==0){
                bookInfo.setStatus(2);
                bookInfo.setStatusCN("不可借阅");
            }else{
                bookInfo.setStatus(1);
                bookInfo.setStatusCN("可借阅");
            }
            bookInfos.add(bookInfo);
        }
        return bookInfos;
    }
}
