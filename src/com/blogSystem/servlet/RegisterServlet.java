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
 * RegisterServlet class
 *
 * @author apple
 * @date 2019-08-10
 */
@WebServlet(name = "RegisterServlet", description = "用于注册的Servlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    private static final String UTF8ENCODING = "utf-8";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(UTF8ENCODING);
        response.setCharacterEncoding(UTF8ENCODING);
        response.setContentType("type=text/html;charset=utf-8");
        var account = request.getParameter("account");
        var pwd = request.getParameter("pwd");
        var infoMap = new HashMap<String, String>(2);
        infoMap.put("account", String.format("\'%s\'", account));
        infoMap.put("pwd", String.format("\'%s\'", pwd));
        var isInsert = DB.insert("user", infoMap);
        // 创建返回值的字典
        var jsonMap = new HashMap<String, String>();
        if (isInsert) {
            jsonMap.put("\"code\"", "\"200\"");
        } else {
            jsonMap.put("\"code\"", "\"403\"");
        }
        var jsonStr = jsonMap.toString().replace("=", ": ");
        System.out.println(jsonStr);
        response.getWriter().append(jsonStr);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
