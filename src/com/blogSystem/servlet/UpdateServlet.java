package com.blogSystem.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ￿￿UpdateServlet class
 *
 * @author apple
 * @date 2019-08-14
 */
@WebServlet(name = "UpdateServlet", description = "更新数据的Servlet", urlPatterns = {"/update"})
public class UpdateServlet extends HttpServlet {
    private static final String UTF8ENCODING = "utf-8";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(UTF8ENCODING);
        response.setCharacterEncoding(UTF8ENCODING);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
