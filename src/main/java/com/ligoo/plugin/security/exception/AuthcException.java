package com.ligoo.plugin.security.exception;

/**
 * @Author: Administrator
 * @Date: 2018/12/24 14:46:13
 * @Description:  认证异常(非法访问时抛出)
 */
public class AuthcException extends Exception {
    public AuthcException() {
    }

    public AuthcException(String message) {
        super(message);
    }

    public AuthcException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthcException(Throwable cause) {
        super(cause);
    }
}
