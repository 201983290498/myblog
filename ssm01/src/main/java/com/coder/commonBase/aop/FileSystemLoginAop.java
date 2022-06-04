package com.coder.commonBase.aop;

import com.coder.commonBase.Utils.RespMessageUtils;
import com.coder.commonBase.entity.User;
import com.coder.commonBase.props.ShiroProps;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author coder
 * @Date 2022/6/4 13:45
 * @Description
 */
@Aspect
@Component
@Data
public class FileSystemLoginAop {

    private final ShiroProps shiroProps;

    public FileSystemLoginAop(ShiroProps shiroProps) {
        this.shiroProps = shiroProps;
    }


    /**
     * 登入切入点
     */
    @Pointcut("execution(public * com.coder.commom.fileSystem.controller.FileSystemAccountController.login(..))")
    public void fileSystemLoginPoint(){}

    /**
     * 定义文件系统的切入点
     */
    @Pointcut("execution(public * com.coder.commom.fileSystem.controller.FileSystemAccountController.register(..))")
    public void fileSystemRegisterPoint(){}

    /**
     * 文件系统的登入程序的密码加密和权限认证
     *
     * @param joinPoint 登入程序
     * @return object
     * @throws Throwable the throwable
     */
    @Order(1)
    @Around("fileSystemLoginPoint()")
    public Object aroundFileSystemLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean flag = true;
        Object[] args = joinPoint.getArgs();
        User user = (User) args[0];
        String plainText = user.getPassword();
        plainText = new SimpleHash(shiroProps.getAlgorithm(),plainText,shiroProps.getSalt(),shiroProps.getHashIterations()).toString();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        user.setPassword(plainText);
        args[0] = user;
        Subject currentUser = SecurityUtils.getSubject();
        ModelMap map = (ModelMap) args[1];
        try {
            /*授权登入*/
            currentUser.login(token);
            HttpServletRequest request = (HttpServletRequest) args[args.length-1];
            HttpSession session = request.getSession();
            session.setAttribute("myToken",currentUser);
        }catch (AuthenticationException e){
            /*添加错误信息*/
            RespMessageUtils.generateErrorInfo(map,new String[]{e.getMessage()+":授权登入失败"});
            flag = false;
        }
        Object proceed = joinPoint.proceed(args);
        /*综合两次的登入结果*/
        return proceed;
    }

    /**
     * 文件系统的注册系统
     * @param joinPoint 切入点
     * @return
     * @throws Throwable
     */
    @Order(1)
    @Around("fileSystemRegisterPoint()")
    public Object fileSystemRegister(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取到被切入点的所有参数
        Object[] args = joinPoint.getArgs();
        // 获取到第一个参数，注册的用户对象
        User user = (User) args[0];
        if(user.getUsername().length()<6||user.getUsername().length()>12){
            return new RespMessageUtils(false, "用户名长度过长(短), 请限制在6~12个字符");
        }
        if(user.getPassword().length()<6||user.getPassword().length()>20){
            return new RespMessageUtils(false, "密码长度过长(短), 请限制在6~12个字符");
        }
        String plainText1 = user.getPassword();
        plainText1 = new SimpleHash(shiroProps.getAlgorithm(),plainText1,shiroProps.getSalt(),shiroProps.getHashIterations()).toString();
        user.setPassword(plainText1);
        args[0] = user;
        return joinPoint.proceed(args);
    }
}
