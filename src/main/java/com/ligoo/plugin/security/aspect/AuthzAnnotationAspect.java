package com.ligoo.plugin.security.aspect;

import com.ligoo.framework.annotation.Aspect;
import com.ligoo.framework.annotation.Controller;
import com.ligoo.framework.proxy.AspectProxy;
import com.ligoo.plugin.security.annotation.*;
import com.ligoo.plugin.security.exception.AuthzException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author: Administrator
 * @Date: 2018/12/25 13:44:40
 * @Description: 授权注解切面
 */
@Aspect(Controller.class)
public class AuthzAnnotationAspect extends AspectProxy {

    // 定义一个基于授权功能的注解类数组
    private static final Class[] ANNOTATION_CLASS_ARRAY = {
            Authenticated.class,
            User.class,
            Guest.class,
            HasRoles.class,
            HasPermissions.class
    };

    /**
     * description: 授权注解前置处理
     * author: Administrator
     * date: 2018/12/25 15:34
     *
     * @param: 
     * @return: 
     */
    @Override
    public void before(Class<?> clazz, Method method, Object[] params) throws Throwable {
        // 从目标类与目标方法中获取注解
        Annotation annotation = getAnnotation(clazz, method);
        if(annotation != null){
            Class<?> annotationType = annotation.annotationType();
            if(annotationType.equals(Authenticated.class)){
                handleAuthenticated();
            }else if(annotationType.equals(User.class)){
                handleUser();
            }else if (annotationType.equals(Guest.class)){
                handleGuest();
            }else if (annotationType.equals(HasRoles.class)){
                handleHasRoles((HasRoles) annotation);
            }else if (annotationType.equals(HasPermissions.class)){
                handleHasPermissions((HasPermissions) annotation);
            }
        }
    }

    /**
     * description: 获取指定注解
     * author: Administrator
     * date: 2018/12/25 15:33
     *
     * @param: 
     * @return: 
     */
    @SuppressWarnings("unchecked")
    private Annotation getAnnotation(Class<?> clazz, Method method){
        for (Class<? extends Annotation> annotationClass: ANNOTATION_CLASS_ARRAY){
            if (method.isAnnotationPresent(annotationClass)){
                return method.getAnnotation(annotationClass);
            }
            if (clazz.isAnnotationPresent(annotationClass)){
                return clazz.getAnnotation(annotationClass);
            }
        }
        return null;
    }

    /**
     * description: 处理用户是否登录
     * author: Administrator
     * date: 2018/12/25 15:32
     *
     * @param:
     * @return:
     */
    private void handleUser() throws AuthzException {
        Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection principals = currentUser.getPrincipals();
        if(principals == null || principals.isEmpty()){
            throw new AuthzException("当前用户尚未登录");
        }
    }

    /**
     * description: 处理用户是否为游客
     * author: Administrator
     * date: 2018/12/25 15:57
     *
     * @param:
     * @return:
     */
    private void handleGuest() throws AuthzException {
        Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection principals = currentUser.getPrincipals();
        if(principals != null && !principals.isEmpty()){
            throw new AuthzException("当前用户不是游客");
        }
    }

    /**
     * description: 处理用户是否已认证
     * author: Administrator
     * date: 2018/12/25 15:57
     *
     * @param:
     * @return:
     */
    private void handleAuthenticated() throws AuthzException {
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()){
            throw new AuthzException("当前用户尚未认证");
        }
    }

    /**
     * description: 处理用户是否有某种角色
     * author: Administrator
     * date: 2018/12/25 15:57
     *
     * @param:
     * @return:
     */
    private void handleHasRoles(HasRoles hasRoles) throws AuthzException {
        String roleName = hasRoles.value();
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.hasRole(roleName)){
            throw new AuthzException("当前用户没有指定角色, 角色名:" + roleName);
        }
    }

    /**
     * description: 处理用户是否有权限
     * author: Administrator
     * date: 2018/12/25 15:57
     *
     * @param:
     * @return:
     */
    private void handleHasPermissions(HasPermissions hasPermissions) throws AuthzException {
        String permissionName = hasPermissions.value();
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isPermitted(permissionName)){
            throw new AuthzException("当前用户没有指定权限, 权限名:" + permissionName);
        }
    }
}
