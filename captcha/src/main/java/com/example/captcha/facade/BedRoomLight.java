package com.example.captcha.facade;

public class BedRoomLight implements Light{
    @Override
    public void on() {
        System.out.println("开启卧室灯");
    }

    @Override
    public void off() {
        System.out.println("关闭卧室灯");
    }
}
