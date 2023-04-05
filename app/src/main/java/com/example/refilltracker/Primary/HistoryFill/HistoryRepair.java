package com.example.refilltracker.Primary.HistoryFill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.refilltracker.Adapter;
import com.example.refilltracker.AdapterRepair;
import com.example.refilltracker.Fill.Cost;
import com.example.refilltracker.Fill.EditActivity;
import com.example.refilltracker.Fill.EditRepairActivity;
import com.example.refilltracker.Fill.Fuel;
import com.example.refilltracker.Fill.InfoCost;
import com.example.refilltracker.Fill.InfoFuel;
import com.example.refilltracker.Primary.News;
import com.example.refilltracker.Primary.Notify;
import com.example.refilltracker.Primary.Other;
import com.example.refilltracker.Primary.Report;
import com.example.refilltracker.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class HistoryRepair extends AppCompatActivity {

    private String username;
    private EditText eKind;
    private LinearLayout linearWelcome;
    private RecyclerView recyclerView;
    int sumMoney = 0;
    DatabaseReference costrepair;
    ArrayList<InfoCost> list2 = new ArrayList<>();
    AdapterRepair adapterRepair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_repair);
        recyclerView = findViewById(R.id.rv_history_repair);
        adapterRepair = new AdapterRepair(HistoryRepair.this, list2);
        bindUI();
        getHistoryRepair();
        setRecyclerView();

        costrepair = FirebaseDatabase.getInstance().getReference().child("Repair");
        eKind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getKindOfVehicles();
            }
        });
    }

    private void bindUI() {
        linearWelcome = findViewById(R.id.linear_welcome);
        eKind = findViewById(R.id.ed_kind);
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapterRepair);
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
                        list2.add(ifc);
                        sumMoney = Integer.parseInt(ifc.getPrice()) + sumMoney;
                    }
                    adapterRepair.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void getKindOfVehicles() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // set title
        builder.setTitle("Select kind of vehicles");

        // generate an array of places
        final String[] places = new String[]{
                "Mercedes",
                "BMW",
                "RangeRover",
                "Camry"
        };
        // set single choice
        builder.setSingleChoiceItems(places, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // get the selected item
                String selectedItem = Arrays.asList(places).get(i);

                // set selected item to edit text
                eKind.setText(selectedItem);
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eKind.setText("");
            }
        });

        // Create the alert dialog
        AlertDialog dialog = builder.create();

        // Finally, display the alert dialog
        dialog.show();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case 369:
                InfoCost infoCost = list2.get(item.getGroupId());
                Intent i = new Intent(HistoryRepair.this, EditRepairActivity.class);
                i.putExtra("info", (Serializable) infoCost);
                startActivityForResult(i,123);
                break;

            case 951:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HistoryRepair.this);
                alertDialogBuilder.setMessage("Bạn có muốn xóa Note này!");
                alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // String key = fuelfill(item.getGroupId()+ "").getKey();
                        costrepair.child(list2.get(item.getGroupId()).getNumber()).removeValue();
                        Toast.makeText(HistoryRepair.this, "Xóa thành công", Toast.LENGTH_LONG).show();
                    }
                });
                alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //không làm gì
                    }
                });
                alertDialogBuilder.show();
                break;
        }
        return true;
    }
}