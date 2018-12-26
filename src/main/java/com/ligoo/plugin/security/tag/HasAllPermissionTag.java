package com.ligoo.plugin.security.tag;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

/**
 * @Author: Administrator
 * @Date: 2018/12/25 16:39:56
 * @Description:
 */
public class HasAllPermissionTag extends PermissionTag {
    private static final String PERMISSION_NAMES_DELIMITER = ",";

    @Override
    protected boolean showTagBody(String permissionNames) {
        boolean hasAllPermission = false;
        Subject subject = SecurityUtils.getSubject();
        if (subject != null){
            if(subject.isPermittedAll(permissionNames.split(PERMISSION_NAMES_DELIMITER)))
                hasAllPermission = true;
        }
        return hasAllPermission;
    }
}
