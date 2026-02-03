package com.bite.book.service;

import com.bite.book.BookInfo;
import com.bite.book.dao.BookDao;

import java.util.List;

public class BookService {
    public List<BookInfo> getBookList(){
        BookDao bookDao = new BookDao();
        List<BookInfo> bookInfos = bookDao.mockData();
        for(BookInfo bookInfo: bookInfos){
            if(bookInfo.getStatus() == 2){
                bookInfo.setStatusCN("不可借阅");
            }else{
                bookInfo.setStatusCN("可借阅");
            }
        }
        return bookInfos;
    }
}
