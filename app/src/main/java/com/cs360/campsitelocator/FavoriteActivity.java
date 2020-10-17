package com.cs360.campsitelocator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    //sets variables for on screen texts and buttons and favorites database
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    TextView empty_favorites;

    MyDatabaseHelper myDB;
    ArrayList<String> site_id, site_name, site_location, site_rating, site_info, site_url;
    CustomAdapter customAdapter;

    //creates list for favorites screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerView = findViewById(R.id.recyclerView2);
        empty_favorites = findViewById(R.id.empty_favorites);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FavoriteActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        //creates the array list for favorite database
        myDB = new MyDatabaseHelper(FavoriteActivity.this);
        site_id = new ArrayList<>();
        site_name = new ArrayList<>();
        site_location = new ArrayList<>();
        site_rating = new ArrayList<>();
        site_info = new ArrayList<>();
        site_url = new ArrayList<>();

        storeDataInArrays();

        //calls custom adapter to show favorites list
        customAdapter = new CustomAdapter(FavoriteActivity.this, this, site_id, site_name, site_location, site_rating, site_info, site_url);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(FavoriteActivity.this));
    }

    //creates list
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    //reads database information via cursor position
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            empty_favorites.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                site_id.add(cursor.getString(0));
                site_name.add(cursor.getString(1));
                site_location.add(cursor.getString(2));
                site_rating.add(cursor.getString(3));
                site_info.add(cursor.getString(4));
                site_url.add(cursor.getString(5));
            }
            empty_favorites.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    //sets delete all from favorites database and removes shared preferences to deselect favorite checkbox in campsite information screen
    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all favorites?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences preferences = getSharedPreferences("favorite_box", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                MyDatabaseHelper myDB = new MyDatabaseHelper(FavoriteActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(FavoriteActivity.this, FavoriteActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
