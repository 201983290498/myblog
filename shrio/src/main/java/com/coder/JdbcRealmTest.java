package com.coder;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Author coder
 * @Date 2021/11/25 23:37
 * @Description
 */
public class JdbcRealmTest {
    DruidDataSource dataSource = new DruidDataSource();

    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
    }

    @Test
    public void testAuthentication() {
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        //设置权限开关，默认为false
        jdbcRealm.setPermissionsLookupEnabled(true);

        String sql = "select password from test_user where user_name = ?";
        jdbcRealm.setAuthenticationQuery(sql);


        String rolesql = "select role_name from test_user_role where user_name = ?";
        jdbcRealm.setUserRolesQuery(rolesql);

        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);
        //2主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);//设置环境
        Subject subject = SecurityUtils.getSubject();//获得主体
        UsernamePasswordToken token = new UsernamePasswordToken("xiaoming", "654321");
        subject.login(token);

        System.out.println("isAuthenticated:" + subject.isAuthenticated());
        //是否具备管理员角色
//        subject.checkRole("admin");
//        subject.checkRoles("admin", "user");
//
//        subject.checkPermission("user:select");

        subject.checkRole("user");
    }
}