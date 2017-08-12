package com.nazimodi.java.study.basic;

import com.nazimodi.java.study.basic.dynamic.proxy.CountService;
import com.nazimodi.java.study.basic.dynamic.proxy.CountServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.DecimalFormat;


/**
 * @author renqingwang on 2017/8/12.
 * @version 1.0
 */
public class DynamicProxy {
    public static void main(String[] args) throws Exception{
        CountService delegate = new CountServiceImpl();
        long time = System.currentTimeMillis();
        CountService jdkProxy = createJdkDynamicProxy(delegate);
        time = System.currentTimeMillis() - time;
        System.out.print("create jdk proxy: " + time + "ms\n");

        time = System.currentTimeMillis();
        CountService cglibProxy = createCglibDynamicProxy(delegate);
        time = System.currentTimeMillis() - time;
        System.out.print("create cglib proxy:" + time + "ms\n");
        for(int i =0; i< 3; i++) {
            test(delegate, "run decorate:");
            test(jdkProxy, "run jdk proxy:");
            test(cglibProxy, "run cglib proxy:");
            System.out.print("---------------------------\n");
        }
    }

    private static CountService createJdkDynamicProxy(final CountService delegate) {
        CountService jdkProxy = (CountService) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{CountService.class}, new JdkHandler(delegate));
        return jdkProxy;
    }

    private static class JdkHandler implements InvocationHandler {
        final Object delegate;

        JdkHandler(Object delegate) {
            this.delegate = delegate;
        }

        public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
            return method.invoke(delegate, objects);
        }
    }

    private static CountService createCglibDynamicProxy(final CountService delegate){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CountServiceImpl.class);
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return methodProxy.invokeSuper(o, objects);
            }
        });
        CountService cglibProxy = (CountService) enhancer.create();
        return cglibProxy;
    }

    private static void test(CountService countService, String label) {
        countService.count();//warm up
        int count = 100000000;
        long time = System.currentTimeMillis();
        for (int i = 0; i< count; i++) {
            countService.count();
        }
        time = System.currentTimeMillis() - time;
        System.out.print(label + time + "ms " + new DecimalFormat().format(count * 1000 / time) + "t/s\n");

    }
}
