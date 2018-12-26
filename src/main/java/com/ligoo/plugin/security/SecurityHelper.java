package com.ligoo.plugin.security;

import com.ligoo.plugin.security.exception.AuthcException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author: Administrator
 * @Date: 2018/12/24 14:44:31
 * @Description: Security 助手类
 */
public class SecurityHelper {
    private static final Logger LOGGER  = LoggerFactory.getLogger(SecurityHelper.class);

    /**
     * description: 登录
     * author: Administrator
     * date: 2018/12/24 14:45
     *
     * @param:
     * @return:
     */
    public static void login(String username, String password) throws AuthcException {
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser != null){
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                currentUser.login(token);
            }catch (AuthenticationException e){
                LOGGER.error("login failure", e);
                throw new AuthcException(e);
            }
        }

    }

    /**
     * description: 注销
     * author: Administrator
     * date: 2018/12/24 14:45
     *
     * @param:
     * @return:
     */
    public static void logout(){
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser != null){
            currentUser.logout();
        }
    }
}
