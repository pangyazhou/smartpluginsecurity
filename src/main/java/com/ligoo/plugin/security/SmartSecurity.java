package com.ligoo.plugin.security;

import java.util.Set;

/**
 * @Author: Administrator
 * @Date: 2018/12/24 11:43:05
 * @Description: 安全控制接口
 */
public interface SmartSecurity {
    /**
     * description: 根据用户名获取密码
     * author: Administrator
     * date: 2018/12/24 11:46
     *
     * @param: username 用户名
     * @return: 密码
     */
    String getPassword(String username);
    
    /**
     * description: 根据用户名获取角色
     * author: Administrator
     * date: 2018/12/24 11:46
     *
     * @param: username 用户名
     * @return: 角色名集合
     */
    Set<String> getRoleNameSet(String username);
    
    /**
     * description: 根据角色获取权限
     * author: Administrator
     * date: 2018/12/24 11:46
     *
     * @param: roleName 角色名
     * @return: 权限名集合
     */
    Set<String> getPerssionNameSet(String roleName);
}
