package com.coder.blog.props;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author coder
 * @Date 2021/12/2 11:28
 * @Description
 */
@Data
public class EmailProps {

    @Value("${email.sendEmail}")
    private String sendEmail;

    @Value("${email.emailPwd}")
    private String emailPwd;

    @Value("${email.timeout}")
    private Long timeout;
}
