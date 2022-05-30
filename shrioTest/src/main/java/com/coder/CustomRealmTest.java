package com.coder;

/**
 * @Author coder
 * @Date 2021/11/25 23:47
 * @Description
 */
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class CustomRealmTest {

    @Test
    public void testAuthentication() {
        CustomRealm customRealm = new CustomRealm();
        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        // 2.声明CustomRealm使用了Md5加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //加密算法为MD5
        matcher.setHashAlgorithmName("md5");
        //加密次数为1
        matcher.setHashIterations(1);
        customRealm.setCredentialsMatcher(matcher);

        //2主题提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);//设置环境
        Subject subject = SecurityUtils.getSubject();//获得主体
        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
        subject.login(token);

        System.out.println("isAuthenticated:" + subject.isAuthenticated());
        //是否具备管理员角色
        subject.checkRole("admin");
        //是否具备用户删除的权限
        subject.checkPermissions("user:add", "user:delete");
        //退出
    }
    //用来计算123456加密后的密码为：e10adc3949ba59abbe56e057f20f883e
    public static void main(String[] args) {
        //盐为zou  一般为随机数，这里为了方便
        Md5Hash md5Hash = new Md5Hash("123456", "zou");
        System.out.println(md5Hash);
    }
}


