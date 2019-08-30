package com.blogSystem.database;

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
    public static final String AND = " and";

    public static void main(String[] args) {
        var title = "title";
        title = String.join(title, "34");
        System.out.println(title);
    }

    /**
     * 查找数据库当中的元素
     * @param sql 查找的 SQL 语句
     * @param attrs 想要获得的属性名
     * @return 属性列表
     */
    public static List<Map<String, Object>> select(String sql, String... attrs) {
        var list = new ArrayList<Map<String, Object>>(5);
        // 加载数据库驱动
        try {
            // 建立数据库链接
            var connection = DBConnection.getConnection();
            // 创建一个“小手”，操作数据库
            var statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                var result = new HashMap<String, Object>(5);
                for (var attr : attrs) {
                    result.put(attr, resultSet.getObject(attr));
                }
                list.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close();
        }
        list.trimToSize();
        return list;
    }

    /***
     * 查找数据库当中是否有该用户
     * @param tableName 需要查找的表名
     * @param attrMap 属性字典 key: 表当中的列名，value: 其中的属性值
     * @param predicate SQL 语句当中的谓词,
     * @return 属性与属性值是否完全一一对应
     */
    public static boolean select(String tableName, Map<String, String> attrMap, String predicate) {
        var connection = DBConnection.getConnection();
        var stringBuilder = new StringBuilder("select * from ").append(tableName);
        if (Objects.nonNull(attrMap)) {
            stringBuilder.append(" where ");
            String res = attrMap.toString().substring(1, attrMap.toString().length() - 1).replace(",", predicate);
            stringBuilder.append(res).append(';');
            try {
                var statement = connection.createStatement();
                var resultSet = statement.executeQuery(stringBuilder.toString());
                while (resultSet.next()) {
                    var userClass = Class.forName("com.blogSystem.entity.User");
                    var fields = userClass.getDeclaredFields();
                    var propList = new String[fields.length];
                    for (var i = 0; i < fields.length; i++) {
                        propList[i] = fields[i].getName();
                    }
                    for (var i = 0; i < propList.length; i++) {
                        var dataFromSet = String.format("\'%s\'", resultSet.getString(propList[i]));
                        if (!Objects.equals(dataFromSet, attrMap.get(propList[i]))) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean insert(String sql, Object... objects) throws SQLException {
        var connection = DBConnection.getConnection();
        var preparedStatement = connection.prepareStatement(sql);
        if (objects.length > 0) {
            for (var i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i + 1, objects[i]);
            }
        }
        return preparedStatement.execute();
    }

    /**
     * 向数据库当中添加数据
     *
     * @param tableName 表名
     * @param attrMap 属性字典 key: 表当中的列名，value: 其中的属性值
     * @return 是否添加成功
     */
    public static boolean insert(String tableName, Map<String, String> attrMap) {
        if (Objects.isNull(tableName) || Objects.isNull(attrMap)) {
            return false;
        } else {
            var connection = DBConnection.getConnection();
            var stringBuilder = new StringBuilder("insert into ").append(tableName);
            // 将属性名转换成字符串
            var attrSet = attrMap.keySet();
            var transArray = new String[attrSet.size()];
            transArray = attrSet.toArray(transArray);
            var str = Arrays.toString(transArray);
            str = str.replace('[', '(').replace(']', ')');
            stringBuilder.append(str).append(" values ");
            // 将属性值转换成字符串
            var values = attrMap.values();
            transArray = values.toArray(transArray);
            str = Arrays.toString(transArray);
            str = str.replace('[', '(').replace(']', ')');
            stringBuilder.append(str).append(";");
            try {
                var statement = connection.createStatement();
                var execute = statement.executeUpdate(stringBuilder.toString());
                System.out.println(execute);
                return execute == 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /***
     * 删除表当中的元素
     * @param tableName 表名
     * @param attrsMap 属性字典 key: 表当中的列名，value: 其中的属性值
     * @return 是否删除成功
     * @throws SQLException 当执行 SQL 语句失败的时候，抛出此异常。
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

    /**
     * 向数据库当中更新数据
     * @param tableName 表名
     * @param limitAttrMap 需要限制的属性字典 key: 表当中的列名，value: 其中的属性值
     * @param changeAttrMap 需要更新的属性字典 key: 表当中的列名，value: 其中的属性值
     */
    public static void update(String tableName, Map<String, String> limitAttrMap, Map<String, String> changeAttrMap) {
    }
}