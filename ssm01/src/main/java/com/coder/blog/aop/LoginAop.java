package com.coder.blog.aop;

import com.coder.blog.Utils.RespMessageUtils;
import com.coder.blog.entity.User;
import com.coder.blog.props.ShiroProps;
import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

/**
 * 登入的注册的时候实现对密码的加密和授权登入
 * @Author coder
 * @Date 2021/12/2 20:40
 * @Description
 */
@Aspect
@Component
@Data
public class LoginAop {



    private final ShiroProps shiroProps;

    public LoginAop(ShiroProps shiroProps) {
        this.shiroProps = shiroProps;
    }

    /**
     * 登入切入点
     */
    @Pointcut("execution(public * com.coder.blog.controller.AccountController.login(..))")
    public void loginPoint(){
    }

    /**
     * 注册切入点
     */
    @Pointcut("execution(public * com.coder.blog.controller.AccountController.register(..))")
    public void registerPoint(){
    }

    /**
     * 登入程序的密码加密和权限认证
     * @param joinPoint 登入程序
     * @return
     * @throws Throwable
     */
    @Order(1)
    @Around("loginPoint()")
    public Object aroundLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean flag = true;
        Object[] args = joinPoint.getArgs();
        String plainText = (String) args[1];
        plainText = new SimpleHash(shiroProps.getAlgorithm(),plainText,shiroProps.getSalt(),shiroProps.getHashIterations()).toString();
        UsernamePasswordToken token = new UsernamePasswordToken((String)args[0], (String)args[1]);
        args[1] = plainText;
        Subject currentUser = SecurityUtils.getSubject();
        ModelMap map = (ModelMap) args[2];
        try {
            /*授权登入*/
            currentUser.login(token);
        }catch (AuthenticationException e){
            /*添加错误信息*/
            RespMessageUtils.generateErrorInfo(map,new String[]{e.getMessage()+":授权登入失败"});
            flag = false;
        }
        Object proceed = joinPoint.proceed(args);
        /*综合两次的登入结果*/
        if(flag){
            return proceed;
        }else{
            return "error";
        }
    }


    /**
     * 注册时候实现密码加密
     */
    @Order(1)
    @Around("registerPoint()")
    public Object Register(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        User user = (User) args[0];
        String plainText2 = (String) args[2];
        String plainText1 = user.getPassword();
        plainText1 = new SimpleHash(shiroProps.getAlgorithm(),plainText1,shiroProps.getSalt(),shiroProps.getHashIterations()).toString();
        plainText2 = new SimpleHash(shiroProps.getAlgorithm(),plainText2,shiroProps.getSalt(),shiroProps.getHashIterations()).toString();
        user.setPassword(plainText1);
        args[0] = user;
        args[2] = plainText2;
        return joinPoint.proceed(args);
    }

}
