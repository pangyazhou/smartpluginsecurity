package com.ligoo.plugin.security.realm;

import com.ligoo.plugin.security.SecurityConstant;
import com.ligoo.plugin.security.SmartSecurity;
import com.ligoo.plugin.security.password.Md5CredentialsMatchers;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Administrator
 * @Date: 2018/12/24 16:18:21
 * @Description: 基于Smart的自定义Realm
 */
public class SmartCustomRealm extends AuthorizingRealm {
    private SmartSecurity smartSecurity;

    public SmartCustomRealm(SmartSecurity smartSecurity) {
        this.smartSecurity = smartSecurity;
        super.setName(SecurityConstant.REALMS_CUSTOM);
        //super.setCredentialsMatcher(new Md5CredentialsMatchers());
    }

    /**
     * description: 授权
     * author: Administrator
     * date: 2018/12/24 17:04
     *
     * @param: 
     * @return: 
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if(principals == null){
            throw new AuthorizationException("parameter principals is null");
        }
        String username = (String) super.getAvailablePrincipal(principals);
        Set<String> roleNameSet = smartSecurity.getRoleNameSet(username);
        Set<String> permissionNameSet = new HashSet<>();
        if(roleNameSet != null && roleNameSet.size() > 0){
            for (String roleName: roleNameSet){
                Set<String> currentPermissionNameSet = smartSecurity.getPerssionNameSet(roleName);
                permissionNameSet.addAll(currentPermissionNameSet);
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleNameSet);
        authorizationInfo.setStringPermissions(permissionNameSet);
        return authorizationInfo;
    }

    /**
     * description: 认证
     * author: Administrator
     * date: 2018/12/24 17:05
     *
     * @param: 
     * @return: 
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if(token == null){
            throw new AuthenticationException("parameter token is null");
        }
        String username = ((UsernamePasswordToken) token).getUsername();
        String password = smartSecurity.getPassword(username);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
        authenticationInfo.setPrincipals(new SimplePrincipalCollection(username, super.getName()));
        authenticationInfo.setCredentials(password);
        return authenticationInfo;
    }
}
