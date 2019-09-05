package com.blogSystem.servlet;

import com.blogSystem.database.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * DeleteServlet class
 *
 * @author apple
 * @date 2019/9/4
 */
@WebServlet(name = "DeleteServlet", urlPatterns = {"/delete"}, description = "删除博客")
public class DeleteServlet extends HttpServlet {
    private static final String UTF_8_ENCODING = "utf-8";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(UTF_8_ENCODING);
        response.setCharacterEncoding(UTF_8_ENCODING);
        response.setContentType("text/html;charset=utf-8");
        var id = request.getParameter("id");
        var attrMap = new HashMap<String, String>(1);
        attrMap.put("id", id);
        String json = null;
        try {
            var isDelete = DB.delete("blog", attrMap, DB.AND);
            if (isDelete) {
                json = "{\"message\": \"200\"}";
            } else {
                json = "{\"message\": \"403\"}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().append(json);
    }
}
