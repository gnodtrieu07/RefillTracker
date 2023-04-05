package com.example.refilltracker.Fill;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.refilltracker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class Cost extends AppCompatActivity {
    private EditText etDate;
    private EditText etTime;
    private EditText etType;
    private EditText etPrice;
    private Button btnConfirmRepair;
    private Spinner spinTypeRepair;

    // variables for Date picker
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth = -1;

    // variables for Time picker
    private int lastSelectedHour = -1;
    private int lastSelectedMinute;

    DatabaseReference costrepair;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost);

        etDate = findViewById(R.id.expense_date);
        etTime = findViewById(R.id.expense_time);
        etType = findViewById(R.id.expense_type);
        etPrice = findViewById(R.id.expense_price);
        spinTypeRepair = findViewById(R.id.spinnerTypeCarRepair);
        btnConfirmRepair = findViewById(R.id.btn_confirmCost);

        costrepair = FirebaseDatabase.getInstance().getReference().child("Repair");

        setToolBar();
        btnConfirmRepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindUI();
            }
        });
    }

    private void bindUI() {
        String date = etDate.getText().toString();
        String time = etTime.getText().toString();
        String type = etType.getText().toString();
        String price = etPrice.getText().toString();
        String typeCar = spinTypeRepair.getSelectedItem().toString();

        Random random = new Random();
        String number = String.valueOf(random.nextInt(9999999 - 0 + 1));
        // number.replace(".", "0");

        InfoCost infoCost = new InfoCost(date,time,type,price,typeCar,number);

        Toast.makeText(Cost.this, "Swap" + number, Toast.LENGTH_SHORT).show();
        costrepair.child(number+"").setValue(infoCost);
        Toast.makeText(Cost.this, "Data add completed", Toast.LENGTH_SHORT).show();

        finish();

        /*etDateR.setOnClickListener(v -> {
            if (lastSelectedDayOfMonth == -1) {
                final Calendar c = Calendar.getInstance();
                this.lastSelectedYear = c.get(Calendar.YEAR);
                this.lastSelectedMonth = c.get(Calendar.MONTH);
                this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);
            }

            DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
                etDateR.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                lastSelectedYear = year;
                lastSelectedMonth = month;
                lastSelectedDayOfMonth = dayOfMonth;
            };

            DatePickerDialog datePickerDialog =  new DatePickerDialog(this,
                    dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);

            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 1000);

            datePickerDialog.show();
        });

        etTimeR.setOnClickListener(v -> {
            if(this.lastSelectedHour == -1)  {
                final Calendar c = Calendar.getInstance();
                this.lastSelectedHour = c.get(Calendar.HOUR_OF_DAY);
                this.lastSelectedMinute = c.get(Calendar.MINUTE);
            }

            TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    // format to 04:04 if hour of minute < 10
                    etTimeR.setText(( (hourOfDay < 10) ? "0" + hourOfDay : hourOfDay ) + ":" + ( (minute < 10) ? "0" + minute : minute ) );
                    lastSelectedHour = hourOfDay;
                    lastSelectedMinute = minute;
                }
            };

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    timeSetListener, lastSelectedHour, lastSelectedMinute, true);

            timePickerDialog.show();
        });*/
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.tool_bar_expense);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    //========== MENU OPTION CREATE ==========//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_items, menu);
        return true;
    }

    //========== MENU OPTION EVENT ==========//
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.function_add:
                intent = getIntent();
                intent.putExtra("Date", etDate.getText().toString());
                intent.putExtra("Time", etTime.getText().toString());
                intent.putExtra("Type", etType.getText().toString());
                intent.putExtra("Price", etPrice.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
