package com.coder;

/**
 * @Author coder
 * @Date 2021/11/25 23:42
 * @Description
 */
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
    //模拟数据库
    Map<String, String> userMap = new HashMap<String, String>(16);

    {
        userMap.put("Mark", "e10adc3949ba59abbe56e057f20f883e");//原密码为：123456，因为使用了MD5
        //加密
        super.setName("customRealm");
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户
        String userName = (String) principalCollection.getPrimaryPrincipal();
        //从数据库或者缓存中获取角色数据
        Set<String> roles = getRolesByUserName(userName);
        //从数据库或者缓存中获取权限数据
        Set<String> permissions = getPermissionByUserName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //设置权限
        simpleAuthorizationInfo.setStringPermissions(permissions);
        //设置角色
        simpleAuthorizationInfo.setRoles(roles);

        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
        sets.add("user:delete");
        sets.add("user:add");
        return sets;
    }

    private Set<String> getRolesByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1. 从主体传过来的认证信息中，获得用户名
        String userName = (String) authenticationToken.getPrincipal();

        // 2. 通过用户名到数据库获取凭证
        String password = getpasswordByUserName(userName);
        if (null == password) {
            return  null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("Mark", password, "customRealm");
        //设置盐
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("zou"));
        return authenticationInfo;
    }

    /*
     * 模拟数据库查询凭证（获得密码）
     * */
    private String getpasswordByUserName(String userName) {
        return userMap.get(userName);
    }
}
