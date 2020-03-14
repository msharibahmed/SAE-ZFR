package com.example.android.zfr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.android.zfr.ItemActivity.BrakesItemActivity;
import com.example.android.zfr.ItemActivity.ChassisItemActivity;
import com.example.android.zfr.ItemActivity.ElectronicsItemActivity;
import com.example.android.zfr.ItemActivity.MiscellaneousItemActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.Objects;

public class Department extends AppCompatActivity {
    ListView listView;
    String[] mItem = {"Vehicle Dynamics", "Chassis", "Power Train", "Brakes", "Electronics", "Miscellaneous"};
    int[] mImage = {R.drawable.vehicledynamics, R.drawable.chassis, R.drawable.powertrain, R.drawable.brakes,
            R.drawable.electronics, R.drawable.miscellaneous};
    String[] mCost = new String[6];
    int sumofdcost = 0;
    private ImageView d_back_btn;
    String sumofdcostvalue;
    private DatabaseReference vdDbcost, cDbcost, ptDbcost, bDbcost, eDbcost, mDbcost, dDb, nDb;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        d_back_btn = findViewById(R.id.department_back_btn);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        listView = findViewById(R.id.listView);
        d_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Department.this, NavActivity.class);
                finish();
                startActivity(intent);
            }
        });
        database = FirebaseDatabase.getInstance();
        nDb = database.getReference().child("Navigation Activity").child("Cost");
        dDb = database.getReference().child("Department");

        vdDbcost = database.getReference().child("Department").child("Vehicle Dynamics").child("Cost");
        cDbcost = database.getReference().child("Department").child("Chassis").child("Cost");
        ptDbcost = database.getReference().child("Department").child("Power Train").child("Cost");
        bDbcost = database.getReference().child("Department").child("Brakes").child("Cost");
        eDbcost = database.getReference().child("Department").child("Electronics").child("Cost");
        mDbcost = database.getReference().child("Department").child("Miscellaneous").child("Cost");
        vdDbcost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String cost = dataSnapshot.getValue(String.class);
                mCost[0] = String.valueOf(cost);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        cDbcost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String cost = dataSnapshot.getValue(String.class);
                mCost[1] = String.valueOf(cost);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        ptDbcost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String cost = dataSnapshot.getValue(String.class);
                mCost[2] = String.valueOf(cost);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        bDbcost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String cost = dataSnapshot.getValue(String.class);
                mCost[3] = String.valueOf(cost);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        eDbcost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String cost = dataSnapshot.getValue(String.class);
                mCost[4] = String.valueOf(cost);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        mDbcost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String cost = dataSnapshot.getValue(String.class);
                mCost[5] = String.valueOf(cost);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        dDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object totalcost = map.get("Cost");
                    int totalcostvalue = Integer.parseInt(String.valueOf(totalcost));
                    sumofdcost += totalcostvalue;
                    sumofdcostvalue = String.valueOf(sumofdcost);
                }
                nDb.setValue(sumofdcostvalue);


                //Creating ADAPTER class

                MainAdapter adapter = new MainAdapter(Department.this, mItem, mImage, mCost);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });






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

    private void adapterCall() {
    }

}

