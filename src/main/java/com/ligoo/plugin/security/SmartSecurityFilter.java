package com.ligoo.plugin.security;

import com.ligoo.plugin.security.realm.SmartCustomRealm;
import com.ligoo.plugin.security.realm.SmartJdbcRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.CachingSecurityManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Author: Administrator
 * @Date: 2018/12/24 15:51:29
 * @Description:
 */
public class SmartSecurityFilter extends ShiroFilter {

    @Override
    public void init() throws Exception {
        super.init();
        WebSecurityManager webSecurityManager = super.getSecurityManager();
        setRealms(webSecurityManager);
        setCache(webSecurityManager);
    }

    /**
     * description: 添加自己实现的基于jdbc的realm
     * author: Administrator
     * date: 2018/12/24 17:09
     *
     * @param:
     * @return:
     */
    private void addJdbcRealm(Set<Realm> realms){
        SmartJdbcRealm jdbcRealm = new SmartJdbcRealm();
        realms.add(jdbcRealm);
    }

    /**
     * description: 添加自定义的realm
     * author: Administrator
     * date: 2018/12/24 17:09
     *
     * @param:
     * @return:
     */
    private void addCustomerRealm(Set<Realm> realms){
        SmartSecurity smartSecurity = SecurityConfig.getSmartSecurity();
        SmartCustomRealm customRealm = new SmartCustomRealm(smartSecurity);
        realms.add(customRealm);
    }

    /**
     * description: 设置Realm
     * author: Administrator
     * date: 2018/12/24 17:14
     *
     * @param:
     * @return:
     */
    private void setRealms(WebSecurityManager webSecurityManager){
        String securityRealms = SecurityConfig.getRealms();
        if(securityRealms != null){
            String[] realmArray = securityRealms.split(",");
            if(realmArray.length > 0){
                Set<Realm> realms = new LinkedHashSet<>();
                for(String realm: realmArray){
                    if(realm.equalsIgnoreCase(SecurityConstant.REALMS_JDBC)){
                        addJdbcRealm(realms);
                    }else if(realm.equalsIgnoreCase(SecurityConstant.REALMS_CUSTOM)){
                        addCustomerRealm(realms);
                    }
                }
                RealmSecurityManager realmSecurityManager = (RealmSecurityManager) webSecurityManager;
                realmSecurityManager.setRealms(realms);
            }
        }

    }

    /**
     * description: 设置缓存
     * author: Administrator
     * date: 2018/12/24 17:12
     *
     * @param: 
     * @return: 
     */
    private void setCache(WebSecurityManager webSecurityManager){
        if(SecurityConfig.isCache()){
            CachingSecurityManager cachingSecurityManager = (CachingSecurityManager) webSecurityManager;
            // 使用基于内存的cache
            CacheManager cacheManager = new MemoryConstrainedCacheManager();
            cachingSecurityManager.setCacheManager(cacheManager);
        }
    }
}
