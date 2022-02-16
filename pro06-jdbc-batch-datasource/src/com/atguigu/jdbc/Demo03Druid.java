package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.SQLException;

//验证连接池的各项参数：初始化大小、最大激活数量、最大等待时间
public class Demo03Druid {
    public static void main(String[] args) throws SQLException {

        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        // 启动时的初始连接数量
        dataSource.setInitialSize(2);
        // 可以分配的最大连接数量
        dataSource.setMaxActive(5);
        // 收回连接的最长等待时间，超出后会抛出异常
        dataSource.setMaxWait(2000);

        for (int i = 0; i < 10; i++) {
            System.out.println(dataSource.getConnection());
        }

        /*
         * Exception in thread "main" com.alibaba.druid.pool.GetConnectionTimeoutException: wait millis 2013, active 5, maxActive 5, creating 0
         * 	at com.alibaba.druid.pool.DruidDataSource.getConnectionInternal(DruidDataSource.java:1512)
         * 	at com.alibaba.druid.pool.DruidDataSource.getConnectionDirect(DruidDataSource.java:1255)
         * 	at com.alibaba.druid.pool.DruidDataSource.getConnection(DruidDataSource.java:1235)
         * 	at com.alibaba.druid.pool.DruidDataSource.getConnection(DruidDataSource.java:1225)
         * 	at com.atguigu.jdbc.Demo03Druid.main(Demo03Druid.java:23)
         */
    }
}
