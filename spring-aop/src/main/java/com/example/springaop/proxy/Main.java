package com.example.springaop.proxy;

import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        //静态代理
//        HouseProxy proxy = new HouseProxy(new RealHouseSubject());
//        proxy.rentHouse();

        //JDK动态代理
        /**
         * newProxyInstance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler h)
         *  loader 加载我们的代理类的classload
         *  interfaces: 要实现的接口
         *  h: 代理要做的事情, 需要实现 InvocationHandler 这个接口
         *
         */
        //目标对象
//        RealHouseSubject target = new RealHouseSubject();
        //动态生成代理对象  代理接口, 运行成功
//        HouseSubject proxy= (HouseSubject)Proxy.newProxyInstance(
//                HouseSubject.class.getClassLoader(),
//                new Class[]{HouseSubject.class},
//                new JDKInvocationHandler(target));
//        proxy.rentHouse();
//        proxy.saleHouse();

            //JDK代理类, 运行失败
//        RealHouseSubject proxy= (RealHouseSubject)Proxy.newProxyInstance(
//                RealHouseSubject.class.getClassLoader(),
//                new Class[]{RealHouseSubject.class},
//                new JDKInvocationHandler(target));
//        proxy.rentHouse();
//        proxy.saleHouse();

        // 使用CGLib 完成代理
        //目标对象
        HouseSubject target = new RealHouseSubject();
        //代理接口, 运行成功
//        HouseSubject houseSubject = (HouseSubject)Enhancer.create(target.getClass(), new CGLibMethodInterceptor(target));
//        houseSubject.rentHouse();
//        houseSubject.saleHouse();

        //代理类  运行成功
        RealHouseSubject houseSubject = (RealHouseSubject)Enhancer.create(target.getClass(), new CGLibMethodInterceptor(target));
        houseSubject.rentHouse();
        houseSubject.saleHouse();


    }
}
