package com.ligoo.plugin.security.exception;

/**
 * @Author: Administrator
 * @Date: 2018/12/24 14:48:23
 * @Description: 授权异常(当权限无效时抛出)
 */
public class AuthzException extends Exception {
    public AuthzException() {
    }

    public AuthzException(String message) {
        super(message);
    }

    public AuthzException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthzException(Throwable cause) {
        super(cause);
    }
}
