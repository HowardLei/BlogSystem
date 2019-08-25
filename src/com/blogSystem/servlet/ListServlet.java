package com.blogSystem.servlet;

import com.blogSystem.database.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * ListServlet class
 *
 * @author apple
 * @date 2019-08-13
 */
@WebServlet(name = "ListServlet", description = "列举出所有数据", urlPatterns = {"/list"})
public class ListServlet extends HttpServlet {
    private static final String UTF_8_ENCODING = "utf-8";
    private static final int LINE_LIMITS = 10;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(UTF_8_ENCODING);
        response.setCharacterEncoding(UTF_8_ENCODING);
        response.setContentType("text/html;charset=utf-8");
        var account = request.getSession().getAttribute("account");
        var searchID = String.format("select id from user where account = \'%s\'", account);
        var res = DB.select(searchID,"id");
        String id = null;
        for (Map<String, Object> map : res) {
            id = String.valueOf(map.get("id"));
        }
        // 通过 sqlBuilder 构造 SQL 语句
        var sqlBuilder = new StringBuilder("select ");
        sqlBuilder.append("title, ").append("author, ").append("blog.createdTime ");
        sqlBuilder.append("from user, blog ");
        sqlBuilder.append("where blog.author = user.nickName").append(" && ").append("blog.userID = ").append(id);
        sqlBuilder.append(" limit 0, ").append(LINE_LIMITS).append(';');
        var sql = sqlBuilder.toString();
        var fields = new String[] {"title", "author", "createdTime"};
        var list = DB.select(sql, fields);
        var jsonStringBuilder = new StringBuilder("[");
        for (var map : list) {
            jsonStringBuilder.append(map.toString());
        }
        jsonStringBuilder.append(']');
        var json = jsonStringBuilder.toString().replace("=", "\" =\"").replace("}{", "},{").replace("{", "{\"").replace(", ", "\", \"").replace("}", "\"}").replace('=', ':');
        response.getWriter().append(json);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
