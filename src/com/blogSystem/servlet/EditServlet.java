package com.blogSystem.servlet;

import com.blogSystem.database.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * EditServlet class
 *
 * @author apple
 * @date 2019/8/30
 */
@WebServlet(name = "EditServlet", value = {"/edit"}, description = "更改博客当中的内容")
public class EditServlet extends HttpServlet {
    private static final String UTF_8_ENCODING = "utf-8";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(UTF_8_ENCODING);
        response.setCharacterEncoding(UTF_8_ENCODING);
        response.setContentType("text/html;charset=utf-8");
        var id = request.getParameter("id");
        var title = request.getParameter("title");
        var content = request.getParameter("content");
        var attrMap = new HashMap<String, String>(1);
        attrMap.put("id", id);
        var limitMap = new HashMap<String, String>(2);
        limitMap.put("title", title);
        limitMap.put("content", content);
        var update = DB.update("blog", limitMap, attrMap, DB.AND);
        String json = null;
        if (update == null) {
            json = "{\"message\": \"403\"}";
        } else {
            if (update.equals(1)) {
                json = "{\"message\": \"200\"}";
            } else {
                json = "{\"message\": \"403\"}";
            }
        }
        response.getWriter().append(json);
    }

    /**
     * 在 home.html 当中点击编辑按钮以后调用 get 请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(UTF_8_ENCODING);
        response.setCharacterEncoding(UTF_8_ENCODING);
        response.setContentType("text/html;charset=utf-8");
        var id = request.getParameter("id");
        var stringBuilder = new StringBuilder("select title, content ");
        stringBuilder.append("from blog ");
        stringBuilder.append("where id = ").append(id).append(';');
        var list = DB.select(stringBuilder.toString(), "title", "content");
        var map = list.get(0);
        for (var entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        request.getRequestDispatcher("/html/index.jsp").forward(request, response);
    }
}
