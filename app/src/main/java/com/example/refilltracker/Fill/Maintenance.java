package com.example.refilltracker.Fill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.refilltracker.Primary.Other;
import com.example.refilltracker.R;
import com.example.refilltracker.Repair.ShopAActivity;
import com.example.refilltracker.Repair.ShopBActivity;
import com.example.refilltracker.Repair.ShopCActivity;
import com.example.refilltracker.Repair.ShopDActivity;
import com.example.refilltracker.Repair.ShopEActivity;
import com.example.refilltracker.Repair.ShopFActivity;

public class Maintenance extends AppCompatActivity {
    private ImageView imgA;
    private ImageView imgB;
    private ImageView imgC;
    private ImageView imgD;
    private ImageView imgE;
    private ImageView imgF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);


        imgA= findViewById(R.id.shopA);
        imgB = findViewById(R.id.shopB);
        imgC = findViewById(R.id.shopC);
        imgD = findViewById(R.id.shopD);
        imgE = findViewById(R.id.shopE);
        imgF = findViewById(R.id.shopF);

        imgA.setOnClickListener(v -> {
            Intent intent = new Intent(Maintenance.this, ShopAActivity.class);
            startActivity(intent);
        });

        imgB.setOnClickListener(v -> {
            Intent intent = new Intent(Maintenance.this, ShopBActivity.class);
            startActivity(intent);
        });

        imgC.setOnClickListener(v -> {
            Intent intent = new Intent(Maintenance.this, ShopCActivity.class);
            startActivity(intent);
        });

        imgD.setOnClickListener(v -> {
            Intent intent = new Intent(Maintenance.this, ShopDActivity.class);
            startActivity(intent);
        });

        imgE.setOnClickListener(v -> {
            Intent intent = new Intent(Maintenance.this, ShopEActivity.class);
            startActivity(intent);
        });

        imgF.setOnClickListener(v -> {
            Intent intent = new Intent(Maintenance.this, ShopFActivity.class);
            startActivity(intent);
        });
    }
}