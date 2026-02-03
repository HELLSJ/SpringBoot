package com.bite.book.controller;

import com.bite.book.BookInfo;
import com.bite.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
