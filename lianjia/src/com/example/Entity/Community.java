package com.example.Entity;

import com.microsoft.sqlserver.jdbc.Geometry;

public class Community {
    private String uid;
    private String name;
    private String type;
    private String district_name;
    private double avgprice;
    private boolean sell;
    private float rjl;
    private float lhl;
    private Geometry geom;
    private double x;
    private double y;

    public Community() {
    }

    public Community(String uid, String name, String type, String district_name, double avgprice, boolean sell, float rjl, float lhl, Geometry geom) {
        this.uid = uid;
        this.name = name;
        this.type = type;
        this.district_name = district_name;
        this.avgprice = avgprice;
        this.sell = sell;
        this.rjl = rjl;
        this.lhl = lhl;
        this.geom = geom;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public double getAvgprice() {
        return avgprice;
    }

    public void setAvgprice(double avgprice) {
        this.avgprice = avgprice;
    }

    public boolean isSell() {
        return sell;
    }

    public void setSell(boolean sell) {
        this.sell = sell;
    }

    public float getRjl() {
        return rjl;
    }

    public void setRjl(float rjl) {
        this.rjl = rjl;
    }

    public float getLhl() {
        return lhl;
    }

    public void setLhl(float lhl) {
        this.lhl = lhl;
    }

    public Geometry getGeom() {
        return geom;
    }

    public void setGeom(Geometry geom) {
        this.geom = geom;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
