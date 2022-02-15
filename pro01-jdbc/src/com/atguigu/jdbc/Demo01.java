package com.atguigu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 目标：和数据库建立连接
 *
 * JDBC 是java程序和数据库之间进行通信的规范（接口）
 * 各大数据库厂商根据jdbc接口完成实现类。
 */
public class Demo01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1.加载mysql驱动
        // Class.forName：作用是返回一个类，jvm会查找并加载指定的类，会执行该类的静态代码块
        // Driver类会在静态代码块中加载驱动 "DriverManager.registerDriver(new Driver());"
        // org.gjt.mm.mysql是com.mysql.jdbc.Driver的一个子类，二者都能使用
        Class.forName("com.mysql.jdbc.Driver");

        // 2.通过驱动管理器获取连接对象，需要url(统一资源定位符)、用户名和密码作为参数
        String url = "jdbc:mysql://localhost:3306/fruitdb";
        String user = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, user, password);

        // soutv : Prints a value to System.out
        System.out.println("connection = " + connection);

        // 运行过程中会有关于SSL的警告：
        // Establishing SSL connection without server's identity verification is not recommended.
        // 解决方法见Demo02
    }

}
