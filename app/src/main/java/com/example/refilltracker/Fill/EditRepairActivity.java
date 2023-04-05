package com.example.refilltracker.Fill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.refilltracker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditRepairActivity extends AppCompatActivity {
    private EditText etDate;
    private EditText etTime;
    private EditText etType;
    private EditText etPrice;
    private Button btnConfirm;
    private Spinner spinType;

    InfoCost infoCost = new InfoCost();
    DatabaseReference costrepair;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost);
        setToolBar();

        etDate = findViewById(R.id.expense_date);
        etTime = findViewById(R.id.expense_time);
        etType = findViewById(R.id.expense_type);
        etPrice = findViewById(R.id.expense_price);
        spinType = findViewById(R.id.spinnerTypeCarRepair);
        btnConfirm = findViewById(R.id.btn_confirmCost);

        getEdit();
        costrepair = FirebaseDatabase.getInstance().getReference().child("Repair");


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmCost();
            }
        });
    }

    private void getEdit()
    {
        Intent intent = getIntent();
        infoCost = (InfoCost) intent.getSerializableExtra("info");
        etDate.setText(infoCost.getDate());
        etTime.setText(infoCost.getTime());
        etType.setText(infoCost.getPlace());
        etPrice.setText(infoCost.getPrice());
        spinType.getSelectedItem().toString();
    }

    private void confirmCost() {
        String date = etDate.getText().toString();
        String time = etTime.getText().toString();
        String type = etType.getText().toString();
        String price = etPrice.getText().toString();;
        String typeCar = spinType.getSelectedItem().toString();

        InfoCost infocc = new InfoCost(date, time, type, price, typeCar, infoCost.number);

        costrepair.child(infoCost.getNumber()).setValue(infocc);
        Toast.makeText(EditRepairActivity.this, "Data add completed", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.tool_bar_expense);
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