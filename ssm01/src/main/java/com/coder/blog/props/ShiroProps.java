package com.coder.blog.props;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;


/**
 * @Author coder
 * @Date 2021/11/26 16:06
 * @Description
 */
@Data
public class ShiroProps {

    @Value("${shiro.salt}")
    private String salt;

    @Value("${shiro.hashIterations}")
    private Integer hashIterations;

    @Value("${shiro.algorithm}")
    private String algorithm;
}
