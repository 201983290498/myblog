package com.coder.blog.config;

import com.coder.blog.Utils.Queue.MessageQueUntils;
import com.coder.blog.props.EmailProps;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Author coder
 * @Date 2021/12/2 10:46
 * @Description
 */
@Configuration
public class SpringContextConfig {

    @Bean
    @Scope("singleton")
    public EmailProps emailProps(){return new EmailProps();}

    @Bean
    @Scope("singleton")
    public MessageQueUntils messageQueUntils(@NotNull EmailProps emailProps){
        return new MessageQueUntils(emailProps.getSendEmail(),emailProps.getEmailPwd(), emailProps.getTimeout());
    }
}
