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
 * LoginServlet class
 *
 * @author apple
 * @date 2019-08-12
 */
@WebServlet(name = "LoginServlet", description = "用于登录的 Servlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final String UTF_8_ENCODING = "utf-8";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(UTF_8_ENCODING);
        response.setCharacterEncoding(UTF_8_ENCODING);
        response.setContentType("text/html;charset=utf-8");
        var account = request.getParameter("account");
        var pwd = request.getParameter("pwd");
        var map = new HashMap<String, String>(2);
        map.put("account", String.format("\'%s\'", account));
        map.put("pwd", String.format("\'%s\'", pwd));
        var res = DB.select("user", map);
        if (res) {
            response.sendRedirect("html/home.html");
        } else {
            // FIXME: 2019-08-17 当登录失败了以后，还不知道如何处理
            request.getRequestDispatcher("");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
