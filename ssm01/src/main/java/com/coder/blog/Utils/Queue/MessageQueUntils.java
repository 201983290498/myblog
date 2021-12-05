package com.coder.blog.Utils.Queue;

import com.coder.blog.entity.ValidationInfo;
import com.coder.blog.exception.MessageException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author  coder
 * @Date 2021/12/1 20:20
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageQueUntils {
    /**
     * 授权邮箱
     */
    private String sendEmail;

    /**
     * 授权码
     */
    private String pwd;

    /**
     * 验证码的有效时间
     */
    private Long timeout;

    private HashMap<String,String> messageMap = new HashMap<>();
    private ArrayDeque<ValidationInfo> validationInfoQueue = new ArrayDeque<>();

    /**
     * 生成并发生验证信息，如果生成的验证信息依旧有效则重新发送验证信息
     * @param email
     * @return
     */
    public String generateMsg(String email){
        clearStaleData();
        String msg = getMsg(email);
        sendHtmlMail(email,email,msg);
        return msg;
    }

    public Boolean checkMsg(String email, String message) throws MessageException {
        clearStaleData();
        String msg = messageMap.get(email);
        if(message!=null&&message.equals(msg)){
            return true;
        }else{
            throw new MessageException("验证码错误");
        }
    }

    private boolean sendHtmlMail(String receiveEmail, String name,String code){
        try{
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
//            设置邮箱主机，如果是qq邮箱就是smtp.qq.com，网易smtp.163.com
            sender.setHost("smtp.qq.com");
//            设置编码集合
            sender.setDefaultEncoding("utf-8");
            //建立邮箱消息,我们需要以html格式发送邮件
            MimeMessage mailMessage = sender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);

//            设置收件人，寄件人和邮件的主题
            messageHelper.setTo(receiveEmail);
            messageHelper.setFrom(sendEmail);
            messageHelper.setSubject("coder Blog验证系统");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm::ss");

            String str="<!DOCTYPE html><html><head><meta charset='UTF-8'></head><body>"
                    +"<p style='font-size:20px;font-weight:blod;'>尊敬的："+ name +",您好</p>"
                    +"<p style='text-indent:2em;font-size:20px'>欢迎使用coder Blog系统，您本次的验证码是"
                    +"<span style='font-size:30px; font-weight:blod; color:red;'>"+ code +"</span>"
                    +",10分钟之内有效，请尽快使用!</p><p style='text-align:right; padding-right:20px;'>"
                    +"<a href='https:www.coderSimple.com' style='font-size18px;'>coder团队</a></p>"
                    +"<span style='font-size:18px; float:right; margin-right:60px;'>"+ sdf.format(new Date()) +"</span></body></html>";


            messageHelper.setText(str,true);

            //设置发送的账号和密码，状态码
            sender.setUsername(sendEmail);
            sender.setPassword(pwd);

            Properties prop = new Properties();
            /* 让服务器去认证用户名和密码 */
            prop.put("mail.smtp.auth", "true");
            /* 连接超时时间 */
            prop.put("mail.smtp.timeout", "2500");
            sender.setJavaMailProperties(prop);
            sender.send(mailMessage);
            return true;
        } catch (MessagingException e) {
            System.out.println("接收人或者寄件人邮箱错误");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除消息队列中过时的验证信息
     */
    private void clearStaleData(){
        boolean flag=true;
        while(!validationInfoQueue.isEmpty()&&flag){
            ValidationInfo validationInfo = validationInfoQueue.getFirst();
            if(System.currentTimeMillis()-validationInfo.getCreateTime()>=timeout){
                //状态码失效
                messageMap.remove(validationInfo.getEmail());
                validationInfoQueue.removeFirst();
            }else{
                flag=false;
            }
        }
    }

    /**
     * 获取新的验证信息
     * @param email
     * @return
     */
    private String getMsg(String email){
        String msg = messageMap.get(email);
        if(msg==null){
            msg = UUID.randomUUID().toString().substring(0, 6);
        }
        messageMap.put(email, msg);
        validationInfoQueue.addLast(new ValidationInfo(msg,email,System.currentTimeMillis()));
        return msg;
    }


    public MessageQueUntils(String sendEmail, String pwd, Long timeout) {
        this.sendEmail = sendEmail;
        this.pwd = pwd;
        this.timeout = timeout;
    }
}
