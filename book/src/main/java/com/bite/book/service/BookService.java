package com.bite.book.service;

import com.bite.book.BookInfo;
import com.bite.book.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component //Spring帮我们管理这个对象 IOC
public class BookService {
    @Autowired //从Spring中拿到这个对象 DI
    private BookDao bookDao;
    public List<BookInfo> getBookList(){
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
