package com.atguigu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo01Batch {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        // 批处理操作一： 如果要执行批处理任务，URL中需要添加一个参数：rewriteBatchedStatements=true
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&CharacterEncoding=utf-8&rewriteBatchedStatements=true", "root", "123456");
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        for (int i = 0; i < 10; i++) {
            pst.setString(1,"榴莲" + i);
            pst.setInt(2, 20);
            pst.setInt(3, 10);
            pst.setString(4, "批处理...");

            // 批处理操作二：psmt.addBatch()
            pst.addBatch();

            if(i%1000==0){  //如果任务较多，可以分批次执行，每次执行完，清空任务队列
                pst.executeBatch();
                pst.clearBatch();
            }
        }

        // 批处理操作三
        // 大于或等于零的数字,表示命令已成功处理，并且是更新计数，给出了数据库中受命令影响的行数执行
        // SUCCESS_NO_INFO ( -2)的值,表示命令为处理成功，但受影响的行数为未知
        int[] count = pst.executeBatch();

        for (int j : count) {
            System.out.println(j); // -2
        }

        pst.executeBatch();
        pst.close();
        conn.close();
    }
}
