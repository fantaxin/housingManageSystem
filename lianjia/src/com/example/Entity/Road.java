package com.example.Entity;

import com.microsoft.sqlserver.jdbc.Geometry;

public class Road {
    private Integer road_fid;
    private String osm_id;
    private Integer code;
    private String fclass;
    private String name;
    private String bridge;
    private String tunnel;
    private Geometry geom;

    public Road(){
    }
    public Road(Integer road_fid, String osm_id, Integer code, String fclass, String name, String bridge, String tunnel)
    {
        this.road_fid=road_fid;
        this.osm_id=osm_id;
        this.code=code;
        this.fclass=fclass;
        this.name=name;
        this.bridge=bridge;
        this.tunnel=tunnel;
    }

    public Road(Integer road_fid, String osm_id, Integer code, String fclass, String name, String bridge, String tunnel, Geometry geom) {
        this.road_fid = road_fid;
        this.osm_id = osm_id;
        this.code = code;
        this.fclass = fclass;
        this.name = name;
        this.bridge = bridge;
        this.tunnel = tunnel;
        this.geom = geom;
    }

    public void setRoad_fid(Integer road_fid) {
        this.road_fid = road_fid;
    }

    public void setOsm_id(String osm_id) {
        this.osm_id = osm_id;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setFclass(String fclass) {
        this.fclass = fclass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBridge(String bridge) {
        this.bridge = bridge;
    }

    public void setTunnel(String tunnel) {
        this.tunnel = tunnel;
    }

    public Integer getRoad_fid() {
        return road_fid;
    }

    public String getOsm_id() {
        return osm_id;
    }

    public Integer getCode() {
        return code;
    }

    public String getFclass() {
        return fclass;
    }

    public String getName() {
        return name;
    }

    public String getBridge() {
        return bridge;
    }

    public String getTunnel() {
        return tunnel;
    }

    public Geometry getGeom() {
        return geom;
    }

    public void setGeom(Geometry geom) {
        this.geom = geom;
    }
}
