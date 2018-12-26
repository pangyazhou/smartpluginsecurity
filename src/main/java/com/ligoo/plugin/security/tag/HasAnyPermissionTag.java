package com.ligoo.plugin.security.tag;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;


/**
 * @Author: Administrator
 * @Date: 2018/12/25 16:10:28
 * @Description:
 */
public class HasAnyPermissionTag extends PermissionTag {
    private static final String PERMISSION_NAMES_DELIMITER = ",";

    @Override
    protected boolean showTagBody(String permissionNames) {
        boolean hasAnyPermission = false;
        Subject subject = SecurityUtils.getSubject();
        if (subject != null){
            for (String permissionName: permissionNames.split(PERMISSION_NAMES_DELIMITER)){
                if (subject.isPermitted(permissionName.trim())){
                    hasAnyPermission = true;
                    break;
                }
            }
        }
        return hasAnyPermission;
    }
}
