package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

//读取外部的配置文件设置连接池

/**
 * 补充：
 *      class.getClassLoader()默认从此类所在的包下获取资源
 *      class.getClassLoader().getResourceAsStream()默认从classpath根目录下获取
 */
public class Demo05Druid {
    public static void main(String[] args) throws Exception {

        // DruidDataSource dataSource = new DruidDataSource();

        Properties properties = new Properties();
        InputStream inputStream = Demo05Druid.class.getResourceAsStream("jdbc2.properties");
        // InputStream inputStream = Demo05Druid.class.getClassLoader().getResourceAsStream("jdbc2.properties");
        properties.load(inputStream);

        // dataSource.configFromPropety(properties);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        for(int i = 0 ; i<10 ; i++){
            Connection conn1 = dataSource.getConnection();
            System.out.println(i+"-------->"+conn1);

        }

    }
}
