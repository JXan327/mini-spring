package com.minispring.aop;

/**
 * AOP配置异常
 * 当AOP配置出现问题时抛出此异常
 */
public class AopConfigException extends RuntimeException {

    public AopConfigException(String message) {
        super(message);
    }

    public AopConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
