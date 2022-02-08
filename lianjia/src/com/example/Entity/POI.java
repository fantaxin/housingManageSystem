package com.example.Entity;

import com.microsoft.sqlserver.jdbc.Geometry;

public class POI {
    private String poiId;
    private String name;
    private String location;
    private String district_name;
    private String big_category;
    private String mid_category;
    private String small_category;
    private Geometry geom;
    private double x;
    private double y;

    public POI() {
    }

    public POI(String poiId, String name, String location, String big_category, String mid_category, String small_category, Geometry geom) {
        this.poiId = poiId;
        this.name = name;
        this.location = location;
        this.big_category = big_category;
        this.mid_category = mid_category;
        this.small_category = small_category;
        this.geom = geom;
    }

    public POI(String poiId, String name, String location, String district_name, String big_category, String mid_category, String small_category, Geometry geom) {
        this.poiId = poiId;
        this.name = name;
        this.location = location;
        this.district_name = district_name;
        this.big_category = big_category;
        this.mid_category = mid_category;
        this.small_category = small_category;
        this.geom = geom;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBig_category() {
        return big_category;
    }

    public void setBig_category(String big_category) {
        this.big_category = big_category;
    }

    public String getMid_category() {
        return mid_category;
    }

    public void setMid_category(String mid_category) {
        this.mid_category = mid_category;
    }

    public String getSmall_category() {
        return small_category;
    }

    public void setSmall_category(String small_category) {
        this.small_category = small_category;
    }

    public Geometry getGeom() {
        return geom;
    }

    public void setGeom(Geometry geom) {
        this.geom = geom;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
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
