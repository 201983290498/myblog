package com.coder.commonBase.config;

import com.coder.commonBase.Utils.Queue.MessageQueUntils;
import com.coder.commonBase.props.EmailProps;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * The type Spring context config.
 *
 * @Author coder
 * @Date 2021 /12/2 10:46
 * @Description
 */
@Configuration
public class SpringContextConfig {

    /**
     * Email props email props.
     *
     * @return the email props
     */
    @Bean
    @Scope("singleton")
    public EmailProps emailProps(){return new EmailProps();}

    /**
     * 注入邮箱
     *
     * @param emailProps the email props
     * @return the message que untils
     */
    @Bean
    @Scope("singleton")
    public MessageQueUntils messageQueUntils(@NotNull EmailProps emailProps){
        return new MessageQueUntils(emailProps.getSendEmail(),emailProps.getEmailPwd(), emailProps.getTimeout());
    }
}
