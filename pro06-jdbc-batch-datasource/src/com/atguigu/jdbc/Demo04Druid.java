package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

//读取外部的配置文件设置连接池
public class Demo04Druid {
    public static void main(String[] args) throws IOException, SQLException {
        Properties properties = new Properties();
        // 配置文件要放在src同级目录下，否则找不回返回null
        InputStream inputStream = Demo04Druid.class.getClassLoader().getResourceAsStream("jdbc.properties");
        properties.load(inputStream);

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(properties.getProperty("jdbc.url"));
        dataSource.setUsername(properties.getProperty("jdbc.username"));
        dataSource.setPassword(properties.getProperty("jdbc.pwd"));
        dataSource.setInitialSize(Integer.parseInt(properties.getProperty("jdbc.initialSize")));
        dataSource.setMaxActive(Integer.parseInt(properties.getProperty("jdbc.maxActive")));
        dataSource.setMaxWait(Integer.parseInt(properties.getProperty("jdbc.maxWait")));

        for (int i = 0; i < 10; i++) {
            System.out.println(dataSource.getConnection());
        }
    }
}
