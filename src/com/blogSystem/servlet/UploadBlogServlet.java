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
        title = String.join("\'", title);
        var content = request.getParameter("content");
        var jsonMap = new HashMap<String, String>(1);
        // 根据标题是否相同来判断有没有相同文章
        var selectTitleSQL = String.format("select title from blog where title = %s;", title);
        var titleList = DB.select(selectTitleSQL, "title");
        if (titleList.size() == 0) {
            // 获得他的账号信息
            var account = request.getSession().getAttribute("account");
            var sqlBuilder = new StringBuilder("select id nickName from user where account = \'");
            sqlBuilder.append(account).append("\';");
            var list = DB.select(sqlBuilder.toString(), "id", "nickName");
            var stringObjectMap = list.get(0);
            var userId = stringObjectMap.get("id");
            var author = stringObjectMap.get("author");
            var dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            var date = dateFormat.format(new Date());
            sqlBuilder.delete(0, sqlBuilder.length());
            var attrMap = new HashMap<String, Object>(6);
            attrMap.put("title", title);
            attrMap.put("content", content);
            attrMap.put("author", author);
            attrMap.put("type", "随笔");
            attrMap.put("userId", userId);
            attrMap.put("createdTime", date);
            sqlBuilder.append("insert into blog (title, content, author, type, userId, createdTime) values (");
            sqlBuilder.append("\'").append(title).append("\',");
            sqlBuilder.append('\'').append(content).append("\',");
            sqlBuilder.append('\'').append(author).append("\',");
            sqlBuilder.append('\'').append("随笔").append("\',");
            sqlBuilder.append(Long.valueOf(userId.toString())).append(",");
            sqlBuilder.append('\'').append(date).append("\');");
//            DB.insert("blog", )
        } else {
            jsonMap.put("msg", "403");
        }
        response.getWriter().append(jsonMap.toString().replace('=', ':'));
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
