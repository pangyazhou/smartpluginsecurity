package com.ligoo.plugin.security;

import org.apache.shiro.web.env.EnvironmentLoaderListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * @Author: Administrator
 * @Date: 2018/12/24 15:45:02
 * @Description:
 */
public class SmartSecurityPlugin implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> handleTypes, ServletContext servletContext) throws ServletException {
        // 设置初始化参数
        servletContext.setInitParameter("shiroConfigLocations", "classpath:smart-security.ini");
        // 注册Listener
        servletContext.addListener(EnvironmentLoaderListener.class);
        // 注册Filter
        FilterRegistration.Dynamic smartSecurityFilter = servletContext.addFilter("SmartSecurityFilter", SmartSecurityFilter.class);
        smartSecurityFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}
