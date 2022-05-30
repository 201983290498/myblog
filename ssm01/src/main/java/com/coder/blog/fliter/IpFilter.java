package com.coder.blog.fliter;

import com.coder.blog.Utils.UserIpUtils;
import com.coder.blog.entity.visit.BlackIp;
import com.coder.blog.service.BlackIpService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 这个类用来过滤一些不可访问的IP地址
 *
 * @Author coder
 * @Date 2021 /11/26 21:26
 * @Description
 */
public class IpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //过滤器先获取到请求体和响应体，然后从请求体中获取IP地址
        String ip= UserIpUtils.getIp(request);
        HttpSession session = request.getSession();// 获取到session
        ServletContext servletContext = session.getServletContext();
        BlackIp blackIp = null;
        BlackIpService blackIpServiceImpl = null;
        WebApplicationContext applicationContext = WebApplicationContextUtils
                .getWebApplicationContext(servletContext);
        blackIpServiceImpl = (BlackIpService) applicationContext
                .getBean("blackIpServiceImpl");
        //插卡IP地址是否被禁止
        blackIp=blackIpServiceImpl.selectBlackIpByIp(ip);
        if(blackIp==null){
            filterChain.doFilter(request, response);
            return;
        }else{
            //IP地址被禁止
            request.getRequestDispatcher("/error/blackIp.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
