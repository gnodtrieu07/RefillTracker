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

public class Fuel extends AppCompatActivity {
    private EditText etDate;
    private EditText etTime;
    private EditText etPlace;
    private EditText etPrice;
    private EditText etLit;
    private Button btnConfirm;
    private Spinner spinType;

    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth = -1;

    private int lastSelectedHour = -1;
    private int lastSelectedMinute;

    DatabaseReference fuelfill;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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

        fuelfill = FirebaseDatabase.getInstance().getReference().child("Fuel");


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmFuel();
            }
        });
    }

    private void confirmFuel(){
        String date = etDate.getText().toString();
        String time = etTime.getText().toString();
        String place = etPlace.getText().toString();
        String price = etPrice.getText().toString();
        String lit = etLit.getText().toString();
        String typeCar = spinType.getSelectedItem().toString();

        Random random = new Random();
        String number = String.valueOf(random.nextInt(9999999 - 0 + 1));
       // number.replace(".", "0");

        InfoFuel infoFuel = new InfoFuel(date,time,place,price,lit,typeCar,number);

        Toast.makeText(Fuel.this, "Swap" + number, Toast.LENGTH_SHORT).show();
        fuelfill.child(number+"").setValue(infoFuel);
        Toast.makeText(Fuel.this, "Data add completed", Toast.LENGTH_SHORT).show();

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.function_add:
                intent = getIntent();
                intent.putExtra("Date", etDate.getText().toString());
                intent.putExtra("Time", etTime.getText().toString());
                intent.putExtra("Place", etPlace.getText().toString());
                intent.putExtra("Price", etPrice.getText().toString());
                intent.putExtra("Lit", etLit.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
