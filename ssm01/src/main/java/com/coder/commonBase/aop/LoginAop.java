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
 * 登入的注册的时候实现对密码的加密和授权登入
 *
 * @Author coder
 * @Date 2021 /12/2 20:40
 * @Description
 */
@Aspect
@Component
@Data
public class LoginAop {



    private final ShiroProps shiroProps;

  /**
   * Instantiates a new Login aop.
   *
   * @param shiroProps the shiro props
   */
  public LoginAop(ShiroProps shiroProps) {
        this.shiroProps = shiroProps;
    }

  /**
   * 定义登入切入点
   */
  @Pointcut("execution(public * com.coder.commonBase.controller.AccountController.login(..))")
    public void loginPoint(){
    }

  /**
   * 定义注册切入点
   */
@Pointcut("execution(public * com.coder.commonBase.controller.AccountController.register(..))")
    public void registerPoint(){
    }

  /**
   * 登入程序的密码加密和权限认证
   *
   * @param joinPoint 登入程序
   * @return object
   * @throws Throwable the throwable
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
        if(flag){
            return proceed;
        }else{
            return "error";
        }
    }


  /**
   * 注册时候实现密码加密
   *
   * @param joinPoint the join point
   * @return the object
   * @throws Throwable the throwable
   */
    @Order(1)
    @Around("registerPoint()")
    public Object Register(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取到被切入点的所有参数
        Object[] args = joinPoint.getArgs();
        // 获取到第一个参数，注册的用户对象
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
