package com.blogSystem.servlet;

import com.blogSystem.database.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * UploadBlogServlet class
 *
 * @author apple
 * @date 2019-08-26
 */
@WebServlet(name = "UploadBlogServlet", value = {"/uploadBlog"}, description = "上传博客的 Servlet")
public class UploadBlogServlet extends HttpServlet {
    private static final String UTF_8_ENCODING = "utf-8";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(UTF_8_ENCODING);
        response.setCharacterEncoding(UTF_8_ENCODING);
        response.setContentType("type/html,charset=utf-8");
        var title = request.getParameter("title");
        var content = request.getParameter("content");
        var jsonMap = new HashMap<String, String>(1);
        // 根据标题是否相同来判断有没有相同文章
        var sqlBuilder = new StringBuilder("select title from blog where title = ");
        sqlBuilder.append('\'').append(title).append("\';");
        var titleList = DB.select(sqlBuilder.toString(), "title");
        if (titleList.size() == 0) {
            // 获得他的账号信息
            var account = request.getSession().getAttribute("account");
            sqlBuilder.delete(0, sqlBuilder.length());
            sqlBuilder.append("select id, nickName from user where account = \'");
            sqlBuilder.append(account).append("\';");
            var list = DB.select(sqlBuilder.toString(), "id", "nickName");
            var stringObjectMap = list.get(0);
            var userId = stringObjectMap.get("id");
            var author = stringObjectMap.get("nickName");
            var dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            var date = dateFormat.format(new Date());
            var attrMap = new HashMap<String, String>(6);
            attrMap.put("title", String.format("\'%s\'", title));
            attrMap.put("content", String.format("\'%s\'", content));
            attrMap.put("author", String.format("\'%s\'", author));
            attrMap.put("type", "\'随笔\'");
            attrMap.put("userId", String.valueOf(userId));
            attrMap.put("createdTime", String.format("\'%s\'", date));
            var isInsert = DB.insert("blog", attrMap);
            if (isInsert) {
                jsonMap.put("\"msg\"", "\"200\"");
            } else {
                jsonMap.put("\"msg\"", "\"403\"");
            }
        } else {
            jsonMap.put("\"msg\"", "\"403\"");
        }
        response.getWriter().append(jsonMap.toString().replace('=', ':'));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
