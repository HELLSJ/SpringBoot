package com.example.springaop.proxy;

/**
 * 业务实现类
 */
public class RealHouseSubject implements HouseSubject{
    @Override
    public void rentHouse() {
        System.out.println("我是房东, 我出租房子");
    }

    @Override
    public void saleHouse() {
        System.out.println("我是房东, 我出售房子");
    }
}
