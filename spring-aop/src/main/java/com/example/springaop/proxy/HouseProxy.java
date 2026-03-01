package com.example.springaop.proxy;

/**
 * 静态代理的代理类
 */
public class HouseProxy implements HouseSubject{
    private HouseSubject target;

    public HouseProxy(HouseSubject target) {
        this.target = target;
    }

    @Override
    public void rentHouse() {
        //出租前
        System.out.println("我是中介, 开始代理");
        //出租房子
        target.rentHouse();
        //出租后
        System.out.println("我是中介, 结束代理");
    }

    @Override
    public void saleHouse() {
        //出租前
        System.out.println("我是中介, 开始代理");
        //出租房子
        target.saleHouse();
        //出租后
        System.out.println("我是中介, 结束代理");
    }
}
