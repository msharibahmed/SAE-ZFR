package com.example.android.zfr;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.zfr.ItemActivity.Last2;
import com.example.android.zfr.ItemActivity.VdSuspensionItemActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;
import java.util.Objects;

public class VehicleDynamicsActivity extends AppCompatActivity {
    String[] mItemA = {"Steering", "Suspension"};
    int[] mImageA = {R.drawable.steering, R.drawable.suspension};
    String[] mCountA = new String[2];
    String[] mCostA = new String[2];
    int sumofvdcost = 0;
    String sumofvdcostvalue;

    private DatabaseReference suspensionDbcost, steeringDbcost, suspensionDbcount, steeringDbcount, vdDb, dvdDb;
    private FirebaseDatabase database;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicledynamics);
        listView = findViewById(R.id.listView);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        database = FirebaseDatabase.getInstance();

        dvdDb = database.getReference().child("Department").child("Vehicle Dynamics").child("Cost");
        vdDb = database.getReference().child("Sub Department").child("Vehicle Dynamics");
        steeringDbcost = database.getReference().child("Sub Department").child("Vehicle Dynamics").child("Steering").child("Cost");
        suspensionDbcost = database.getReference().child("Sub Department").child("Vehicle Dynamics").child("Suspension").child("Cost");
        steeringDbcount = database.getReference().child("Sub Department").child("Vehicle Dynamics").child("Steering").child("Count");
        suspensionDbcount = database.getReference().child("Sub Department").child("Vehicle Dynamics").child("Suspension").child("Count");

        steeringDbcost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String cost = dataSnapshot.getValue(String.class);
                mCostA[0] = String.valueOf(cost);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        steeringDbcount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String count = dataSnapshot.getValue(String.class);
                mCountA[0] = String.valueOf(count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        suspensionDbcost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String cost = dataSnapshot.getValue(String.class);
                mCostA[1] = String.valueOf(cost);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        suspensionDbcount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String count = dataSnapshot.getValue(String.class);
                mCountA[1] = String.valueOf(count);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        vdDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object totalcost = map.get("Cost");
                    int totalcostvalue = Integer.parseInt(String.valueOf(totalcost));
                    sumofvdcost += totalcostvalue;
                    sumofvdcostvalue = String.valueOf(sumofvdcost);
                }
                dvdDb.setValue(sumofvdcostvalue);
                //Creating ADAPTER class
                MainAdapterA adapterA = new MainAdapterA(VehicleDynamicsActivity.this, mItemA, mImageA, mCostA, mCountA);
                listView.setAdapter(adapterA);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(VehicleDynamicsActivity.this, Last2.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(VehicleDynamicsActivity.this, VdSuspensionItemActivity.class);
                    startActivity(intent);
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_button, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.home_button) {
            Intent intent = new Intent(this, NavActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
