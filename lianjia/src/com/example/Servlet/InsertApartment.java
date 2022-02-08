package com.example.Servlet;

import com.example.DAO.DataBase;
import com.example.Main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( name = "InsertApartment", value = "/InsertApartment")
public class InsertApartment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        DataBase db = Main.db;
        request.setCharacterEncoding("utf-8");//设置编码格式
        response.setContentType("text/plain; charset=utf-8");
        String community_name,community_type,type,price,jarea,tarea,face,allfloor,time,floor,jztype = "";
        community_name = request.getParameter("community_name");
        community_type = request.getParameter("community_type");
        type = request.getParameter("type");
        price = request.getParameter("price");
        jarea = request.getParameter("jarea");
        tarea = request.getParameter("tarea");
        face = request.getParameter("face");
        allfloor = request.getParameter("allfloor");
        time = request.getParameter("time");
        floor = request.getParameter("floor");
        System.out.println(community_name+'|'+community_type+'|'+type+'|'+price+'|'+jarea+'|'+tarea+'|'+face+'|'+allfloor+'|'+time+'|'+floor);
        String res = db.InsertApartment(type,Double.parseDouble(price),0.0,Double.parseDouble(jarea),Double.parseDouble(tarea),face,community_name,community_type,floor,Integer.parseInt(allfloor),Integer.parseInt(time),jztype)?"1":"0";
        System.out.println(res);
        response.getWriter().print(res);
    }
}
