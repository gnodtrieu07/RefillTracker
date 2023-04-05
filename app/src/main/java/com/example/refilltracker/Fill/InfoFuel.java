package com.example.refilltracker.Fill;

import java.io.Serializable;

public class InfoFuel implements Serializable {
    String date;
    String time;
    String place;
    String price;
    public static String lit;
    String typeCar;
    String number;
    public InfoFuel(){};
    public InfoFuel(String date, String time, String place, String price, String lit, String typeCar, String number) {
        this.date = date;
        this.time = time;
        this.place = place;
        this.price = price;
        this.lit = lit;
        this.typeCar = typeCar;
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLit() {
        return lit;
    }

    public void setLit(String lit) {
        this.lit = lit;
    }

    public String getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(String typeCar) {
        this.typeCar = typeCar;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
