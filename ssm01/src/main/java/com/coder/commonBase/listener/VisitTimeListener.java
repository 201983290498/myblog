package com.coder.commonBase.listener;

import com.coder.commonBase.Utils.AddressUtils;
import com.coder.commonBase.Utils.UserAgentUtils;
import com.coder.commonBase.Utils.UserIpUtils;
import com.coder.commonBase.entity.visit.Visit;
import com.coder.commonBase.service.VisitService;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

/**
 * The type Visit time listener.
 *
 * @Author coder
 * @Date 2021 /11/26 23:06
 * @Description 监听所有访问网络的人 ，但是不监听每次的访问记录
 */
@Data
public class VisitTimeListener implements ServletRequestListener {

    private Log log = LogFactory.getLog(getClass());
    private ApplicationContext applicationContext = null;

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent arg0) {
        HttpServletRequest request = (HttpServletRequest) arg0
                .getServletRequest();
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();
        Visit visit;
        VisitService visitServiceImpl;
        applicationContext = WebApplicationContextUtils
                .getWebApplicationContext(servletContext);
        try {
            visitServiceImpl = (VisitService) applicationContext
                    .getBean("visitServiceImp");
            if (session.isNew()) {
                log.debug("-------applicationContext--------");
                log.debug("begin- " + applicationContext + " -end");
                log.debug("-----begin-----");
                log.debug(applicationContext.getBean("visitServiceImp"));
                //查看sessionIDs是否重复，如果不存在则加上一个，区分两次登入
                Object uuid = session.getAttribute("sessionId");
                if(uuid==null){
                    uuid = UUID.randomUUID().toString();
                    session.setAttribute("sessionId",uuid);
                }
                // 先判断当前ip当天是否已经访问过,如果没有则保存当前访问记录
                visit = new Visit();
                visit.setIp(UserIpUtils.getIp(request));
                visit.setSessionId((String) uuid);
                visit.setTime(new Date());
                String userAgent = request.getHeader("user-agent");
                visit.setUserAgent(userAgent);
                synchronized (this) {
                    if (visitServiceImpl.findVisitTimes(visit) == 0) {
                        visit.setPlatformType(UserAgentUtils.getUserAgent(
                                userAgent).getPlatformType());
                        visit.setBrowserType(UserAgentUtils.getUserAgent(
                                userAgent).getBrowserType());
                        visit.setUrl(request.getRequestURL().toString());
                        visit.setCity(new AddressUtils().getAddresses("ip="
                                + visit.getIp(), "utf-8"));
                        visitServiceImpl.insert(visit);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
