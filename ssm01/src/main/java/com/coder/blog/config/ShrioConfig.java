package com.coder.blog.config;

import com.coder.blog.dao.UserDao;
import com.coder.blog.props.ShiroProps;
import com.coder.blog.realms.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

/**
 * @Author coder
 * @Date 2021/11/26 1:29
 * @Description
 */
@Configuration
public class ShrioConfig {

    /**
     * 读入配置文件
     * @return
     */
    @Bean
    @Scope("singleton")
    public ShiroProps shiroProps(){
        return new ShiroProps();
    }

  /**
     * 注入加密器
     * @param shiroProps
     * @return
     */
    @Bean
    @Scope("singleton")
    public HashedCredentialsMatcher credentialsMatcher(ShiroProps shiroProps){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(shiroProps.getAlgorithm());
        /* 设置加密次数 */
        credentialsMatcher.setHashIterations(shiroProps.getHashIterations());
        return credentialsMatcher;
    }

    /**
     * 注入缓存器
     * @return
     */
    @Bean
    @Scope("singleton")
    public MemoryConstrainedCacheManager cacheManager(){
        return  new MemoryConstrainedCacheManager();
    }


    /**
     * 注入我们的Realm
     * @param userDao
     * @param cacheManager
     * @param credentialsMatcher
     * @param shiroProps
     * @return
     */
    @Bean
    public MyRealm myRealm(UserDao userDao,MemoryConstrainedCacheManager cacheManager,HashedCredentialsMatcher credentialsMatcher,ShiroProps shiroProps){
        MyRealm myRealm = new MyRealm();
        myRealm.setUserDao(userDao);
        myRealm.setCacheManager(cacheManager);
        myRealm.setSalt(shiroProps.getSalt());
        myRealm.setCredentialsMatcher(credentialsMatcher);
        return myRealm;
    }

    @Bean
    public IniRealm iniRealm(){
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        return iniRealm;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(MyRealm myRealm, IniRealm iniRealm, MemoryConstrainedCacheManager cacheManager){
      DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
      Vector<Realm> vector = new Vector<>();
      vector.add(myRealm);
      vector.add(iniRealm);
      securityManager.setRealms(vector);
      securityManager.setCacheManager(cacheManager);
      return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/images/**","anon");
        filterChainDefinitionMap.put("/users/**","anon");
        filterChainDefinitionMap.put("/blog/**","anon");
        filterChainDefinitionMap.put("/","anon");
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/admin/**","authc");
        factoryBean.setUnauthorizedUrl("/error/error.jsp");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        factoryBean.setLoginUrl("/login");
        return factoryBean;
    }


//配置shiro的注解开启
    /**
     * 必须配置lifecycBeanPostProcessor：管理shiro的常见的对象
     * 保证实现了Shiro内部的lifecycle函数的bean执行
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return  new LifecycleBeanPostProcessor();
    }

    @Bean
    @Scope("singleton")
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    @Scope("singleton")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
