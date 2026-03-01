package com.example.springaop.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibMethodInterceptor implements MethodInterceptor {
    private Object target;

    public CGLibMethodInterceptor(Object target) {
        this.target = target;
    }

    /**
     * 调用代理对象的方法
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("我是中介, 开始代理");
        Object result = method.invoke(target, args);
        System.out.println("我是中介, 结束代理");
        return result;
    }
}
