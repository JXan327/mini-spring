package com.minispring.examples;

import com.minispring.beans.factory.BeanFactory;
import com.minispring.beans.factory.BeanFactoryAware;
import com.minispring.beans.factory.BeanNameAware;
import com.minispring.beans.factory.DisposableBean;
import com.minispring.beans.factory.InitializingBean;
import com.minispring.beans.factory.config.BeanPostProcessor;
import com.minispring.context.support.ClassPathXmlApplicationContext;

/**
 * Bean生命周期示例
 * 演示Mini-Spring中Bean的完整生命周期
 */
public class LifecycleExample {

    public static void main(String[] args) {
        System.out.println("=== Mini-Spring Bean生命周期示例 ===\n");

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:examples-lifecycle.xml");

        System.out.println("\n=== 容器启动完成，获取Bean ===");
        LifecycleBean bean = context.getBean("lifecycleBean", LifecycleBean.class);
        bean.doWork();

        System.out.println("\n=== 关闭容器 ===");
        context.close();
    }

    /**
     * 自定义BeanPostProcessor
     */
    public static class CustomBeanPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) {
            if (bean instanceof LifecycleBean) {
                System.out.println("[BeanPostProcessor] 前置处理: " + beanName);
            }
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) {
            if (bean instanceof LifecycleBean) {
                System.out.println("[BeanPostProcessor] 后置处理: " + beanName);
            }
            return bean;
        }
    }

    /**
     * 演示完整生命周期的Bean
     */
    public static class LifecycleBean implements BeanNameAware, BeanFactoryAware,
            InitializingBean, DisposableBean {

        private String beanName;
        private BeanFactory beanFactory;
        private String message;

        public void setMessage(String message) {
            System.out.println("[LifecycleBean] 设置属性: message = " + message);
            this.message = message;
        }

        @Override
        public void setBeanName(String name) {
            System.out.println("[BeanNameAware] 设置Bean名称: " + name);
            this.beanName = name;
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) {
            System.out.println("[BeanFactoryAware] 设置BeanFactory");
            this.beanFactory = beanFactory;
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            System.out.println("[InitializingBean] 初始化回调");
        }

        public void customInit() {
            System.out.println("[自定义] 自定义初始化方法");
        }

        public void doWork() {
            System.out.println("[执行业务逻辑] Bean名称: " + beanName + ", 消息: " + message);
        }

        @Override
        public void destroy() throws Exception {
            System.out.println("[DisposableBean] 销毁回调");
        }

        public void customDestroy() {
            System.out.println("[自定义] 自定义销毁方法");
        }
    }
}
