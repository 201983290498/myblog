package com.coder.blog.props;

/**
 * @Author coder
 * @Date 2021/11/27 13:23
 * @Description
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 对用户访问、跟踪、限制的常量
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class VisitProps {

    @Value("${visit.maxCount}")
    private Integer maxCount;

    @Value("${visit.seconds}")
    private Integer seconds;
}
