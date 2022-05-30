package com.coder;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author coder
 * @Date 2021/11/25 23:25
 * @Description
 */
public class AuthenticationTest {
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("Mark","123456","user","admin");
    }

    @Test
    public void testAuthentication(){
        //构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(simpleAccountRealm);

        //设置环境
        SecurityUtils.setSecurityManager(securityManager);
        //获取主题
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456","admin");
        subject.login(token);

        System.out.println("isAuthenticated:" + subject.isAuthenticated());
        subject.checkRole("admin");
        subject.checkRoles("admin","user");
        subject.logout();
        System.out.println("isAuthenticated:" + subject.isAuthenticated());
    }

}
