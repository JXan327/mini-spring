package com.minispring.examples;

import com.minispring.aop.*;
import com.minispring.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * AOP基本使用示例
 * 演示如何使用Mini-Spring的AOP功能进行面向切面编程
 */
public class AopBasicExample {

    public static void main(String[] args) {
        System.out.println("=== Mini-Spring AOP示例 ===\n");

        // 创建目标对象
        UserService target = new UserService();

        // 创建代理工厂
        ProxyFactory proxyFactory = new ProxyFactory(target);

        // 添加前置通知
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("[前置通知] 方法即将执行: " + method.getName());
                if (args.length > 0) {
                    System.out.println("  参数: " + java.util.Arrays.toString(args));
                }
            }
        });

        // 添加后置通知
        proxyFactory.addAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                System.out.println("[后置通知] 方法执行完成: " + method.getName());
                if (returnValue != null) {
                    System.out.println("  返回值: " + returnValue);
                }
            }
        });

        // 获取代理对象
        UserService proxy = (UserService) proxyFactory.getProxy();

        // 使用代理对象
        System.out.println("调用普通方法:");
        proxy.sayHello("张三");

        System.out.println("\n调用带返回值的方法:");
        String result = proxy.getInfo();
        System.out.println("实际返回: " + result);

        System.out.println("\nAOP代理信息:");
        System.out.println("代理对象类型: " + proxy.getClass().getName());
        System.out.println("是否是Proxy: " + (proxy instanceof org.springframework.cglib.proxy.Proxy ? "CGLIB" : "JDK动态代理"));
    }

    /**
     * 用户服务类 - 作为AOP的目标对象
     */
    public static class UserService {

        public void sayHello(String name) {
            System.out.println("  → Hello, " + name + "!");
        }

        public String getInfo() {
            System.out.println("  → 获取用户信息...");
            return "用户信息数据";
        }

        public void privateMethod() {
            System.out.println("这是私有方法，不会被AOP拦截");
        }
    }
}
