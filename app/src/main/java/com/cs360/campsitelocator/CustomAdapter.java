package com.cs360.campsitelocator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    // sets variables and array lists
    private Context context;
    Activity activity;
    private ArrayList site_id, site_name, site_location, site_rating, site_info, site_url;


    //sets custom adapter with arrays to be added into sqlite database
    CustomAdapter(Activity activity, Context context,
                  ArrayList site_id,
                  ArrayList site_name,
                  ArrayList site_location,
                  ArrayList site_rating,
                  ArrayList site_info,
                  ArrayList site_url){
        this.activity = activity;
        this.context = context;
        this.site_id = site_id;
        this.site_name = site_name;
        this.site_location = site_location;
        this.site_rating = site_rating;
        this.site_info = site_info;
        this.site_url = site_url;

    }

    //view holder takes in all data to be displayed on screen through database
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.site_name_txt.setText(String.valueOf(site_name.get(position)));
        holder.site_location_txt.setText(String.valueOf(site_location.get(position)));
        holder.site_rating_txt.setText(String.valueOf(site_rating.get(position)));
        holder.site_info_txt.setText(String.valueOf(site_info.get(position)));
        holder.site_url_txt.setText(String.valueOf(site_url.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(site_id.get(position)));
                intent.putExtra("site", String.valueOf(site_name.get(position)));
                intent.putExtra("location", String.valueOf(site_location.get(position)));
                intent.putExtra("rating", String.valueOf(site_rating.get(position)));
                intent.putExtra("info", String.valueOf(site_info.get(position)));
                intent.putExtra("url", String.valueOf(site_url.get(position)));
                activity.startActivityForResult(intent, 1);

            }
        });

    }

    //gets item count to validate input into recycler view
    @Override
    public int getItemCount() {

        return site_name.size();
    }

    //
    public class MyViewHolder extends RecyclerView.ViewHolder {

        //sets textview variables and layout variable
        TextView site_name_txt, site_location_txt, site_rating_txt, site_info_txt, site_url_txt;
        LinearLayout mainLayout;

        //sends information to be held in textviews
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            site_name_txt = itemView.findViewById(R.id.site_name_txt);
            site_location_txt = itemView.findViewById(R.id.site_location_txt);
            site_rating_txt = itemView.findViewById(R.id.site_rating_txt);
            site_info_txt = itemView.findViewById(R.id.site_info_txt);
            site_url_txt = itemView.findViewById(R.id.site_url_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
