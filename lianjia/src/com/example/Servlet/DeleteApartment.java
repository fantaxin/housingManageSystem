package com.example.Servlet;

import com.example.DAO.DataBase;
import com.example.Main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( name = "DeleteApartment", value = "/DeleteApartment")
public class DeleteApartment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        DataBase db = Main.db;
        request.setCharacterEncoding("utf-8");//设置编码格式
        String id_str = request.getParameter("id");
        System.out.println(id_str);
        int id = Integer.parseInt(id_str);
        String req = db.DeleteApartmentId(id)?"1":"0";
        response.getWriter().write("1");
}

}
