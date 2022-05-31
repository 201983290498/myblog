package com.coder.commonBase.props;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * The type Email props.
 *
 * @Author coder
 * @Date 2021 /12/2 11:28
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
