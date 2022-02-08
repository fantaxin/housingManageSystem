package com.example.DAO;
import com.example.Entity.*;
import com.microsoft.sqlserver.jdbc.Geometry;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    static final String JDBC_driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String URL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=LianJia";
    static final String userName = "sa";
    static final String userPassword = "sakura";

    private Connection conn = null;
    private Statement stmt = null;

    public DataBase() {
        conn = null;
        stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_driver);
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(URL, userName, userPassword);
            // 执行查询
            System.out.println("实例化Statement对象...");
            stmt = conn.createStatement();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
    }

    public void InsertRoad(Integer road_fid, String osm_id, Integer code, String fclass, String name, String bridge, String tunnel) {
        String SQL_INSERT_REGISTER =
                "INSERT INTO dbo.Road(road_fid, osm_id, code, fclass, name, bridge, tunnel) " +
                        "values('" + road_fid + "', '" + osm_id + "', '" + code + "', '" + fclass + "', '" + name + "', '" + bridge + "', '" + tunnel + "' ;";
        try {
            stmt.executeUpdate(SQL_INSERT_REGISTER);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void InsertRoad(Road road) {
        try {
            String sql = "insert into dbo.Road(road_fid, osm_id, code, fclass, name, bridge, tunnel)"
                    + "values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, road.getRoad_fid());
            ps.setString(2, road.getOsm_id());
            ps.setInt(3, road.getCode());
            ps.setString(4, road.getFclass());
            ps.setString(5, road.getName());
            ps.setString(6, road.getBridge());
            ps.setString(7, road.getTunnel());
            int row = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void UpdateRoadGeom(Road r, String g) {
        try {
            String sql = "update dbo.Road set geom = ? where road_fid = ?";//geom是geometry类型的坐标
            PreparedStatement ps = conn.prepareStatement(sql);

            char[] a_char = g.toCharArray();
            String geom = new String();
            for (int i = 0; i < a_char.length; i++) {
                if (a_char[i] == ',' || a_char[i] == '，')
                    a_char[i] = 32;
                else if (a_char[i] == ' ')
                    a_char[i] = ',';
                geom += a_char[i];
            }

            Geometry geometry = Geometry.parse("LINESTRING ( " + geom + " )");//首先定义geometry对象
            ps.setObject(1, geometry);//将值传入geom中

            ps.setInt(2, r.getRoad_fid());
            int row = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean InsertCommunity(String name, String type, String district_name,
                                   String sell, float rjl, float lhl, Geometry geom) {
        try {
            String sql = "insert into dbo.Community(name, type, district_name, sell, rjl, lhl, geom)"
                    + "values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, type);

            if (district_name.isEmpty())
                ps.setObject(3, null);
            else
                ps.setString(3, district_name);

            if (sell.isEmpty() || sell.equals("3"))
                ps.setObject(4, null);
            else
                ps.setString(4, sell);

            if (Float.valueOf(rjl).equals(Float.valueOf(0)))
                ps.setObject(5, null);
            else
                ps.setFloat(5, rjl);

            if (Float.valueOf(lhl).equals(Float.valueOf(0)))
                ps.setObject(6, null);
            else
                ps.setFloat(6, lhl);

            ps.setObject(7, geom);

            int row = ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean DeleteCommunity(String name, String type){
        try {
            String SQL_DELETE_COMMUNITY = "{call dbo.delete_community(?,?)}";
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE_COMMUNITY);
            ps.setString(1, name);
            ps.setString(2, type);
            int row = ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean DeleteApartmentId(int id){
        try {
            String SQL_DELETE_COMMUNITY = "{call dbo.delete_apartment_id(?)}";
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE_COMMUNITY);
            ps.setInt(1, id);
            int row = ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean InsertApartment(String type, double price, double avgprice, double jarea, double tarea,
                                   String face, String community_name, String community_type,
                                   String floor, int allfloor, int time, String jztype) {
        try {
            String sql = "insert into dbo.Apartment(type, price, avgprice, jarea, tarea, face, " +
                    "community_name, community_type, floor, allfloor, time, jztype)"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, type);
            ps.setDouble(2, price);
            ps.setDouble(3, avgprice);
            ps.setDouble(4, jarea);
            if (Double.valueOf(tarea).equals(0.0))
                ps.setObject(5, 0);
            else
                ps.setDouble(5, tarea);
            ps.setString(6, face);
            ps.setString(7, community_name);
            ps.setString(8, community_type);
            ps.setString(9, floor);
            ps.setInt(10, allfloor);
            ps.setInt(11, time);
            if (jztype.isEmpty() || jztype.equals("0"))
                ps.setObject(12, "null");
            else
                ps.setString(12, jztype);
            int row = ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.getErrorCode();
            return false;
        }
    }

    public Community SearchOneCommunity(String name, String type){
        Community c = new Community();
        String SQL_SEARCH_COMMUNITY = "{call dbo.get_community(?,?)}";
        try{
            PreparedStatement ps = conn.prepareStatement(SQL_SEARCH_COMMUNITY);
            ps.setString(1,name);
            ps.setString(2,type);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                c.setName(rs.getString(1));
                c.setType(rs.getString(2));
                c.setDistrict_name(rs.getString(3));
                c.setSell(rs.getBoolean(4));
                c.setRjl(rs.getFloat(5));
                c.setLhl(rs.getFloat(6));
                c.setX(rs.getDouble(7));
                c.setY(rs.getDouble(8));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return c;
    }

    public List<Apartment> SearchApartment(String type, String price, String avgprice, String jarea, String tarea,
                                           String face, String community_name, String community_type,
                                           String floor, String allfloor, String time, String jztype,
                                           String district_name) {
        List<Apartment> list = new ArrayList<>();
        String SQL_SEARCH_APARTMENT = "{call dbo.get_apartment(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL_SEARCH_APARTMENT);
            ps.setString(1, type);
            ps.setString(2, price);
            ps.setString(3, avgprice);
            ps.setString(4, jarea);
            ps.setString(5, tarea);
            ps.setString(6, face);
            ps.setString(7, community_name);
            ps.setString(8, community_type);
            ps.setString(9, floor);
            ps.setString(10, allfloor);
            ps.setString(11, time);
            ps.setString(12, jztype);
            ps.setString(13, district_name);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Apartment a = new Apartment();
                a.setType(rs.getString(1));
                a.setPrice(rs.getDouble(2));
                a.setAvgprice(rs.getDouble(3));
                a.setJarea(rs.getDouble(4));
                a.setTarea(rs.getDouble(5));
                a.setFace(rs.getString(6));
                a.setCommunity_name(rs.getString(7));
                a.setCommunity_type(rs.getString(8));
                a.setFloor(rs.getString(9));
                a.setAllfloor(rs.getInt(10));
                a.setTime(rs.getInt(11));
                a.setJztype(rs.getString(12));
                a.setId(rs.getInt(13));
                a.setComment(rs.getString(14));
                list.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public boolean DeleteApartment(String type, double price, double jarea,
                                   String face, String community_name, String community_type,
                                   String floor, int allfloor, int time){
        try {
            String SQL_DELETE_COMMUNITY = "{call dbo.delete_apartment(?,?,?,?,?,?,?,?,?)}";
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE_COMMUNITY);
            ps.setString(1, type);
            ps.setDouble(2, price);
            ps.setDouble(3, jarea);
            ps.setString(4, face);
            ps.setString(5, community_name);
            ps.setString(6, community_type);
            ps.setString(7, floor);
            ps.setInt(8, allfloor);
            ps.setInt(9, time);
            int row = ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public List<Apartment> SearchApartmentRoad(String name){
        List<Apartment> list = new ArrayList<>();
        try {
            String SQL_DELETE_COMMUNITY = "{call dbo.search_apartment_road(?)}";
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE_COMMUNITY);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Apartment a = new Apartment();
                a.setType(rs.getString(1));
                a.setPrice(rs.getDouble(2));
                a.setAvgprice(rs.getDouble(3));
                a.setJarea(rs.getDouble(4));
                a.setTarea(rs.getDouble(5));
                a.setFace(rs.getString(6));
                a.setCommunity_name(rs.getString(7));
                a.setCommunity_type(rs.getString(8));
                a.setFloor(rs.getString(9));
                a.setAllfloor(rs.getInt(10));
                a.setTime(rs.getInt(11));
                a.setJztype(rs.getString(12));
                a.setId(rs.getInt(13));
                a.setComment(rs.getString(14));
                list.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<Apartment> SearchApartmentPOI(String name){
        List<Apartment> list = new ArrayList<>();
        try {
            String SQL_DELETE_COMMUNITY = "{call dbo.search_apartment_poi(?)}";
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE_COMMUNITY);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Apartment a = new Apartment();
                a.setType(rs.getString(1));
                a.setPrice(rs.getDouble(2));
                a.setAvgprice(rs.getDouble(3));
                a.setJarea(rs.getDouble(4));
                a.setTarea(rs.getDouble(5));
                a.setFace(rs.getString(6));
                a.setCommunity_name(rs.getString(7));
                a.setCommunity_type(rs.getString(8));
                a.setFloor(rs.getString(9));
                a.setAllfloor(rs.getInt(10));
                a.setTime(rs.getInt(11));
                a.setJztype(rs.getString(12));
                a.setId(rs.getInt(13));
                a.setComment(rs.getString(14));
                list.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<POI> SearchPOICommunity(String name, String type, String poiId){
        List<POI> list = new ArrayList<>();
        try {
            String SQL_DELETE_COMMUNITY = "{call dbo.search_poi_community(?,?,?)}";
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE_COMMUNITY);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setString(3, poiId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                POI a = new POI();
                a.setName(rs.getString(1));
                a.setDistrict_name(rs.getString(2));
                a.setLocation(rs.getString(3));
                a.setPoiId(rs.getString(4));
                a.setX(rs.getDouble(5));
                a.setY(rs.getDouble(6));
                list.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public boolean UpdateCommunitySell(String name, String type, String sell){
        try {
            String SQL_DELETE_COMMUNITY = "{call dbo.update_community_sell(?,?,?)}";
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE_COMMUNITY);
            ps.setString(1, name);
            ps.setString(2, type);
            if (sell.isEmpty() || sell.equals("3"))
                ps.setObject(3, null);
            else
                ps.setString(3, sell);
            int row = ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean UpdateCommunityName(String name, String type, String newname){
        try {
            String SQL_DELETE_COMMUNITY = "{call dbo.update_community_name(?,?,?)}";
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE_COMMUNITY);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setString(3, newname);
            int row = ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean UpdateCommunityGeom(String name, String type, Geometry geom){
        try {
            String SQL_DELETE_COMMUNITY = "{call dbo.update_community_geom(?,?,?)}";
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE_COMMUNITY);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setObject(3, geom);
            int row = ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean UpdateApartmentPrice(int id, double price){
        try {
            String SQL_DELETE_COMMUNITY = "{call dbo.update_apartment_price(?,?)}";
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE_COMMUNITY);
            ps.setInt(1, id);
            ps.setDouble(2, price);
            int row = ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean SearchDistrictApartment(String distric_name, RS r){
        try {
            String SQL_DELETE_COMMUNITY = "{call dbo.search_district_apartment(?)}";
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE_COMMUNITY);
            ps.setString(1, distric_name);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                r.setAvg_price(rs.getDouble(1));
                r.setAvg_avgprice(rs.getDouble(2));
                r.setCount(rs.getInt(3));
            }
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public int RI_USER(User user, int flag){
        List<User> list = new ArrayList<>();
        int re = 0;
        try {
            String SQL_DELETE_COMMUNITY = "{? = call dbo.search_insert_user(?,?,?)}";
            CallableStatement ps = conn.prepareCall(SQL_DELETE_COMMUNITY);
            ps.registerOutParameter(1,Types.INTEGER);
            ps.setString(2, user.getAccount());
            ps.setString(3,user.getPassword());
            ps.setInt(4,flag);

            ps.execute();
            re = ps.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return re;
    }

    public void InsertDistrict(District d){
        try {
            String sql = "insert into dbo.District(id, country, name, geom)"
                    + "values(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, d.getId());
            ps.setString(2, d.getCountry());
            ps.setString(3, d.getLocname());
            ps.setObject(4, d.getGeom());
            int row = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean UpdateComment(String comment, int id){
        try {
            String SQL_DELETE_COMMUNITY = "{call dbo.update_comment(?,?)}";
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE_COMMUNITY);
            ps.setString(1, comment);
            ps.setInt(2, id);
            int row = ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public void InsertPOI(POI p){
        try {
            String sql = "insert into dbo.POI(poiId, name, location, big_category, mid_category, small_category, geom)"
                    + "values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getPoiId());
            ps.setString(2, p.getName());
            ps.setString(3, p.getLocation());
            ps.setString(4, p.getBig_category());
            ps.setString(5, p.getMid_category());
            ps.setString(6, p.getSmall_category());
            ps.setObject(7, p.getGeom());
            int row = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void close() {
        // 关闭资源
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException se2) {
        }// 什么都不做
        try {
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}

