package com.bite.book.controller;

import com.bite.book.model.BookInfo;
import com.bite.book.model.PageRequest;
import com.bite.book.model.PageResult;
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
    @RequestMapping("/getBookListByPage")
    public PageResult<BookInfo> getBookListByPage(PageRequest pageRequest){
        log.info("查询图书列表，请求参数pageRequest: {}", pageRequest);
        return bookService.getBookListByPage(pageRequest);
    }

    /**
     * 查询图书信息
     * @param bookId
     * @return
     */
    @RequestMapping("/queryBookById")
    public BookInfo queryBookById(Integer bookId){
        log.info("根据ID查询图书信息，id:"+bookId);
        return bookService.queryBookById(bookId);
    }

    /**
     * 更新图书
     */
    @RequestMapping("/updateBook")
    public String updateBook(BookInfo bookInfo){
        log.info("更新图书，bookInfo: {}", bookInfo);
        try{
            Integer result = bookService.updateBookById(bookInfo);
            if(result > 0){
                return "";
            }
            return "内部错误";
        }catch(Exception e){
            log.error("更新图书失败：e", e);
            return "更新图书失败："+ e.getMessage();
        }
    }
}
