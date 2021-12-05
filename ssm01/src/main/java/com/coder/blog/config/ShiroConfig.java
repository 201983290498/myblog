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
 * The type Shiro config.
 *
 * @Author coder
 * @Date 2021 /11/26 1:29
 * @Description
 */
@Configuration
public class ShiroConfig {

  /**
   * 读入配置文件
   *
   * @return shiro props
   */
  @Bean
    @Scope("singleton")
    public ShiroProps shiroProps(){
        return new ShiroProps();
    }

  /**
   * 注入加密器
   *
   * @param shiroProps the shiro props
   * @return hashed credentials matcher
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
   *
   * @return memory constrained cache manager
   */
  @Bean
    @Scope("singleton")
    public MemoryConstrainedCacheManager cacheManager(){
        return  new MemoryConstrainedCacheManager();
    }


  /**
   * 注入我们的Realm
   *
   * @param userDao            the user dao
   * @param cacheManager       the cache manager
   * @param credentialsMatcher the credentials matcher
   * @param shiroProps         the shiro props
   * @return my realm
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

  /**
   * Ini realm ini realm.
   *
   * @return the ini realm
   */
  @Bean
    public IniRealm iniRealm(){
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        return iniRealm;
    }

  /**
   * Security manager security manager.
   *
   * @param myRealm      the my realm
   * @param iniRealm     the ini realm
   * @param cacheManager the cache manager
   * @return the security manager
   */
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

  /**
   * Shiro filter shiro filter factory bean.
   *
   * @param securityManager the security manager
   * @return the shiro filter factory bean
   */
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
        filterChainDefinitionMap.put("/account/**","authc");
        factoryBean.setUnauthorizedUrl("/error/error.jsp");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        factoryBean.setLoginUrl("/login");
        return factoryBean;
    }


//配置shiro的注解开启

  /**
   * 必须配置lifecycBeanPostProcessor：管理shiro的常见的对象
   * 保证实现了Shiro内部的lifecycle函数的bean执行
   *
   * @return lifecycle bean post processor
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
