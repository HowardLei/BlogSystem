package com.blogSystem.servlet;

import com.blogSystem.database.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ListServlet class
 *
 * @author apple
 * @date 2019-08-13
 */
@WebServlet(name = "ListServlet", description = "列举出所有数据", urlPatterns = {"/list"})
public class ListServlet extends HttpServlet {
    private static final String UTF_8_ENCODING = "utf-8";
    private static final int LINE_LIMITS = 10;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(UTF_8_ENCODING);
        response.setCharacterEncoding(UTF_8_ENCODING);
        response.setContentType("text/html;charset=utf-8");
        var account = request.getSession().getAttribute("account");
        var sql = String.format("select * from blog limit 0,%d", LINE_LIMITS);
        var list = DB.select(sql);
        list.forEach(System.out::println);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
