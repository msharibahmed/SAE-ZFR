package com.example.android.zfr;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.android.zfr.ItemActivity.BrakesItemActivity;
import com.example.android.zfr.ItemActivity.ChassisItemActivity;
import com.example.android.zfr.ItemActivity.ElectronicsItemActivity;
import com.example.android.zfr.ItemActivity.MiscellaneousItemActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class NavActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private TextView nav_total_cost;
    private MenuItem givetask, task;
    float totalcosttonav;
    String totalcostvaluetonav;
    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_main);
        nav_total_cost = findViewById(R.id.nav_totalcost);
        givetask = findViewById(R.id.menu_give_task);
        task = findViewById(R.id.menu_tasks);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if (mFirebaseUser == null) {
                    finish();
                    Intent intent = new Intent(NavActivity.this, LogInActivity.class);
                    startActivity(intent);
                }
            }
        };


        totalcosttonav = BrakesItemActivity.sumofbrakescost + ChassisItemActivity.sumofchassiscost + ElectronicsItemActivity.sumofelectronicscost +
                MiscellaneousItemActivity.sumofmiscellaneouscost + VehicleDynamicsActivity.sumofvehicledynamicscost + PowerTrainActivity.sumofpowertraincost;
        totalcostvaluetonav = String.valueOf(totalcosttonav);
        nav_total_cost.setText(totalcostvaluetonav);


        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.menu_departments) {
            Intent intent = new Intent(this, Department.class);
            startActivity(intent);
        }
        if (id == R.id.menu_management) {

        }
        if (id == R.id.menu_give_task) {


        }
        if (id == R.id.menu_tasks) {

        }
        if (id == R.id.menu_log_out) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, LogInActivity.class);
            finish();
            startActivity(intent);
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();

        }
        if (id == R.id.menu_share_app) {

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }
        if (id == R.id.menu_about_us) {
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.amulogo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}