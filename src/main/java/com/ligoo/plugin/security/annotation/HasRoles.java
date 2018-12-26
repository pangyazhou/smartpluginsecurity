package com.ligoo.plugin.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Administrator
 * @Date: 2018/12/25 15:49:54
 * @Description: 判断当前用户是否有某种角色
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HasRoles {
    // 角色名字符串
    String value();
}
