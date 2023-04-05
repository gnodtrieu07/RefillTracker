package com.example.refilltracker.Primary;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.example.refilltracker.Fill.Cost;
import com.example.refilltracker.Fill.Fuel;
import com.example.refilltracker.Fill.Maintenance;
import com.example.refilltracker.Fill.Support;
import com.example.refilltracker.Location.LocationActivity;
import com.example.refilltracker.Login.LoginActivity;
import com.example.refilltracker.Primary.HistoryFill.History;
import com.example.refilltracker.Primary.HistoryFill.HistoryRepair;
import com.example.refilltracker.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Other extends AppCompatActivity {

    private ImageView imgEmergency;
    private ImageView imgRepair;
    private ImageView imgMap;
    private ImageView imgCalendar;
    private ImageView imgMaintenance;
    private ImageView imgSendemail;
    private ImageView imgEntertaiment;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        setBottomNavigation();

        imgEmergency = findViewById(R.id.img_emergency);
        imgRepair = findViewById(R.id.history_repair);
        imgMap = findViewById(R.id.img_map);
        imgCalendar = findViewById(R.id.calendar);
        imgMaintenance = findViewById(R.id.maintenance);
        imgEntertaiment = findViewById(R.id.img_entertaiment);
        imgSendemail = findViewById(R.id.img_email);
        btnLogout = findViewById(R.id.btn_logout);


        imgEmergency.setOnClickListener(v -> {
            Intent intent = new Intent(Other.this, Support.class);
            startActivity(intent);
        });

        imgRepair.setOnClickListener(v -> {
            Intent intent = new Intent(Other.this, HistoryRepair.class);
            startActivity(intent);
        });

        imgMap.setOnClickListener(v -> {
            Intent intent = new Intent(Other.this, LocationActivity.class);
            startActivity(intent);
        });

        imgEntertaiment.setOnClickListener(v -> {
            Intent intent = new Intent(Other.this, Relax.class);
            startActivity(intent);
        });

        imgSendemail.setOnClickListener(v -> {
            Intent intent = new Intent(Other.this, Relax.class);
            startActivity(intent);
        });

        imgCalendar.setOnClickListener(v -> {
            Intent intent = new Intent(Other.this, DateTime.class);
            startActivity(intent);
        });

        imgMaintenance.setOnClickListener(v -> {
            Intent intent = new Intent(Other.this, Maintenance.class);
            startActivity(intent);
        });


        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = getSharedPreferences("login", MODE_PRIVATE).edit();
            editor.putString("username", "");
            editor.putString("password", "");
            editor.putString("email", "");
            editor.putBoolean("logged", false);
            editor.apply();

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.putExtra("finish", true);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

            finish();
        });
    }

    private void setBottomNavigation() {
        View bottomNavigation = findViewById(R.id.bottom_navigation);

        BottomNavigationView bottomNavigationView = bottomNavigation.findViewById(R.id.home_nav);

        bottomNavigationView.setSelectedItemId(R.id.home_bottom_more);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_bottom_report:
                    startActivity(new Intent(Other.this, Report.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.home_bottom_history:
                    startActivity(new Intent(Other.this, History.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.home_bottom_notification:
                    startActivity(new Intent(Other.this, Notify.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.home_bottom_more:
                    return true;

                case R.id.home_bottom_news:
                    startActivity(new Intent(Other.this, News.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
            }

            return false;
        });

        FloatingActionButton fab = bottomNavigation.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Chọn");

            String[] functions = {"Đổ xăng", "Sửa chữa"};
            builder.setItems(functions, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0: // Đổ xăng
                            startActivity(new Intent(Other.this, Fuel.class));
                            break;
                        case 1: // Sửa chữa
                            startActivity(new Intent(Other.this, Cost.class));
                            break;
                    }
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });

    }
}