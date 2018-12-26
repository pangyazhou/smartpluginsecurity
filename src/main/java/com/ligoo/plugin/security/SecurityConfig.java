package com.ligoo.plugin.security;

import com.ligoo.framework.helper.ConfigHelper;
import com.ligoo.framework.util.ReflectionUtil;

/**
 * @Author: Administrator
 * @Date: 2018/12/24 16:20:50
 * @Description: 从配置文件中获取相关属性
 */
public class SecurityConfig {
    /**
     * description: 获取Realm
     * author: Administrator
     * date: 2018/12/24 16:25
     *
     * @param: 
     * @return: 
     */
    public static String getRealms(){
        return ConfigHelper.getString(SecurityConstant.REALMS);
    }
    
    /**
     * description: 获取安全控制器
     * author: Administrator
     * date: 2018/12/24 16:33
     *
     * @param: 
     * @return: 
     */
    public static SmartSecurity getSmartSecurity(){
        String className = ConfigHelper.getString(SecurityConstant.SMART_SECURITY);
        return (SmartSecurity) ReflectionUtil.newInstance(className);
    }

    public static String getJdbcAuthcQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_AUTHC_QUERY);
    }

    public static String getJdbcRolesQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_ROLES_QUERY);
    }

    public static String getJdbcPermissionsQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_PERMISSIONS_QUERY);
    }

    public static boolean isCache() {
        return ConfigHelper.getBoolean(SecurityConstant.CACHE);
    }
}
