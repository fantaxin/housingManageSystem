package com.example.Servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.DAO.DataBase;
import com.example.Entity.Apartment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.Entity.Community;
import com.example.Main;

@WebServlet(name = "ApartmentByPOIServlet", value = "/ApartmentByPOIServlet")
public class ApartmentByPOIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBase db = Main.db;
        String poi_name = req.getParameter("poi_name");
        List<Apartment> list = db.SearchApartmentPOI(poi_name);
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
