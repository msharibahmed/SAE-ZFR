package com.example.android.zfr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
                    Intent intent = new Intent(Department.this, VehicleDynamicsActivity.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(Department.this, ChassisActivity.class);
                    startActivity(intent);

                }
                if (position == 2) {
                    Intent intent = new Intent(Department.this, PowerTrainActivity.class);
                    startActivity(intent);

                }
                if (position == 3) {
                    Intent intent = new Intent(Department.this, BrakesActivity.class);
                    startActivity(intent);

                }
                if (position == 4) {
                    Intent intent = new Intent(Department.this, ElectronicsActivity.class);
                    startActivity(intent);

                }
                if (position == 5) {
                    Intent intent = new Intent(Department.this, MiscellaneousActitvity.class);
                    startActivity(intent);

                }
            }
        });
    }

}

