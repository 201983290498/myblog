package com.coder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
/**
 * @Author coder
 * @Date 2021/11/25 23:35
 * @Description
 */
public class InitRealm {
    IniRealm iniRealm = new IniRealm("classpath:user.ini");

    @Test
    public void testAuthentication() {
        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);
        //2主题提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);//设置环境
        Subject subject = SecurityUtils.getSubject();//获得主体
        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
        subject.login(token);

        System.out.println("isAuthenticated:" + subject.isAuthenticated());
        //是否具备管理员角色
        subject.checkRole("admin");
        //是否具备用户删除的权限
        subject.checkPermission("user:create");
        //退出
    }
}