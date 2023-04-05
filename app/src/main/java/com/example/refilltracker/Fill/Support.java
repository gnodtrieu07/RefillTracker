package com.example.refilltracker.Fill;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.refilltracker.R;


public class Support extends AppCompatActivity {
    private static final int REQUEST_ACTION_CALL = 111;

    private LinearLayout linearLayout113;
    private LinearLayout linearLayout114;
    private LinearLayout linearLayout115;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        setToolBar();

        linearLayout113 = findViewById(R.id.linear_emergency_113);
        linearLayout114 = findViewById(R.id.linear_emergency_114);
        linearLayout115 = findViewById(R.id.linear_emergency_115);

        linearLayout113.setOnClickListener(v -> callEmergency("113"));
        linearLayout114.setOnClickListener(v -> callEmergency("114"));
        linearLayout115.setOnClickListener(v -> callEmergency("115"));
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.tool_bar_emergency);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void callEmergency(String number) {
        if (hasPermission()) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);
        } else {
            requestPermission();
        }
    }

    private boolean hasPermission() {
        int code = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        return code == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(Support.this,
                new String[]{
                        Manifest.permission.CALL_PHONE
                }, REQUEST_ACTION_CALL);
    }
}