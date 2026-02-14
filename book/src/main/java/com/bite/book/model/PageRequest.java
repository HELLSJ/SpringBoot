package com.bite.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class PageRequest {
    private Integer pageNum;
    private Integer pageSize = 10;
    private Integer offset;

    // 偏移量 = (当前页码 - 1) * 每页条数
    // pageNum=1，pageSize=10 → (1-1)*10=0（查第1页，从第0条开始）
    // pageNum=3，pageSize=10 → (3-1)*10=20（查第3页，从第20条开始）
    public Integer getOffset() {
        return (pageNum-1)*pageSize;
    }
}
