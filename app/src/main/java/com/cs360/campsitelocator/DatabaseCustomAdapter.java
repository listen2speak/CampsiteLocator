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

public class DatabaseCustomAdapter extends RecyclerView.Adapter<DatabaseCustomAdapter.DevViewHolder> {

    //sets arraylist for internal database structure
    private Context context;

    private ArrayList dev_id, dev_name, dev_location, dev_state, dev_rating, dev_info, dev_url, dev_lat, dev_lon;

    DatabaseCustomAdapter(Context context,
                          ArrayList dev_id,
                          ArrayList dev_name,
                          ArrayList dev_location,
                          ArrayList dev_state,
                          ArrayList dev_info,
                          ArrayList dev_url,
                          ArrayList dev_rating,
                          ArrayList dev_lat,
                          ArrayList dev_lon){
        this.context = context;
        this.dev_id = dev_id;
        this.dev_name = dev_name;
        this.dev_location = dev_location;
        this.dev_state = dev_state;
        this.dev_rating = dev_rating;
        this.dev_info = dev_info;
        this.dev_url = dev_url;
        this.dev_lat = dev_lat;
        this.dev_lon = dev_lon;

    }

    //sets holder for internal database
    @NonNull
    @Override
    public DevViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.database_row, parent, false);
        return new DevViewHolder(view);
    }

    //sets information to be put into database
    @Override
    public void onBindViewHolder(@NonNull DatabaseCustomAdapter.DevViewHolder holder, final int position) {
        holder.site_id_dev.setText(String.valueOf(dev_id.get(position)));
        holder.site_name_dev.setText(String.valueOf(dev_name.get(position)));
        holder.site_location_dev.setText(String.valueOf(dev_location.get(position)));
        holder.site_info_dev.setText(String.valueOf(dev_info.get(position)));
        holder.url_info_dev.setText(String.valueOf(dev_url.get(position)));
        holder.site_rating_dev.setText(String.valueOf(dev_rating.get(position)));
        holder.site_info_lat.setText(String.valueOf(dev_lat.get(position)));
        holder.site_info_lon.setText((String.valueOf(dev_lon.get(position))));
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CampsiteActivity.class);
                intent.putExtra("id", String.valueOf(dev_id.get(position)));
                intent.putExtra("site", String.valueOf(dev_name.get(position)));
                intent.putExtra("location", String.valueOf(dev_location.get(position)));
                intent.putExtra("info", String.valueOf(dev_info.get(position)));
                intent.putExtra("url", String.valueOf(dev_url.get(position)));
                intent.putExtra("rating", String.valueOf(dev_rating.get(position)));
                intent.putExtra("lat", String.valueOf(dev_lat.get(position)));
                intent.putExtra("lon", String.valueOf(dev_lon.get(position)));
                context.startActivity(intent);


            }
        });

    }

    //sets count to validate information
    @Override
    public int getItemCount() {

        return dev_id.size();
    }

    public class DevViewHolder extends RecyclerView.ViewHolder {

        //textview variables and layout variable to display information in screen
        TextView site_id_dev, site_name_dev, site_location_dev, site_info_dev, url_info_dev, site_rating_dev, site_info_lat, site_info_lon;
        LinearLayout mainlayout;
        public DevViewHolder(@NonNull View itemView) {
            super(itemView);
            site_id_dev = itemView.findViewById(R.id.site_id_dev);
            site_name_dev = itemView.findViewById(R.id.site_name_dev);
            site_location_dev = itemView.findViewById(R.id.site_location_dev);
            site_info_dev = itemView.findViewById(R.id.site_info_dev);
            url_info_dev = itemView.findViewById(R.id.url_info_dev);
            site_rating_dev = itemView.findViewById(R.id.site_rating_dev);
            site_info_lat = itemView.findViewById(R.id.site_info_lat);
            site_info_lon = itemView.findViewById(R.id.site_info_lon);
            mainlayout = itemView.findViewById(R.id.resultLayout);
        }
    }
}
