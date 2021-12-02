package com.coder.blog.intercepter;

import com.coder.blog.annotation.AccessLimit;
import com.coder.blog.Utils.UserIpUtils;
import com.coder.blog.entity.visit.RequestIp;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author coder
 * @Date 2021/11/27 11:27
 * @Description
 */

/**
 * 对标注了访问限制的方法进行拦截，避免过度修改数据库，带来的数据库压力,将数据写入session来限制访问
 * 对特殊方法的限制
 */
public class AccessIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;//视图方法
            // 使用注解
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);//获得视图方法上的注解
            if (accessLimit == null) {
                return true;
            }

            response.setCharacterEncoding("UTF-8");
            // 取用户的真实IP
            String ip = UserIpUtils.getIp(request);
            // 取session中的IP对象
            RequestIp re = (RequestIp) request.getSession().getAttribute(ip);
            // 第一次请求
            if (null == re) {
                // 放入到session中
                RequestIp reIp = new RequestIp();
                reIp.setCreateTime(System.currentTimeMillis());
                reIp.setReCount(1);
                request.getSession().setAttribute(ip, reIp);
            } else {
                Long createTime = re.getCreateTime();
                if (null == createTime) {
                    // 时间请求为空
                    //out.write("<script >layer.msg('请求太快，请稍后再试！', {icon: 2});</script>");
                } else {
                    if (((System.currentTimeMillis() - createTime) / 1000) > accessLimit.seconds()) {
                        //System.out.println("通过请求！"+ ((System.currentTimeMillis() - createTime) / 1000));
                        // 当前时间离上一次请求时间大于3秒，可以直接通过,保存这次的请求
                        RequestIp reIp = new RequestIp();
                        reIp.setCreateTime(System.currentTimeMillis());
                        reIp.setReCount(1);
                        request.getSession().setAttribute(ip, reIp);
                    } else {
                        // 小于3秒，并且3秒之内请求了10次，返回提示
                        if (re.getReCount() > accessLimit.maxCount()) {
                            //out.write("<script>layer.msg('请求太快，请稍后再试！', {icon: 2});</script>");
                            return false;
                        } else {
                            // 小于3秒，但请求数小于10次，给对象添加
                            re.setCreateTime(System.currentTimeMillis());
                            re.setReCount(re.getReCount() + 1);
                            request.getSession().setAttribute(ip, re);
                        }
                    }
                }}
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
