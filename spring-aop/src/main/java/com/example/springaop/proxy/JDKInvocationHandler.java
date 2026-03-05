package com.example.springaop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKInvocationHandler implements InvocationHandler {
    private Object target;//目标对象

    public JDKInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 代理对象  通过 invoke调用  目标对象的方法
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("我是代理, 开始代理");
        //通过反射, 调用目标对象的方法
        Object result = method.invoke(target, args);
        System.out.println("我是代理, 结束代理");
        return result;
    }
}
