package com.minispring.examples;

import com.minispring.beans.factory.support.DefaultListableBeanFactory;
import com.minispring.beans.factory.xml.XmlBeanDefinitionReader;
import com.minispring.context.support.ClassPathXmlApplicationContext;

/**
 * IoC容器基本使用示例
 * 演示如何使用Mini-Spring的IoC容器进行依赖注入
 */
public class IoCBasicExample {

    public static void main(String[] args) {
        System.out.println("=== Mini-Spring IoC容器示例 ===\n");

        // 方式1: 使用BeanFactory
        System.out.println("方式1: 使用BeanFactory");
        System.out.println("-------------------");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:examples-ioc.xml");

        UserService userService1 = beanFactory.getBean("userService", UserService.class);
        userService1.addUser("张三");
        userService1.addUser("李四");

        // 方式2: 使用ApplicationContext
        System.out.println("\n方式2: 使用ApplicationContext");
        System.out.println("---------------------------");
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:examples-ioc.xml");

        UserService userService2 = context.getBean("userService", UserService.class);
        userService2.addUser("王五");

        System.out.println("\n容器中的所有Bean:");
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("- " + beanName);
        }

        context.close();
    }

    /**
     * 用户服务类 - 演示依赖注入
     */
    public static class UserService {
        private String appName;
        private UserRepository userRepository;

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public void setUserRepository(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public void addUser(String username) {
            System.out.println("[" + appName + "] 添加用户: " + username);
            if (userRepository != null) {
                userRepository.save(username);
            }
        }
    }

    /**
     * 用户仓库类 - 演示DAO模式
     */
    public static class UserRepository {
        public void save(String username) {
            System.out.println("  → 保存用户到数据库: " + username);
        }
    }
}
