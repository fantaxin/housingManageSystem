package com.example.Entity;

public class Apartment {
    private int id;
    private String type;
    private double price;
    private double avgprice;
    private double jarea;
    private double tarea;
    private String face;
    private String community_name;
    private String community_type;
    private String floor;
    private int allfloor;
    private int time;
    private String jztype;
    private String comment;

    public Apartment() {
    }

    public Apartment(String type, double price, double avgprice, double jarea, double tarea, String face, String community_name, String community_type, String floor, int allfloor, int time, String jztype) {
        this.type = type;
        this.price = price;
        this.avgprice = avgprice;
        this.jarea = jarea;
        this.tarea = tarea;
        this.face = face;
        this.community_name = community_name;
        this.community_type = community_type;
        this.floor = floor;
        this.allfloor = allfloor;
        this.time = time;
        this.jztype = jztype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAvgprice() {
        return avgprice;
    }

    public void setAvgprice(double avgprice) {
        this.avgprice = avgprice;
    }

    public double getJarea() {
        return jarea;
    }

    public void setJarea(double jarea) {
        this.jarea = jarea;
    }

    public double getTarea() {
        return tarea;
    }

    public void setTarea(double tarea) {
        this.tarea = tarea;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getCommunity_name() {
        return community_name;
    }

    public void setCommunity_name(String community_name) {
        this.community_name = community_name;
    }

    public String getCommunity_type() {
        return community_type;
    }

    public void setCommunity_type(String community_type) {
        this.community_type = community_type;
    }

    public int getAllfloor() {
        return allfloor;
    }

    public void setAllfloor(int allfloor) {
        this.allfloor = allfloor;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getJztype() {
        return jztype;
    }

    public void setJztype(String jztype) {
        this.jztype = jztype;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
