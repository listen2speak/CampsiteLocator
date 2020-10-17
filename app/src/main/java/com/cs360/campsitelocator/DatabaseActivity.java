package com.cs360.campsitelocator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    //FloatingActionButton add_button; //Dev only
    DatabaseCustomAdapter databaseCustomAdapter;

    //DatabaseAccess devDB;
    ArrayList<String> dev_id, dev_name, dev_location, dev_state, dev_info, dev_url, dev_rating, dev_lat, dev_lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        recyclerView = findViewById(R.id.recyclerView3);

        //add button for dev work only

        //add_button = findViewById(R.id.add_button2);
        /*add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatabaseActivity.this, LibraryActivity.class);
                startActivity(intent);

            }
        });*/

        //pulls all data from SetLibrary.db
        dev_id = new ArrayList<>();
        dev_name = new ArrayList<>();
        dev_location = new ArrayList<>();
        dev_state = new ArrayList<>();
        dev_info = new ArrayList<>();
        dev_url = new ArrayList<>();
        dev_rating = new ArrayList<>();
        dev_lat = new ArrayList<>();
        dev_lon = new ArrayList<>();

        devDataInArrays();

        databaseCustomAdapter= new DatabaseCustomAdapter(DatabaseActivity.this, dev_id, dev_name, dev_location, dev_state, dev_info, dev_url, dev_rating, dev_lat, dev_lon);
        recyclerView.setAdapter(databaseCustomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DatabaseActivity.this));

    }
    //pulls data from database via state
    void devDataInArrays() {

        Intent intent = getIntent();
        String state = intent.getStringExtra("state");


        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        Cursor cursor = databaseAccess.c;
        //databaseAccess.readAllData(); //dev only


        //pulls data from database via selected state from dropdown on in searchActivity
        if(state.equals("MA")){
            cursor = databaseAccess.readAllDataMA();
        }else if(state.equals("CT")){
            cursor = databaseAccess.readAllDataCT();
        }else if(state.equals("RI")){
            cursor = databaseAccess.readAllDataRI();
        }else if(state.equals("ME")){
            cursor = databaseAccess.readAllDataME();
        }else if(state.equals("VT")){
            cursor = databaseAccess.readAllDataVT();
        }else if(state.equals("NH")){
            cursor = databaseAccess.readAllDataNH();
        }

        //if nothings selected, pop up no data, else pull all info to next screen
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                dev_id.add(cursor.getString(0));
                dev_name.add(cursor.getString(1));
                dev_location.add(cursor.getString(2));
                dev_state.add(cursor.getString(3));
                dev_info.add(cursor.getString(4));
                dev_url.add(cursor.getString(5));
                dev_rating.add(cursor.getString(6));
                dev_lat.add(cursor.getString(7));
                dev_lon.add(cursor.getString(8));

            }
        }
    }
}
