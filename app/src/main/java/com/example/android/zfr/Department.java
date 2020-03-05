package com.example.android.zfr;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.zfr.ItemActivity.BrakesItemActivity;
import com.example.android.zfr.ItemActivity.ChassisItemActivity;
import com.example.android.zfr.ItemActivity.ElectronicsItemActivity;
import com.example.android.zfr.ItemActivity.MiscellaneousItemActivity;

public class Department extends AppCompatActivity {
    ListView listView;
    String[] mItem = {"Vehicle Dynamics", "Chassis", "Power Train", "Brakes", "Electronics", "Miscellaneous"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        listView = findViewById(R.id.listView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Creating ADAPTER class
        MainAdapter adapter = new MainAdapter(this, mItem);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //A//
                    //sub department, vehicle dynamics//
                    Intent intent = new Intent(Department.this, VehicleDynamicsActivity.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    //B//
                    //Sub department,chassis//
                    Intent intent = new Intent(Department.this, ChassisItemActivity.class);
                    startActivity(intent);

                }
                if (position == 2) {
                    //C//
                    //Sub department,power train//
                    Intent intent = new Intent(Department.this, PowerTrainActivity.class);
                    startActivity(intent);

                }
                if (position == 3) {
                    //D//
                    //Sub department, brakes//
                    Intent intent = new Intent(Department.this, BrakesItemActivity.class);
                    startActivity(intent);

                }
                if (position == 4) {
                    //E//
                    //sub department,electronics//
                    Intent intent = new Intent(Department.this, ElectronicsItemActivity.class);
                    startActivity(intent);

                }
                if (position == 5) {
                    //F//
                    //sub department, miscellaneous//
                    Intent intent = new Intent(Department.this, MiscellaneousItemActivity.class);
                    startActivity(intent);

                }
            }
        });
    }

}

