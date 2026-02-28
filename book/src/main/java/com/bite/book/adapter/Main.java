package com.bite.book.adapter;

public class Main {
    public static void main(String[] args) {
        Slf4jLog slf4jLog = new Log4jAdapter(new Log4j());
        slf4jLog.log("我是客户端");
    }
}
