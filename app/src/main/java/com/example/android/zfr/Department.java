package com.example.android.zfr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Department extends AppCompatActivity {
    ListView listView;
    String[] mItem = {"item1", "item2", "item3", "item4", "item5","item6"};


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
                Intent intent = new Intent(Department.this,Main2Activity.class);
                intent.putExtra("word",mItem[position]);
                startActivity(intent);
            }
        });
    }
}

