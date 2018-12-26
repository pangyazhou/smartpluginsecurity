package com.ligoo.plugin.security.tag;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.RoleTag;

import java.util.Arrays;

/**
 * @Author: Administrator
 * @Date: 2018/12/25 13:32:41
 * @Description:
 */
public class HasAllRolesTag extends RoleTag {
    private static final String ROLE_NAME_DELIMITER = ",";

    @Override
    protected boolean showTagBody(String roleName) {
        boolean hasAllRole = false;
        Subject subject = getSubject();
        if(subject != null){
            if(subject.hasAllRoles(Arrays.asList(roleName.split(ROLE_NAME_DELIMITER)))){
                hasAllRole = true;
            }
        }
        return hasAllRole;
    }
}
