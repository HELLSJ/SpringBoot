package com.bite.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor


@Data
public class PageResult<T> {
    private int code; //业务吗200成功，-1失败，-2未登录
    private String msg;
    private List<T> records;
    private Integer count;
    private PageRequest pageRequest;
}
