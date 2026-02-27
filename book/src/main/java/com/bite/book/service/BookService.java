package com.bite.book.service;

import com.bite.book.enums.BookStatus;
import com.bite.book.mapper.BookMapper;
import com.bite.book.model.BookInfo;

import com.bite.book.model.PageRequest;
import com.bite.book.model.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service //Spring帮我们管理这个对象 IOC
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    @Deprecated
    //针对废弃的接口，如果是新项目，可以直接删除
    //如果是已经在线上运行的项目，不可轻易删除
    //加上这个注解可以告诉调用方，这个接口不用了
//    public List<BookInfo> getBookList() {
//        List<BookInfo> bookInfos = bookDao.mockData();
//        for (BookInfo bookInfo : bookInfos) {
//            if (bookInfo.getStatus() == 2) {
//                bookInfo.setStatusCN("不可借阅");
//            } else {
//                bookInfo.setStatusCN("可借阅");
//            }
//        }
//        return bookInfos;
//    }

    public Integer insertBook(BookInfo bookInfo) {
        return bookMapper.insertBook(bookInfo);
    }

    public PageResult<BookInfo> getBookListByPage(PageRequest pageRequest) {
        //1.获取总记录数
        Integer count = bookMapper.count();
        //2.获取当前页的记录
        List<BookInfo> bookInfos = bookMapper.queryBookByPage(pageRequest.getOffset(), pageRequest.getPageSize());
        //3.处理状态
        //4.状态 0-删除，1-可借阅，2-不可借阅
        for (BookInfo bookInfo : bookInfos) {
            bookInfo.setStatusCN(BookStatus.getDescByCode(bookInfo.getStatus()).getDesc());
        }
        return new PageResult<>(bookInfos, count, pageRequest);
    }

    public BookInfo queryBookById(Integer bookId) {
        BookInfo bookInfo = bookMapper.queryBookById(bookId);
        bookInfo.setStatusCN(BookStatus.getDescByCode(bookInfo.getStatus()).getDesc());
        return bookInfo;
    }

    public Integer updateBookById(BookInfo bookInfo) {
        return bookMapper.updateBookById(bookInfo);
    }

    public Integer deleteBook(Integer bookId) {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setId(bookId);
        bookInfo.setStatus(0);
        return bookMapper.updateBookById(bookInfo);
    }

    public Integer batchDeleteBookByIds(List<Integer> ids) {
        return bookMapper.batchDeleteBookByIds(ids);
    }
}
