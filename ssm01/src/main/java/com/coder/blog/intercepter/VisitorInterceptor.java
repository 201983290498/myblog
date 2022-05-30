package com.coder.blog.intercepter;

import com.coder.blog.Utils.UserIpUtils;
import com.coder.blog.entity.User;
import com.coder.blog.entity.visit.VisitRecord;
import com.coder.blog.service.VisitRecordService;
import com.coder.commom.annotation.ResourceAcquisitionRecorder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * The type Visitor interceptor.
 *
 * @Author coder
 * @Date 2021 /12/3 12:34
 * @Description
 */
public class VisitorInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      if(handler instanceof HandlerMethod){
        HandlerMethod hm = (HandlerMethod) handler;

        //获取注解
        ResourceAcquisitionRecorder recorder = hm.getMethodAnnotation(ResourceAcquisitionRecorder.class);
        if(recorder != null){
          VisitRecord record = new VisitRecord();
          HttpSession session = request.getSession();
          record.setSessionId((String) session.getAttribute("sessionId"));
          record.setIp(UserIpUtils.getIp(request));
          record.setTime(new Date());
          record.setUrl(request.getRequestURL().toString());
          //查看是否已经登入
          Object user = session.getAttribute("user");
          if(user != null){
            User account = (User) user;
            record.setUsername(account.getUsername());
          }
          record.setApplicationType(recorder.applicationType().toString());
          record.setResourceType(recorder.resourceType().toString());
          record.setMessage(recorder.name());
          //一般注入是先注入Spring的ioc容器，然后注入SpringMVC的bean, 所以SpringMVC中可以通过WebApplicationContext获取到响应的beans
          WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
          VisitRecordService service = applicationContext.getBean(VisitRecordService.class);
          service.insert(record);
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
