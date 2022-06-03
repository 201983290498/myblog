package com.coder.commonBase.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.coder.commonBase.props.DruidProps;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;


/**
 * 开始事务管理
 * 开始spring的AOP
 *
 * @author coder
 * @Date 2021 /11/25 0:25
 * @Description
 */
@EnableTransactionManagement
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MybatisConfig {

    /**
     * Druid props druid props.
     *
     * @return the druid props
     */
    @Bean
    @Scope("singleton")
    public DruidProps druidProps(){
        return new DruidProps();
    }

    /**
     * 配置数据源  @param druidProps the druid props
     *
     * @return the druid data source
     */
    @Bean
    @Scope("singleton")
    public DruidDataSource dataSource(DruidProps druidProps){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(druidProps.getDriverClassName());
        druidDataSource.setUrl(druidProps.getUrl());
        druidDataSource.setUsername(druidProps.getUsername());
        druidDataSource.setPassword(druidProps.getPasswd());

        druidDataSource.setInitialSize(druidProps.getInitialSize());
        druidDataSource.setMinIdle(druidProps.getMinIdle());
        druidDataSource.setMaxActive(druidProps.getMaxActive());
        druidDataSource.setMaxWait(druidProps.getTimeout());
        return druidDataSource;
    }

    /**
     * 注册分页拦截器
     *
     * @return the page interceptor
     */
    @Bean
    public PageInterceptor pageInterceptor(){
      PageInterceptor pageInterceptor = new PageInterceptor();
      Properties properties = new Properties();
      properties.setProperty("helperDialect","mysql");
      properties.setProperty("reasonable","true");
      properties.setProperty("supportMethodsArguments","true");
      pageInterceptor.setProperties(properties);
      return pageInterceptor;
    }


    /**
     * sqlSessionFactory
     *
     * @param druidDataSource the druid data source
     * @param pageInterceptor the page interceptor
     * @return the sql session factory
     * @throws Exception the exception
     */
    @Bean
    @Scope("singleton")
    public SqlSessionFactory sqlSessionFactory(DruidDataSource druidDataSource,Interceptor pageInterceptor) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(druidDataSource);
        factoryBean.setTypeAliasesPackage("com.coder.commonBase.entity,com.coder.commom.fileSystem.entity");
        try {
            factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        } catch (IOException e) {
            System.out.println("mybatis读取配置文件失败，com.coder.commonBase.mapper");
            e.printStackTrace();
        }
        //设置分页插件
        factoryBean.setPlugins(new Interceptor[]{pageInterceptor});
       // 加载mybatis的配置文件
        factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        return factoryBean.getObject();
    }

    /**
     * 配置mapperScannerConfigure
     * 加载所有的dao
     *
     * @return the mapper scanner configurer
     */
    @Bean
    @Scope("singleton")
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.coder.commonBase.dao,com.coder.commom.fileSystem.dao");
        return mapperScannerConfigurer;
    }

    /**
     * Transaction manager platform transaction manager.
     *
     * @param druidDataSource the druid data source
     * @return the platform transaction manager
     */
    @Bean
    @Scope("singleton")
    public PlatformTransactionManager transactionManager(DataSource druidDataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(druidDataSource);
        return dataSourceTransactionManager;
    }
}
