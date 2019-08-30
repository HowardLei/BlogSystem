package com.blogSystem.servlet;

import com.blogSystem.database.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * EditServlet class
 *
 * @author apple
 * @date 2019/8/30
 */
@WebServlet(name = "EditServlet", urlPatterns = {"/edit"}, description = "更改博客当中的内容")
public class EditServlet extends HttpServlet {
    private static final String UTF_8_ENCODING = "utf-8";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(UTF_8_ENCODING);
        response.setCharacterEncoding(UTF_8_ENCODING);
        response.setContentType("text/html;charset=utf-8");
        var id = request.getParameter("id");
        var stringBuilder = new StringBuilder("select title, content from blog ");
        stringBuilder.append("where id = ").append(id).append(';');
        var list = DB.select(stringBuilder.toString(), "title", "content");
        if (list.size() == 1) {
            var attrMap = list.get(0);
            request.setAttribute("title", attrMap.get("title"));
            request.setAttribute("content", attrMap.get("content"));
            request.getRequestDispatcher("/BlogSystem/jsp/changeBlog.jsp").forward(request, response);
        } else {
            System.out.println("111");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
