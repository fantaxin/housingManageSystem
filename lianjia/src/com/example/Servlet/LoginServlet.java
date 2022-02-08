package com.example.Servlet;

import com.example.DAO.DataBase;
import com.example.Entity.User;
import com.example.Main;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        DataBase db = Main.db;

        req.setCharacterEncoding("UTF-8");
        String flag = req.getParameter("flag");
        String account = req.getParameter("account");
        String password = req.getParameter("password");

        System.out.println(account+"||"+password);

        Main.user.setAccount(account);
        Main.user.setPassword(password);
        int a = db.RI_USER(Main.user,Integer.parseInt(flag));

        System.out.println(a);

        res.setCharacterEncoding("GBK");
        PrintWriter out = res.getWriter();

        switch(a){
            case 3:
                out.println("<script language='JavaScript'> location.href='/lianjia';alert('用户不存在！');</script>");
                System.out.println("用户不存在！");
                break;
            case 4:
                out.println("<script language='JavaScript'> location.href='/lianjia';alert('密码错误！');</script>");
                System.out.println("密码错误！");
                break;
            case 5:
                out.println("<script language='JavaScript'> location.href='/lianjia';alert('用户已存在！');</script>");
                System.out.println("用户已存在！");
            case 6:
                req.getSession().setAttribute("user",Main.user);
                out.println("<script language='JavaScript'> location.href='/lianjia/main.jsp';alert('登录成功！'); </script>");
                System.out.println("登录成功！");
                break;
            case 7:
                out.println("<script language='JavaScript'> location.href='/lianjia';alert('注册成功！');</script>");
                System.out.println("注册成功！");
                break;
            default:
                out.println("<script language='JavaScript'> location.href='/lianjia';alert('未知错误！');</script>");
                System.out.println("未知错误！");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
