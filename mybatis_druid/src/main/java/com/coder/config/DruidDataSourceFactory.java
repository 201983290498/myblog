package com.coder.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author coder
 * @Date 2021/11/24 21:44
 * @Description
 */
@Data
public class DruidDataSourceFactory implements DataSourceFactory {

    private Properties properties;

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public DataSource getDataSource() {
        DruidDataSource dds = new DruidDataSource();
        dds.setUrl(this.properties.getProperty("url"));
        dds.setUsername(this.properties.getProperty("username"));
        dds.setPassword(this.properties.getProperty("password"));
        try{
            dds.init();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dds;
    }
}
