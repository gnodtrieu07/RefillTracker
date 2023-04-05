package com.example.refilltracker.Primary.HistoryFill;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.refilltracker.Adapter;
import com.example.refilltracker.Fill.Cost;
import com.example.refilltracker.Fill.EditActivity;
import com.example.refilltracker.Fill.Fuel;
import com.example.refilltracker.Fill.InfoFuel;
import com.example.refilltracker.Fill.InfoCost;
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

public class History extends AppCompatActivity {
    private String username;
    private EditText eKind;
    private LinearLayout linearWelcome;
    private RecyclerView recyclerView;
    int sumMoney = 0;
    DatabaseReference fuelfill;
    ArrayList<InfoFuel> list = new ArrayList<>();
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView = findViewById(R.id.rv_history);
        adapter = new Adapter(History.this, list);
        setBottomNavigation();
        bindUI();
        getHistoryFuel();
        setRecyclerView();

        fuelfill = FirebaseDatabase.getInstance().getReference().child("Fuel");
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
        recyclerView.setAdapter(adapter);
    }

    private void getHistoryFuel() {
        fuelfill = FirebaseDatabase.getInstance().getReference().child("Fuel");
        fuelfill.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (list != null) {
                    list.clear();
                    int i = 0;
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        InfoFuel iff = dataSnapshot.getValue(InfoFuel.class);
                        list.add(iff);
                        sumMoney = Integer.parseInt(iff.getPrice()) + sumMoney;
                    }
                    adapter.notifyDataSetChanged();
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

        bottomNavigationView.setSelectedItemId(R.id.home_bottom_history);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_bottom_report:
                    Intent intent = new Intent(History.this, Report.class);
                    intent.putExtra("username", username);
                    startActivity(intent);

                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.home_bottom_history:
                    return true;

                case R.id.home_bottom_notification:
                    startActivity(new Intent(History.this, Notify.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.home_bottom_more:
                    startActivity(new Intent(History.this, Other.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.home_bottom_news:
                    startActivity(new Intent(History.this, News.class));
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
                            startActivityForResult(new Intent(History.this, Fuel.class), 0);
                            break;
                        case 1: // Sửa chữa
                            startActivityForResult(new Intent(History.this, Cost.class), 1);
                            break;
                    }
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
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
            case 147:
                InfoFuel infoFuel = list.get(item.getGroupId());
                Intent i = new Intent(History.this, EditActivity.class);
                i.putExtra("info", (Serializable) infoFuel);
                startActivityForResult(i,123);
                break;

            case 852:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(History.this);
                alertDialogBuilder.setMessage("Bạn có muốn xóa Note này!");
                alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // String key = fuelfill(item.getGroupId()+ "").getKey();
                        fuelfill.child(list.get(item.getGroupId()).getNumber()).removeValue();
                        Toast.makeText(History.this, "Xóa thành công", Toast.LENGTH_LONG).show();
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




