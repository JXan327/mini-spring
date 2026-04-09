package com.minispring.beans;

/**
 * Bean创建异常
 * 当Bean创建过程中出现错误时抛出此异常
 */
public class BeanCreationException extends BeansException {

    private String beanName;

    public BeanCreationException(String message) {
        super(message);
    }

    public BeanCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanCreationException(String beanName, String message) {
        super("Error creating bean with name '" + beanName + "': " + message);
        this.beanName = beanName;
    }

    public BeanCreationException(String beanName, String message, Throwable cause) {
        super("Error creating bean with name '" + beanName + "': " + message, cause);
        this.beanName = beanName;
    }

    public String getBeanName() {
        return this.beanName;
    }
}
