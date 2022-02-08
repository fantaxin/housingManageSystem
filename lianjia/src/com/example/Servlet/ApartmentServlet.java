package com.example.Servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.DAO.DataBase;
import com.example.Entity.Apartment;
import com.example.Entity.Community;
import com.example.Main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet(name = "ApartmentServlet", value = "/ApartmentServlet")
public class ApartmentServlet extends HttpServlet {
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

        List<Community> clist = new ArrayList<>();
        JSONArray jsonClist = new JSONArray();
        JSONArray jsonAlist = new JSONArray();
        for (int i = 0; i <list.size(); i++) {
            JSONObject jo2 = new JSONObject();
            Apartment apartment = list.get(i);
            jo2.put("type",apartment.getType());
            jo2.put("price",apartment.getPrice());
            jo2.put("avgprice",apartment.getAvgprice());
            jo2.put("jarea",apartment.getJarea());
            jo2.put("face",apartment.getFace());
            jo2.put("floor",apartment.getFloor());
            jo2.put("time",apartment.getTime());
            if(i==list.size()-1||i==0||(!clist.get(clist.size()-1).getName().equals(apartment.getCommunity_name())||!
                    clist.get(clist.size()-1).getType().equals(apartment.getCommunity_type()))) {
                clist.add(db.SearchOneCommunity(apartment.getCommunity_name(), apartment.getCommunity_type()));
                if (i <= 0){
                    jsonAlist.add(jo2);
                    continue;
                }
                JSONObject jo1 = new JSONObject();
                if(i==list.size()-1)
                    jsonAlist.add(jo2);
                jo1.put("alist", jsonAlist);
                jo1.put("name", clist.get(clist.size() - 2).getName());
                jo1.put("type", clist.get(clist.size() - 2).getType());
                jo1.put("x", clist.get(clist.size() - 2).getX());
                jo1.put("y", clist.get(clist.size() - 2).getY());
                jsonAlist = new JSONArray();
                jsonClist.add(jo1);
            }
            jsonAlist.add(jo2);
        }
        System.out.println(jsonClist.toJSONString());
        req.setAttribute("clist",jsonClist.toJSONString());
        req.setAttribute("flag",1);
        req.getRequestDispatcher("search_map.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
