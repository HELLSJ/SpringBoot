package com.bite.book.controller;

import com.bite.book.model.BookInfo;
import com.bite.book.model.PageRequest;
import com.bite.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired //从Spring中拿到这个对象 DI
    private BookService bookService;
    @RequestMapping("/getBookList")
    public List<BookInfo> getBookList(){
//        BookService bookService = new BookService();

        return bookService.getBookList();
    }
    /**
     * 添加图书
     */
    @RequestMapping("/addBook")
    public String addBook(BookInfo bookInfo){
        //校验参数
        log.info("添加图书，接收到参数：bookInfo {}", bookInfo);
        if(!StringUtils.hasLength(bookInfo.getBookName())
                || !StringUtils.hasLength(bookInfo.getAuthor())
                || bookInfo.getCount() == null
                || bookInfo.getPrice() == null
                || !StringUtils.hasLength(bookInfo.getPublish())
                || bookInfo.getStatus() == null){
            return "参数不合法";
        }
        //参数校验成功，添加图书
        try {
            Integer result = bookService.insertBook(bookInfo);
            if(result > 0){
                return "";
            }
        }catch (Exception e){
            log.error("添加图书异常，e:", e);
            return e.getMessage();
        }
        return "添加失败";
    }
    /**
     * 查询图书列表
     */
    @RequestMapping("/getBookListByType")
    public List<BookInfo> getBookListByType(PageRequest pageRequest){
        return bookService.getBookListByType(pageRequest);
    }
}
