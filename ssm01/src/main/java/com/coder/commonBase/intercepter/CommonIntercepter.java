package com.coder.commonBase.intercepter;

import com.coder.commom.annotation.AccessLimit;
import com.coder.commonBase.Utils.UserIpUtils;
import com.coder.commonBase.entity.visit.RequestIp;
import com.coder.commonBase.props.VisitProps;
import com.coder.commom.annotation.Enum.ResourceType;
import com.coder.commom.annotation.ResourceAcquisitionRecorder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * The type Common intercepter.
 * 公共拦截器获取，主要的功能包括了
 *
 * @Author coder
 * @Date 2021 /11/27 13:14
 * @Description
 */
@Data
public class CommonIntercepter implements HandlerInterceptor {

    @Autowired
    private VisitProps visitProps;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        //先判断是不是视图方法
        if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler;
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            ResourceAcquisitionRecorder recorder = hm.getMethodAnnotation(ResourceAcquisitionRecorder.class);
          //获取URI
          String requestURI = request.getRequestURI();
          //静态资源直接放行，包括了visitProps中的publicResource属性
          boolean flag = true;
          for(String publicResource : visitProps.getPublicResources()){
              if (requestURI.matches("(.*)" + publicResource + "$")) {
                  flag = false;
                  break;
              }
          }
          /**
           * 拦截的请求不拦截获取资源记录的请求
           * 在session结构体中写入ip: ResquestIp结构
           */
          if(flag&&accessLimit==null&&recorder!=null&&recorder.resourceType()!= ResourceType.RECORD){
                response.setCharacterEncoding("utf-8");
                String ip = UserIpUtils.getIp(request);
                RequestIp re = (RequestIp) request.getSession().getAttribute(ip);
                if(null==re){
                    re = new RequestIp();
                    re.setCreateTime(System.currentTimeMillis());
                    re.setReCount(1);
                    re.setIp(ip);
                }else{
                    long createTime = re.getCreateTime();
                    if((System.currentTimeMillis()-createTime) / 1000 > visitProps.getSeconds()){
                        re = new RequestIp();
                        re.setReCount(1);
                        re.setCreateTime(System.currentTimeMillis());
                        re.setIp(ip);
                    }else{
                        if(re.getReCount()>visitProps.getMaxCount()){
                            response.setCharacterEncoding("utf-8");
                            //直接给网页写个script脚本执行
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
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
