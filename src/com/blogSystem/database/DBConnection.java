package com.blogSystem.database;

import java.sql.*;
import java.util.Objects;

/**
 * DBConnection class
 *
 * @author apple
 * @date 2019-08-05
 */
public class DBConnection {
    private static final String LOGIN_USER = "root";
    private static final String PASSWORD = "SweetieCan@0830";
    private static final String URL = "jdbc:mysql://localhost:3306/blogSystem?useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone = GMT";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (Objects.isNull(connection)) {
            try {
                var driverClass = Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, LOGIN_USER, PASSWORD);
                System.out.println("链接建立成功，已连接数据库");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("驱动加载失败");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("连接加载失败");
            }
        }
        return connection;
    }
    public static void close() {
        if (Objects.nonNull(connection)) {
            try {
                connection.close();
                System.out.println("链接关闭成功");
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("链接关闭失败");
            }
        }
    }
}
