package com.coder.blog.intercepter;

import com.coder.blog.annotation.AccessLimit;
import com.coder.blog.Utils.UserIpUtils;
import com.coder.blog.entity.visit.RequestIp;
import com.coder.blog.props.VisitProps;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Author coder
 * @Date 2021/11/27 13:14
 * @Description
 */
@Data
public class CommonIntercepter implements HandlerInterceptor {

    @Autowired
    private VisitProps visitProps;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){//先判断是不是视图方法
            HandlerMethod hm = (HandlerMethod) handler;
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if(accessLimit==null){
                response.setCharacterEncoding("utf-8");
                String ip = UserIpUtils.getIp(request);
                RequestIp re = (RequestIp) request.getSession().getAttribute(ip);
                if(null==re){
                    re = new RequestIp();
                    re.setCreateTime(System.currentTimeMillis());
                    re.setReCount(1);
                    re.setIp(ip);
                }else{
                    Long createtime = re.getCreateTime();
                    if((System.currentTimeMillis()-createtime)/1000>visitProps.getSeconds()){
                        re = new RequestIp();
                        re.setReCount(1);
                        re.setCreateTime(System.currentTimeMillis());
                        re.setIp(ip);
                    }else{
                        if(re.getReCount()>visitProps.getMaxCount()){
                            response.setCharacterEncoding("utf-8");
                            OutputStream outputStream = response.getOutputStream();
                            outputStream.write("<script>alert('the frequently of visit is too height!')</script>".getBytes(StandardCharsets.UTF_8));
                            outputStream.flush();
                            outputStream.close();
                            return false;
                        }else{
                            re.setCreateTime(System.currentTimeMillis());
                            re.setReCount(re.getReCount()+1);
                            re.setIp(ip);
                        }
                    }
                }
                request.getSession().setAttribute(ip,re);
            }
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
