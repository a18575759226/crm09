package com._520it.crm.util;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Role;
import org.apache.shiro.SecurityUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author
 * @date 2017/7/17
 */
public class UserUtils {
    private UserUtils(){}
    public static Employee getCurrentUser(){
        Employee currentUser = (Employee) SecurityUtils.getSubject().getPrincipal();
        return currentUser;
    }

    public static Set<String> getUserAllRoles(){
        Set<String> rolesSet = new HashSet<>();
        Employee currentUser = getCurrentUser();
        List<Role> roles = currentUser.getRoles();
        for (Role role : roles) {
            rolesSet.add(role.getSn());
        }
        return rolesSet;
    }
}
