package com.example.refilltracker.Primary.Data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    public String user;
    public String date;
    public String time;
    public String place;
    public String price;
    public String lit;

    public User(String user, String date, String time, String place, String price, String lit) {
        this.user = user;
        this.date = date;
        this.time = time;
        this.place = place;
        this.price = price;
        this.lit = lit;
    }
}
