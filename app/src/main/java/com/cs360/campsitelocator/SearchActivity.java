package com.cs360.campsitelocator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    //set variables
    Spinner state_spinner;
    Button map_search_button, search_button;
    String state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //locate variables
        state_spinner = findViewById(R.id.state_spinner);
        map_search_button = findViewById(R.id.map_search_button);
        search_button = findViewById(R.id.search_button2);

        //Create ArrayAdapter using string array within strings.xml
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.states));

        //Specify layout
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //apply the adapter to spinner
        state_spinner.setAdapter(myAdapter);


        //identify state chosen from spinner and direct user to that map if selected
        map_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, MapActivity.class);
                state = state_spinner.getSelectedItem().toString();
                intent.putExtra("state", state);
                startActivity(intent);
            }
        });

        //identify state chosen from spinner and direct user to that list if selected
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, DatabaseActivity.class);
                state = state_spinner.getSelectedItem().toString();
                intent.putExtra("state", state);
                startActivity(intent);
            }
        });

    }
}
