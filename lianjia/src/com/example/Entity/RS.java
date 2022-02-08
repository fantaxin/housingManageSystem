package com.example.Entity;

public class RS {
    double avg_price;
    double avg_avgprice;
    int count;

    public RS() {
    }

    public RS(double avg_price, double avg_avgprice, int count) {
        this.avg_price = avg_price;
        this.avg_avgprice = avg_avgprice;
        this.count = count;
    }

    public double getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(double avg_price) {
        this.avg_price = avg_price;
    }

    public double getAvg_avgprice() {
        return avg_avgprice;
    }

    public void setAvg_avgprice(double avg_avgprice) {
        this.avg_avgprice = avg_avgprice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
