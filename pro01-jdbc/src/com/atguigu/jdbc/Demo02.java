package com.atguigu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 1. 解决乱码问题与ssl警告问题
 * 2. jdbc添加一条记录
 */
public class Demo02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2.通过驱动管理器获取连接对象
        // 添加useSSL=false表示不使用ssl连接
        // 添加useUnicode=true&characterEncoding=utf-8设置字符编码避免中文乱码
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8","root","123456");
        //3.编写SQL语句，?表示占位符
        //id , fname , price , fcount , remark
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        //4.创建预处理命令对象
        PreparedStatement psmt = conn.prepareStatement(sql);
        //5.填充参数
        psmt.setString(1,"榴莲");
        psmt.setInt(2,15);
        psmt.setInt(3,100);
        psmt.setString(4,"榴莲是一种神奇的水果");
        //6.执行更新(增删改），返回影响行数
        int count = psmt.executeUpdate();
        System.out.println(count > 0 ? "添加成功！" : "添加失败！");
        //7.释放资源（关闭连接 , 先关闭psmt，后关闭conn）
        psmt.close();
        conn.close();
    }

}
