package com.example.refilltracker.Fill;

import java.io.Serializable;

public class InfoCost implements Serializable {
    String date;
    String time;
    String place;
    String type;
    String price;
    String typeCar;
    String number;
    public InfoCost(){};
    public InfoCost(String date, String time, String type, String price, String typeCar, String number) {
        this.date = date;
        this.time = time;
        this.type = type;
        this.price = price;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
