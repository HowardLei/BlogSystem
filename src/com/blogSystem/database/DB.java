package com.blogSystem.database;

import com.blogSystem.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * DB class
 *
 * @author apple
 * @date 2019-08-02
 */
public class DB {
    public static void main(String[] args) {
        var user = new User("admin", "123456", "hhh");
        var attrs = new String[] {"userName"};
        var list = select("select * from users;", attrs);
        var attrsMap = new HashMap<String, String>();
        attrsMap.put("userName", user.getUserName(true));
        attrsMap.put("password", user.getPassword(true));
        System.out.println(select("users", attrsMap));
    }
    /**
     * 查找数据库当中的元素
     * @param sql 查找的 SQL 语句
     * @param attrs 想要获得的属性名
     * @return 属性列表
     */
    public static List<Map<String, Object>> select(String sql, String[] attrs) {
        var list = new ArrayList<Map<String, Object>>(10);
        // 加载数据库驱动
        try {
            // 建立数据库链接
            var connection = DBConnection.getConnection();
            // 创建一个“小手”，操作数据库
            var statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                var result = new HashMap<String, Object>(5);
                for (var attr: attrs) {
                    result.put(attr, resultSet.getObject(attr));
                }
                list.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close();
        }
        return list;
    }

    /***
     * 查找数据库当中是否有该用户
     * @param tableName 需要查找的表名
     * @param attrMap 属性字典 key: 表当中的列名，value: 其中的属性值
     * @return 属性与属性值是否完全一一对应
     */
    public static boolean select(String tableName, Map<String, String> attrMap) {
        var connection = DBConnection.getConnection();
        var stringBuilder = new StringBuilder("select * from ").append(tableName);
        if (Objects.nonNull(attrMap)) {
            stringBuilder.append(" where ");
            var res = attrMap.toString().substring(1, attrMap.toString().length() - 1).replace(",", " and");
            stringBuilder.append(res).append(';');
            try {
                Statement statement = connection.createStatement();
                var resultSet = statement.executeQuery(stringBuilder.toString());
                // FIXME: 当取到结果的时候，不知道如何判断正确与否了。
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean insert(String sql, Object[] objects) throws SQLException {
        var connection = DBConnection.getConnection();
        var preparedStatement = connection.prepareStatement(sql);
        for (var i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }
        return preparedStatement.execute();
    }

    /***
     * 删除表当中的元素
     * @param tableName 表名
     * @param attrsMap 属性字典 key: 表当中的列名，value: 其中的属性值
     * @return 是否删除成功
     * @throws SQLException
     */
    public static boolean delete(String tableName, Map<String, String> attrsMap) throws SQLException {
        var connection = DBConnection.getConnection();
        var stringBuilder = new StringBuilder("delete from ").append(tableName);
        if (Objects.nonNull(attrsMap)) {
            stringBuilder.append(" where ");
            var attrsMapStr = attrsMap.toString().substring(1, attrsMap.toString().length() - 1).replace(",", " or ");
            stringBuilder.append(attrsMapStr);
        }
        var preparedStatement = connection.prepareStatement(stringBuilder.toString());
        return preparedStatement.execute();
    }
}
