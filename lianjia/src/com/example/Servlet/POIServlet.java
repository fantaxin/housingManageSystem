package com.example.Servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.DAO.DataBase;
import com.example.Entity.Apartment;
import com.example.Entity.Community;
import com.example.Entity.POI;
import com.example.Main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name= "POIServlet", value="/POIServlet")
public class POIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBase db = Main.db;
        String select_poi = req.getParameter("select_poi_type");
        String c_name = req.getParameter("c_name");
        String c_type = req.getParameter("c_type");
        List<POI> list = db.SearchPOICommunity(c_name,c_type,select_poi+"%");

        JSONArray jsonClist = new JSONArray();
        for (int i = 0; i <list.size(); i++) {
            JSONObject jo = new JSONObject();
            POI poi = list.get(i);
            jo.put("name",poi.getName());
            switch(poi.getPoiId().toCharArray()[0]){
                case '1':
                    jo.put("type","交通设施");break;
                case '3':
                    jo.put("type","公园广场");break;
                case '4':
                    jo.put("type","学校教育");break;
                case '5':
                    jo.put("type","医院诊所");break;
                case '8':
                    jo.put("type","商店购物");break;
                default:
                    jo.put("type","其它");
            }
            jo.put("x",poi.getX());
            jo.put("y",poi.getY());
            jsonClist.add(jo);
        }
        System.out.println(jsonClist.toJSONString());
        req.setAttribute("clist",jsonClist.toJSONString());
        req.setAttribute("flag",0);
        req.getRequestDispatcher("search_map.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
