package com.example.android.zfr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.android.zfr.InputActivity.InputActivity;
import com.example.android.zfr.InputActivity.PCoolingInputActivity;
import com.example.android.zfr.InputActivity.PDriveTrainInputActivity;
import com.example.android.zfr.InputActivity.PIntakeInputActivity;
import com.example.android.zfr.InputActivity.VSuspensionInputActivity;
import com.example.android.zfr.ItemActivity.BrakesItemActivity;
import com.example.android.zfr.ItemActivity.ChassisItemActivity;
import com.example.android.zfr.ItemActivity.ElectronicsItemActivity;
import com.example.android.zfr.ItemActivity.MiscellaneousItemActivity;
import com.example.android.zfr.ItemActivity.PtCoolingItemActivity;
import com.example.android.zfr.ItemActivity.PtExhaustItemActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class NavActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private TextView nav_total_cost;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference nDb;
    FirebaseAuth.AuthStateListener mAuthListener;
    NavigationView navigationView;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_main);

        nav_total_cost = findViewById(R.id.nav_totalcost);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


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


        nDb = database.getReference().child("Navigation Activity").child("Cost");
        nDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String cost = dataSnapshot.getValue(String.class);
                nav_total_cost.setText(String.valueOf(cost));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



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


    @SuppressLint("IntentReset")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.menu_departments) {
            Intent intent = new Intent(this, Department.class);
            startActivity(intent);
        }

        if (id == R.id.menu_give_task) {

        }
        if (id == R.id.menu_tasks) {

        }
        if (id == R.id.menu_contact_developer) {

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:msharibahmed@gmail.com"));

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

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