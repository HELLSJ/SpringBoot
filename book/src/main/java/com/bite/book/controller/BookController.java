package com.bite.book.controller;

import com.bite.book.BookInfo;
import com.bite.book.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/book")
@RestController
public class BookController {
    @RequestMapping("/getBookList")
    public List<BookInfo> getBookList(){
        BookService bookService = new BookService();
        return bookService.getBookList();
    }
}
