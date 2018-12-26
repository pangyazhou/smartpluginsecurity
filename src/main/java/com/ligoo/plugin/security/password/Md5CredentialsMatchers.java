package com.ligoo.plugin.security.password;

import com.ligoo.framework.util.CodecUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * @Author: Administrator
 * @Date: 2018/12/24 16:06:26
 * @Description: MD5密码匹配器
 */
public class Md5CredentialsMatchers implements CredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        // 从表单提交的密码,明文
        String submitted = String.valueOf(((UsernamePasswordToken)token).getPassword());
        // 从数据库获取的密码, 已通过MD5加密
        String encrypted = String.valueOf(info.getCredentials());
        return CodecUtil.md5(submitted).equals(encrypted);
    }
}
