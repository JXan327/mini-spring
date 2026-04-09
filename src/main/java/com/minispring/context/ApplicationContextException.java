package com.minispring.context;

import com.minispring.beans.BeansException;

/**
 * 应用上下文异常
 * 当ApplicationContext操作出现错误时抛出此异常
 */
public class ApplicationContextException extends BeansException {

    public ApplicationContextException(String message) {
        super(message);
    }

    public ApplicationContextException(String message, Throwable cause) {
        super(message, cause);
    }
}
