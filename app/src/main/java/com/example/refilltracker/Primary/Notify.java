package com.example.refilltracker.Primary;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.refilltracker.Fill.Cost;
import com.example.refilltracker.Fill.Fuel;
import com.example.refilltracker.Primary.HistoryFill.History;
import com.example.refilltracker.R;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

public class Notify extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        setBottomNavigation();
        createNotificationChanel();
        Button button = findViewById(R.id.btn_Notify);

        button.setOnClickListener(v -> {
            Toast.makeText(this, "Reminder Set!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Notify.this, NotifyReminder.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(Notify.this,0,intent,0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            long timeAtButtonClick = System.currentTimeMillis();

            long tenSecondInMillsis = 1000*10;

            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    timeAtButtonClick + tenSecondInMillsis,
                    pendingIntent);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChanel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1){
            CharSequence name = "RefillTrackerReminder";
            String description = "Reminder for RefillTracker";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("NotifyReceive", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

    private int getNotificationId() {
        return (int) new Date().getTime();
    }

    private void setBottomNavigation() {
        View bottomNavigation = findViewById(R.id.bottom_navigation);

        BottomNavigationView bottomNavigationView = bottomNavigation.findViewById(R.id.home_nav);

        bottomNavigationView.setSelectedItemId(R.id.home_bottom_notification);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_bottom_report:
                    startActivity(new Intent(Notify.this, Report.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.home_bottom_history:
                    startActivity(new Intent(Notify.this, History.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.home_bottom_notification:
                    return true;

                case R.id.home_bottom_more:
                    startActivity(new Intent(Notify.this, Other.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.home_bottom_news:
                    startActivity(new Intent(Notify.this, News.class));
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
                            startActivity(new Intent(Notify.this, Fuel.class));
                            break;
                        case 1: // Sửa chữa
                            startActivity(new Intent(Notify.this, Cost.class));
                            break;
                    }
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }
}