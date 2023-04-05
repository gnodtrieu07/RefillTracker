package com.example.refilltracker.Primary;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.refilltracker.Adapter;
import com.example.refilltracker.Fill.Cost;
import com.example.refilltracker.Fill.Fuel;
import com.example.refilltracker.Fill.InfoCost;
import com.example.refilltracker.Fill.InfoFuel;
import com.example.refilltracker.Primary.HistoryFill.History;
import com.example.refilltracker.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Report extends AppCompatActivity {
    DatabaseReference fuelfill;
    DatabaseReference costrepair;

    ArrayList<InfoFuel> list = new ArrayList<>();
    ArrayList<InfoCost> list2 = new ArrayList<>();
    int sumMoney = 0;
    int sumMoneyRepair = 0;
    TextView allMoney, allMoneyRepair;
    TextView totalMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        setBottomNavigation();
        allMoney = findViewById(R.id.tv_report_gas_total);
        allMoneyRepair = findViewById(R.id.tv_report_repair_total);
        totalMoney = findViewById(R.id.tv_report_total);
        getHistory();
        getHistoryRepair();
    }

    private void getHistory()
    {
        fuelfill = FirebaseDatabase.getInstance().getReference().child("Fuel");
        fuelfill.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(list!=null)
                {
                    list.clear();
                    int i=0;
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        InfoFuel iff= dataSnapshot.getValue(InfoFuel.class);
                        sumMoney = Integer.parseInt(iff.getPrice()) + sumMoney;
                    }
                    allMoney.setText(sumMoney + "");
                    totalMoney.setText(sumMoney+ sumMoneyRepair + "");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getHistoryRepair() {
        costrepair = FirebaseDatabase.getInstance().getReference().child("Repair");
        costrepair.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (list2 != null) {
                    list2.clear();
                    int i = 0;
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        InfoCost ifc = dataSnapshot.getValue(InfoCost.class);
                        sumMoneyRepair = Integer.parseInt(ifc.getPrice()) + sumMoneyRepair;
                    }
                    allMoneyRepair.setText(sumMoneyRepair + "");
                    totalMoney.setText(sumMoneyRepair + sumMoney + "");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void setBottomNavigation() {
        View bottomNavigation = findViewById(R.id.bottom_navigation);

        BottomNavigationView bottomNavigationView = bottomNavigation.findViewById(R.id.home_nav);

        bottomNavigationView.setSelectedItemId(R.id.home_bottom_report);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_bottom_report:
                    return true;

                case R.id.home_bottom_history:
                    startActivity(new Intent(Report.this, History.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.home_bottom_notification:
                    startActivity(new Intent(Report.this, Notify.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.home_bottom_more:
                    startActivity(new Intent(Report.this, Other.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.home_bottom_news:
                    startActivity(new Intent(Report.this, News.class));
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
                            startActivity(new Intent(Report.this, Fuel.class));
                            break;
                        case 1: // Sửa chữa
                            startActivity(new Intent(Report.this, Cost.class));
                            break;
                    }
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }
}