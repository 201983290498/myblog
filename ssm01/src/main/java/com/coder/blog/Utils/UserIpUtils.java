package com.coder.blog.Utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * The type User ip utils.
 *
 * @Author coder
 * @Date 2021 /11/26 21:29
 * @Description 通过多种方式获取到登入的ip地址
 */
public class UserIpUtils {
    /**
     * Gets ip.
     *
     * @param httpRequest the http request
     * @return the ip
     */
    public static String getIp(HttpServletRequest httpRequest) {
        String ipAddress = httpRequest.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = httpRequest.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = httpRequest.getHeader("WL-Proxy-Client-IP");
        }
        if(null == ipAddress || 0 == ipAddress.length() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = httpRequest.getHeader("X-Real-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = httpRequest.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress)
                    || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        // "***.***.***.***".length()
        if (ipAddress != null && ipAddress.length() > 15) {
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
