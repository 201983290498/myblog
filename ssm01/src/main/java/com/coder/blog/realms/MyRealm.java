package com.coder.blog.realms;

import com.coder.blog.dao.UserDao;
import com.coder.blog.entity.Role;
import com.coder.blog.entity.User;
import lombok.Data;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author coder
 * @Date 2021/11/26 0:38
 * @Description
 */
@Data
public class MyRealm extends AuthorizingRealm {

    private UserDao userDao;

    @Value("${shiro.salt}")
    private String salt;

    {
        super.setName("myRealm");
    }

    /**
     * 获取用户的角色和权限
     * @param principals: 用户
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();
        User user = userDao.selectOne(userName);
        Set<Role> userRoles = user.getRoles();

        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        for(Role each:userRoles){
            roles.add(each.getRole());
            permissions.add(each.getPermission());
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken token1 = (UsernamePasswordToken) token;
        String userName = (String) token1.getPrincipal();

        String password = userDao.selectOne(userName).getPassword();
        String password1 = new String(token1.getPassword());
        if(null==password){
            throw  null;
        }
        String md5Pwd = new SimpleHash("MD5",password1,salt).toHex();
        if(!md5Pwd.equals(password)){
            throw null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName,password,getName());
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(salt));
        return authenticationInfo;
    }
}
