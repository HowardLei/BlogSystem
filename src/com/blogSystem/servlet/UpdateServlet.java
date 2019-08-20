package com.blogSystem.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * UpdateServlet class
 *
 * @author apple
 * @date 2019-08-14
 */
@WebServlet(name = "UpdateServlet", description = "更新数据的Servlet", urlPatterns = {"/update"})
public class UpdateServlet extends HttpServlet {
    private static final String UTF_8_ENCODING = "utf-8";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(UTF_8_ENCODING);
        response.setCharacterEncoding(UTF_8_ENCODING);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}