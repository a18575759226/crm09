package com._520it.crm.realm;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Role;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.service.IPermissionService;
import com._520it.crm.service.IRoleService;
import lombok.Setter;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 2017/7/12
 */
public class UserRealm extends AuthorizingRealm {
    @Setter
    private IEmployeeService employeeService;
    @Setter
    private IRoleService roleService;
    @Setter
    private IPermissionService permissionService;
    @Override
    public String getName() {
        return "UserRealm";
    }
    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username= (String) token.getPrincipal();
        Employee currentUser = employeeService.queryUserByUsername(username);
        if (currentUser == null) {
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(currentUser,currentUser.getPassword(),getName());
        return info;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录的用户信息,返回类型与认证方法中SimpleAuthenticationInfo初始化的类型一致
        Employee currentUser = (Employee) principalCollection.getPrimaryPrincipal();
        List<String> roles = new ArrayList<>();
        List<String> perms = new ArrayList<>();
        //如果用户是超级管理员,则拥有所有用户和权限
        if (currentUser.isAdmin()) {
            List<Role> roleList = roleService.selectAll();
            for (Role role : roleList) {
                roles.add(role.getSn());
            }
            perms.add("*:*");
        }else {
            //根据用户id查询出所有的角色名称
            roles = roleService.queryRoleSnListByEmployeeId(currentUser.getId());
            //根据用户id查询出所有的权限表达式
            perms = permissionService.queryPermissionListByEmployeeId(currentUser.getId());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(perms);
        return info;
    }


}
