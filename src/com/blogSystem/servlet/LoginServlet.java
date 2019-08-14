package com.blogSystem.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LoginServlet class
 *
 * @author apple
 * @date 2019-08-12
 */
@WebServlet(name = "LoginServlet", description = "用于登录的 Servlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final String UTF8ENCODING = "utf-8";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(UTF8ENCODING);
        response.setCharacterEncoding(UTF8ENCODING);
        response.setContentType("text/html;charset=utf-8");
        var userName = request.getParameter("userName");
        var password = request.getParameter("password");

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("html/login.html").forward(request, response);
    }
}
