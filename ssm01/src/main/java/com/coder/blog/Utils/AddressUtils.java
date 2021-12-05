package com.coder.blog.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * The type Address utils.
 *
 * @Author coder
 * @Date 2021 /11/27 0:02
 * @Description
 */
public class AddressUtils {
    /**
     * 获取IP地址对应的城市
     *
     * @param content        需要获取的字符窜。
     * @param encodingString the encoding string
     * @return addresses
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws JsonProcessingException      the json processing exception
     */
    public  String getAddresses(String content, String encodingString)
            throws UnsupportedEncodingException, JsonProcessingException {
        String urlStr = "http://ip-api.com/json/";
        ObjectMapper mapper = new ObjectMapper();
        String returnStr = this.getResult(urlStr, content, encodingString);
        Map<String,Object> map1 = mapper.readValue(returnStr, Map.class);
        returnStr = map1.get("country").toString() + map1.get("regionName").toString() + map1.get("city").toString();
        if(returnStr.equals(""))
            return  "未知城市";
        else
            return returnStr+" "+map1.get("isp");
    }

    /**
     * 从线上的API获取到相应的ip查询结果
     * @param urlStr
     * @param content
     * @param encoding
     * @return
     */
    private  String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr+"?"+content +"&lang=zh-CN");
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("GET");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return "";
    }
}
