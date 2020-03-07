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

import java.util.Objects;

public class VehicleDynamicsActivity extends AppCompatActivity {
    String[] mItemA = {"Steering", "Suspension"};
    int[] mImageA = {R.drawable.steering, R.drawable.suspension};
    String[] mCostA = {Last2.sumofsteeringcostvalue, VdSuspensionItemActivity.sumofsuspensioncostvalue};
    String[] mCountA = {Last2.sumofsteeringcountvalue, VdSuspensionItemActivity.sumofsuspensioncountvalue};
    ListView listView;
    TextView vsteeringcost, vsteeringcounts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicledynamics);
        listView = findViewById(R.id.listView);
        vsteeringcost = findViewById(R.id.cost);
        vsteeringcounts = findViewById(R.id.counts);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //Creating ADAPTER class
        MainAdapterA adapterA = new MainAdapterA(this, mItemA, mImageA, mCostA, mCountA);
        listView.setAdapter(adapterA);
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
