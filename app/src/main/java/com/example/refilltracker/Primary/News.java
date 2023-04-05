package com.example.refilltracker.Primary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.refilltracker.Fill.Cost;
import com.example.refilltracker.Fill.Fuel;
import com.example.refilltracker.Primary.HistoryFill.History;
import com.example.refilltracker.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class News extends AppCompatActivity{
    ImageView iv;
    TextView openWebpage;
    Integer REQUEST_CAMERA=1, SELECT_FILE=0;

    FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        openWebpage = findViewById(R.id.tvCommonProblems);
        // getting information of a specific user
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        openWebpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://www.24h.com.vn/o-to-c747.html"));
                startActivity(browserIntent);
            }
        });

        setBottomNavigation();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK){
            if(requestCode==SELECT_FILE){
                Uri selectedImageUri = data.getData();
                iv.setImageURI(selectedImageUri);
            }
        }
    }

    private void setBottomNavigation() {
        View bottomNavigation = findViewById(R.id.bottom_navigation);

        BottomNavigationView bottomNavigationView = bottomNavigation.findViewById(R.id.home_nav);

        bottomNavigationView.setSelectedItemId(R.id.home_bottom_news);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_bottom_report:
                    startActivity(new Intent(News.this, Report.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.home_bottom_history:
                    startActivity(new Intent(News.this, History.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.home_bottom_notification:
                    startActivity(new Intent(News.this, Notify.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.home_bottom_news:
                    return true;

                case R.id.home_bottom_more:
                    startActivity(new Intent(News.this, Other.class));
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
                            startActivity(new Intent(News.this, Fuel.class));
                            break;
                        case 1: // Sửa chữa
                            startActivity(new Intent(News.this, Cost.class));
                            break;
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

    }
}