package com.minispring.beans;

/**
 * Bean获取异常
 * 当无法获取Bean实例时抛出此异常
 */
public class CannotGetBeanException extends BeansException {

    public CannotGetBeanException(String message) {
        super(message);
    }

    public CannotGetBeanException(String message, Throwable cause) {
        super(message, cause);
    }
}
