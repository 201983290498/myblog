package com.coder.commonBase.props;

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

import java.util.List;

/**
 * 对用户访问、跟踪、限制的常量
 * 使用Component自动注入
 * @author coder
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class VisitProps {

    /**
     * 单位时间内的频率限制
     */
    @Value("${visit.maxCount}")
    private Integer maxCount;

    /**
     * 频率统计的单位时间
     */
    @Value("${visit.seconds}")
    private Integer seconds;

    /**
     * 不进行频率拦截的文件资源类型
     * //TODO 待整理自动注入时#和$的区别
     */
    @Value("#{'${visit.publicResoruces}'.split(',')}")
    private List<String> publicResources;
}
