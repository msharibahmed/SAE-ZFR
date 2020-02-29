package com.example.android.zfr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class VehicleDynamicsActivity extends AppCompatActivity {
    String[] mItemA = {"Steering", "Suspension"};
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicledynamics);
        listView = findViewById(R.id.listView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Creating ADAPTER class
        MainAdapterA adapterA = new MainAdapterA(this, mItemA);
        listView.setAdapter(adapterA);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(VehicleDynamicsActivity.this, Last2.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(VehicleDynamicsActivity.this, Last2.class);
                    startActivity(intent);
                }
            }
        });
    }
}
