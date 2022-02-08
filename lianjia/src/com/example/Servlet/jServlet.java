package com.example.Servlet;

import com.example.Main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value="/jServlet")
public class jServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("GBK");
        PrintWriter out = resp.getWriter();
        if(Main.user==null||Main.user.getAccount()==null||Main.user.getAccount().isEmpty()){
            resp.sendRedirect("/lianjia");
            return;
        }
        resp.sendRedirect("main.jsp");
    }
}
