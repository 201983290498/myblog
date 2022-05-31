package com.coder.commonBase.props;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * The type Druid props.
 * 属性注入类，自动注入配置文件中的属性。
 *
 * @Author coder
 * @Date 2021 /11/25 0:33
 * @Description
 */
@Data
public class DruidProps {

    @Value("${jdbc.driver}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String passwd;

    @Value("${druid.pool.init}")
    private Integer initialSize;

    @Value("${druid.pool.minIdle}")
    private Integer minIdle;

    @Value("${druid.pool.maxActive}")
    private Integer maxActive;

    @Value("${druid.pool.timeout}")
    private Integer timeout;

}
