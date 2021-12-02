package com.coder.blog.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.coder.blog.props.DruidProps;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @Author coder
 * @Date 2021/11/25 0:25
 * @Description
 */
@Configuration
//开始事务管理
@EnableTransactionManagement
//开始aop动态大力
@EnableAspectJAutoProxy
public class MybatisConfig {

    @Bean
    @Scope("singleton")
    public DruidProps druidProps(){
        return new DruidProps();
    }

/* 配置数据源 */
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

    /* sqlSessionFactory */
    @Bean
    @Scope("singleton")
    public SqlSessionFactory sqlSessionFactory(DruidDataSource druidDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(druidDataSource);
        factoryBean.setTypeAliasesPackage("com.coder.blog.entity");
        try {
            factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        } catch (IOException e) {
            System.out.println("mybatis读取配置文件失败，com.coder.blog.mapper");
            e.printStackTrace();
        }
       // 加载mybatis的配置文件
        factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        return factoryBean.getObject();
    }

    //   配置mapperScannerConfigure
    //    加载所有的dao
    @Bean
    @Scope("singleton")
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.coder.blog.dao");
        return mapperScannerConfigurer;
    }

    @Bean
    @Scope("singleton")
    public PlatformTransactionManager transactionManager(DataSource druidDataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(druidDataSource);
        return dataSourceTransactionManager;
    }
}
