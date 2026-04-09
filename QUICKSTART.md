# Mini-Spring 快速入门指南

## 5分钟上手Mini-Spring

### 步骤1: 创建项目

确保你的项目包含以下依赖：
```xml
<dependency>
    <groupId>com.kama</groupId>
    <artifactId>mini-spring</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### 步骤2: 定义Bean类

```java
package com.example;

public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(String username) {
        userDao.save(username);
    }
}

public class UserDao {
    public void save(String username) {
        System.out.println("保存用户: " + username);
    }
}
```

### 步骤3: 创建配置文件

在 `resources/applicationContext.xml` 中：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userDao" class="com.example.UserDao"/>

    <bean id="userService" class="com.example.UserService">
        <property name="userDao" ref="userDao"/>
    </bean>

</beans>
```

### 步骤4: 使用容器

```java
import com.minispring.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // 启动容器
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        // 获取Bean
        UserService userService = context.getBean("userService", UserService.class);

        // 使用Bean
        userService.addUser("张三");

        // 关闭容器
        context.close();
    }
}
```

### 运行结果

```
保存用户: 张三
```

## 常用配置示例

### 构造器注入

```xml
<bean id="userService" class="com.example.UserService">
    <constructor-arg index="0" ref="userDao"/>
    <constructor-arg index="1" value="默认值"/>
</bean>
```

### Bean作用域

```xml
<!-- 单例模式（默认） -->
<bean id="singletonBean" class="com.example.SingletonBean" scope="singleton"/>

<!-- 原型模式：每次获取创建新实例 -->
<bean id="prototypeBean" class="com.example.PrototypeBean" scope="prototype"/>
```

### 初始化和销毁

```xml
<bean id="lifecycleBean"
      class="com.example.LifecycleBean"
      init-method="init"
      destroy-method="destroy"/>
```

```java
public class LifecycleBean {
    public void init() {
        System.out.println("初始化");
    }

    public void destroy() {
        System.out.println("销毁");
    }
}
```

### 使用Aware接口

```java
import com.minispring.beans.factory.BeanNameAware;
import com.minispring.beans.factory.BeanFactoryAware;

public class AwareBean implements BeanNameAware, BeanFactoryAware {

    @Override
    public void setBeanName(String name) {
        System.out.println("我的Bean名称是: " + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        System.out.println("我已经注入了BeanFactory");
    }
}
```

## AOP快速入门

### 步骤1: 创建切面

```java
import com.minispring.aop.BeforeAdvice;
import com.minispring.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class LoggingAspect implements BeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("[日志] 方法执行: " + method.getName());
    }
}
```

### 步骤2: 创建代理

```java
// 创建目标对象
UserService target = new UserService();

// 创建代理工厂
ProxyFactory factory = new ProxyFactory(target);
factory.addAdvice(new LoggingAspect());

// 获取代理对象
UserService proxy = (UserService) factory.getProxy();

// 使用代理对象
proxy.addUser("李四");
```

## 下一步学习

- 📖 阅读 [开发指南](DEVELOPMENT.md) 了解更多细节
- 🎯 查看 [示例代码](src/test/java/com/minispring/examples/) 学习最佳实践
- 🔧 参考 [测试用例](src/test/java/com/minispring/test/) 了解各种功能

## 常见问题

**Q: 如何调试Bean创建过程？**
A: 在logback.xml中设置com.minispring包的日志级别为DEBUG

**Q: 循环依赖怎么处理？**
A: 使用setter注入替代构造器注入，框架会自动解决循环依赖

**Q: AOP对private方法有效吗？**
A: 无效，AOP只能代理public方法

需要更多帮助？请查看完整的[开发文档](DEVELOPMENT.md)或提交Issue。
