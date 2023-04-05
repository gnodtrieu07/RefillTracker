package com.example.refilltracker.Fill;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.refilltracker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Random;

public class EditActivity extends AppCompatActivity {
    private EditText etDate;
    private EditText etTime;
    private EditText etPlace;
    private EditText etPrice;
    private EditText etLit;
    private Button btnConfirm;
    private Spinner spinType;

    InfoFuel infoFuel = new InfoFuel();
    DatabaseReference fuelfill;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel);
        setToolBar();

        etDate = findViewById(R.id.fuel_date);
        etTime = findViewById(R.id.fuel_time);
        etPlace = findViewById(R.id.fuel_place);
        etPrice = findViewById(R.id.fuel_price);
        etLit = findViewById(R.id.fuel_lit);
        spinType = findViewById(R.id.spinnerTypeCar);
        btnConfirm = findViewById(R.id.btn_confirm);

        getEdit();
        fuelfill = FirebaseDatabase.getInstance().getReference().child("Fuel");


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmFuel();
            }
        });
    }

    private void getEdit()
    {
        Intent intent = getIntent();
        infoFuel = (InfoFuel) intent.getSerializableExtra("info");
        etDate.setText(infoFuel.getDate());
        etTime.setText(infoFuel.getTime());
        etPlace.setText(infoFuel.getPlace());
        etPrice.setText(infoFuel.getPrice());
        etLit.setText(infoFuel.getLit());
        spinType.getSelectedItem().toString();
    }

    private void confirmFuel() {
        String date = etDate.getText().toString();
        String time = etTime.getText().toString();
        String place = etPlace.getText().toString();
        String price = etPrice.getText().toString();
        String lit = etLit.getText().toString();
        String typeCar = spinType.getSelectedItem().toString();

        InfoFuel infoss = new InfoFuel(date, time, place, price, lit, typeCar, infoFuel.number);

        fuelfill.child(infoFuel.getNumber()).setValue(infoss);
        Toast.makeText(EditActivity.this, "Data add completed", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.tool_bar_fuel);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_items, menu);
        return true;
    }
}