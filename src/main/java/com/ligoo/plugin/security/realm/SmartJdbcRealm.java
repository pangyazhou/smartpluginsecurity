package com.ligoo.plugin.security.realm;

import com.ligoo.framework.helper.DataSourceHelper;
import com.ligoo.plugin.security.SecurityConfig;
import com.ligoo.plugin.security.password.Md5CredentialsMatchers;
import org.apache.shiro.realm.jdbc.JdbcRealm;

/**
 * @Author: Administrator
 * @Date: 2018/12/24 16:00:00
 * @Description: 基于Smart的jdbc Realm
 */
public class SmartJdbcRealm extends JdbcRealm {

    public SmartJdbcRealm() {
        super.setDataSource(DataSourceHelper.getDataSource());
        super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());
        super.setUserRolesQuery(SecurityConfig.getJdbcRolesQuery());
        super.setPermissionsQuery(SecurityConfig.getJdbcPermissionsQuery());
        super.setPermissionsLookupEnabled(true);
        super.setCredentialsMatcher(new Md5CredentialsMatchers());
    }
}
