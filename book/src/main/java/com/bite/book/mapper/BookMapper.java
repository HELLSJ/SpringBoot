package com.bite.book.mapper;

import com.bite.book.model.BookInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {
    /**
     * 插入图书
     */
    @Insert("isnert into book_info (book_name, author, count, price, publish, `status`) " +
            "values (#{bookName}, #{author}, #{count}, #{price}, #{publish}, #{status})")
    Integer insertBook(BookInfo bookInfo);
}
