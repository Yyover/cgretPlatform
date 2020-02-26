package com.javaee6.cgret.controller;

import com.javaee6.cgret.model.Client;
import com.javaee6.cgret.service.ILoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 **/

public class customReaml extends AuthorizingRealm {

    /**
     * 认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String userName = (String) principalCollection.getPrimaryPrincipal();

        // 从数据库中读取用户数据
        Set<String> roles = getRoleByUserName(userName);
        Set<String> permissions = getPermissionsByUserName(userName);
        SimpleAuthorizationInfo SimpleAuthorizationInfo = new SimpleAuthorizationInfo();
        SimpleAuthorizationInfo.setStringPermissions(permissions);
        SimpleAuthorizationInfo.setRoles(roles);
        return SimpleAuthorizationInfo;
    }

    /**
     * 授权
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 从主题传过来的认证信息中获得用户名
        String userName = (String) authenticationToken.getPrincipal();

        // 通过name到数据库中获得凭证
        String password = getPassWordByUserName(userName);

        if(password == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, password, "customReaml");
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userName));
        return authenticationInfo;
    }

    @Resource
    private ILoginService loginService;

    private String getPassWordByUserName(String userName){
        Client client = loginService.getUserByUserName(userName);
        if(client != null){
            return client.getPassword();
        }else {
            return null;
        }
    }

    private Set<String> getRoleByUserName(String userName){
        String role = loginService.queryRoleByUserName(userName);
        Set<String> sets = new HashSet<>();
        sets.add(role);

        return sets;
    }

    private Set<String> getPermissionsByUserName(String userName){
        Set<String> sets = new HashSet<>();
        sets.add("user:delete");
        sets.add("user:add");
        return sets;
    }
}
