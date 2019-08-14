package com.blogSystem.database;

import com.blogSystem.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * DB class
 *
 * @author apple
 * @date 2019-08-02
 */
public class DB {
    public static void main(String[] args) {
        var user = new User("demo","123456","wangnima");
        var map = new HashMap<String, String>(1);
        map.put("name", user.getName(true));
        map.put("userName", user.getUserName(true));
        try {
            delete("users", map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Map<String, Object>> select(String sql, String[] strings) {
        var list = new ArrayList<Map<String, Object>>();
        // 加载数据库驱动
        try {
            // 建立数据库链接
            var connection = DBConnection.getConnection();
            // 创建一个“小手”，操作数据库
            var statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                var result = new HashMap<String, Object>();
                for (var string: strings) {
                    result.put(string, resultSet.getObject(string));
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
        var stringBuilder = new StringBuilder("delete from ");
        stringBuilder.append(tableName);
        if (Objects.nonNull(attrsMap)) {
            stringBuilder.append(" where ");
            var attrsMapStr = attrsMap.toString().substring(1, attrsMap.toString().length() - 1).replace(",", " or");
            stringBuilder.append(attrsMapStr);
        }
        var preparedStatement = connection.prepareStatement(stringBuilder.toString());
        return preparedStatement.execute();
    }
}
