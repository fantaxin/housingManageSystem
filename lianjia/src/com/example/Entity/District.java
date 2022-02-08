package com.example.Entity;

import com.microsoft.sqlserver.jdbc.Geometry;

public class District {
    private long id;
    private String country;
    private String locname;
    private Geometry geom;

    public District() {
    }

    public District(String country, String locname, Geometry geom) {
        this.country = country;
        this.locname = locname;
        this.geom = geom;
    }

    public District(long id, String country, String locname, Geometry geom) {
        this.id = id;
        this.country = country;
        this.locname = locname;
        this.geom = geom;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocname() {
        return locname;
    }

    public void setLocname(String locname) {
        this.locname = locname;
    }

    public Geometry getGeom() {
        return geom;
    }

    public void setGeom(Geometry geom) {
        this.geom = geom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
