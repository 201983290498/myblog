package com.coder.blog.config;


import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The type Spring mvc config.
 *
 * @Author coder
 * @Date 2021 /11/25 20:07
 * @Description
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(value = "com.coder.blog",useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)
})
public class SpringMvcConfig {
    /**
     * Lifecycle bean post processor lifecycle bean post processor.
     *
     * @return the lifecycle bean post processor
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return  new LifecycleBeanPostProcessor();
    }

    /**
     * Default advisor auto proxy creator default advisor auto proxy creator.
     *
     * @return the default advisor auto proxy creator
     */
    @Bean
    @Scope("singleton")
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }

    /**
     * Authorization attribute source advisor authorization attribute source advisor.
     *
     * @param securityManager the security manager
     * @return the authorization attribute source advisor
     */
    @Bean
    @Scope("singleton")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
