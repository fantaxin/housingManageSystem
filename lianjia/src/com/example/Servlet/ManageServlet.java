package com.example.Servlet;

import com.alibaba.fastjson.JSON;
import com.example.DAO.DataBase;
import com.example.Entity.Apartment;
import com.example.Main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet(name = "ManageServlet", value = "/ManageServlet")
public class ManageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBase db = Main.db;

        req.setCharacterEncoding("UTF-8");
        String select = req.getParameter("select_district");
        String type = req.getParameter("type");
        String price = req.getParameter("price");
        String avgprice = req.getParameter("avgprice");
        String jarea = req.getParameter("jarea");
        String tarea = req.getParameter("tarea");
        String face = req.getParameter("face");
        String community_name = req.getParameter("community_name");
        String time = req.getParameter("time");
        System.out.println(select);
        List<Apartment> list = db.SearchApartment(type+"%",price+"%",avgprice+"%",jarea+"%",
                tarea+"%", face+"%",community_name+"%","%","%",
                "%",time+"%","%",select);

        req.setAttribute("apartment_list",list);
        req.getRequestDispatcher("manage.jsp").forward(req,resp);

        /*//输出路径
        String path = "E:\\a_java project\\lianjia_web\\lianjia\\web\\data\\manage.json";
        //路径
        File file = new File(path);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdir();
        }
        file.createNewFile();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
        writer.write("{\"code\": 0,\"msg\": \"\",\"count\": " +
                (list.size()+1) +
                ",\"data\": [\n");

        int i=0;
        for (; i <list.size()-1; i++) {
            Apartment apartment = list.get(i);
            String userData = JSON.toJSONString(apartment);
            writer.write(userData+",\n");
        }
        Apartment apartment = list.get(i);
        String userData = JSON.toJSONString(apartment);
        writer.write(userData);
        writer.write("]}");
        writer.flush();
        writer.close();

        PrintWriter out = resp.getWriter();
        out.println("<script language='JavaScript'> location.href='/lianjia/manage.jsp';</script>");*/

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
