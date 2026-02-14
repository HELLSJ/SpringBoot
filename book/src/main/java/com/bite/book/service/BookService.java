package com.bite.book.service;

import com.bite.book.mapper.BookMapper;
import com.bite.book.model.BookInfo;
import com.bite.book.dao.BookDao;
import com.bite.book.model.PageRequest;
import com.bite.book.model.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component //Spring帮我们管理这个对象 IOC
public class BookService {
    @Autowired //从Spring中拿到这个对象 DI
    private BookDao bookDao;
    @Autowired
    private BookMapper bookMapper;
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

    public Integer insertBook(BookInfo bookInfo) {
        return bookMapper.insertBook(bookInfo);
    }

    public PageResult<BookInfo> getBookListByType(PageRequest pageRequest) {
        //1.获取总记录数
        Integer count = bookMapper.count();
        //2.获取当前页的记录
        List<BookInfo> bookInfos = bookMapper.queryBookByPage(pageRequest.getOffset(), pageRequest.getPageSize());
        return new PageResult<>(bookInfos,count);
    }
}
