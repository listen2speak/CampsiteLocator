package com.cs360.campsitelocator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.UiAutomation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    //set variables
    GoogleMap mapAPI;
    private MapView mMapView;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //create mapView bundle and sync it
        Bundle mapViewBundle = null;
        if (savedInstanceState != null){
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView = findViewById(R.id.map);
        mMapView.onCreate(mapViewBundle);

        mMapView.getMapAsync(this);

    }

    // create method to place saved state bundle
    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null){
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    //method to allow user interaction after pause
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    //method to start activity from onCreate(Bundle)
    @Override
    protected void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    //Stops refreshing UI
    @Override
    protected void onStop() {
        super.onStop();
        mMapView.onStop();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        //set map and zoom variable
        mapAPI = googleMap;
        float zoom = 10.0f;
        LatLng loc = null;
        LatLng loc2 = null;
        LatLng loc3 = null;
        LatLng loc4 = null;
        LatLng loc5 = null;
        LatLng focus = null;

        String title = null;
        String title2 = null;
        String title3 = null;
        String title4 = null;
        String title5 = null;

        // bring spinner string from SearchActivity to this
        Intent intent = getIntent();
        String state = intent.getStringExtra("state");

        //If Else statements based on spinner string
        if(state.equals("MA")){
            loc = new LatLng(41.843354, -70.693035);
            loc2 = new LatLng(41.792738, -70.603604);
            loc3 = new LatLng(41.750978, -70.590247);
            loc4 = new LatLng(41.733666, -70.321879);
            loc5 = new LatLng(41.922227, -70.739499);
            title = "Myles Standish State Forest";
            title2 = "Sandy Pond";
            title3 = "Bourne Scenic Park";
            title4 = "Sandy Neck";
            title5 = "Pinewood Lodge";
            focus = new LatLng(41.824267, -70.584328);
        }else if (state.equals("CT")){
            loc = new LatLng(41.635137, -72.296341);
            loc2 = new LatLng(41.610994, -72.514154);
            loc3 = new LatLng(41.499836, -72.295373);
            loc4 = new LatLng(41.526721, -72.218555);
            loc5 = new LatLng(41.642023, -72.092926);
            title = "Waters Edge";
            title2 = "Nelson's Family";
            title3 = "Witch Meadow Lake";
            title4 = "Acorn Acres";
            title5 = "Salt Rock State Forest";
            focus = new LatLng(41.635137, -72.296341);
        }else if (state.equals("ME")) {
            loc = new LatLng(44.043889, -70.374951);
            loc2 = new LatLng(44.060899, -70.328828);
            loc3 = new LatLng(44.096053, -70.457928);
            loc4 = new LatLng(44.050107, -70.441391);
            loc5 = new LatLng(44.042876, -70.345279);
            title = "Poland Spring";
            title2 = "Empire Grove";
            title3 = "Mirror Pond";
            title4 = "Hemlocks";
            title5 = "Range Pond";
            focus = new LatLng(44.043889, -70.374951);
        }else if (state.equals("RI")) {
            loc = new LatLng(41.528318, -71.737093);
            loc2 = new LatLng(41.427760, -71.574336);
            loc3 = new LatLng(41.556676, -71.624209);
            loc4 = new LatLng(41.597974, -71.762181);
            loc5 = new LatLng(41.706592, -71.635216);
            title = "Whispering Pines";
            title2 = "Worden Pond";
            title3 = "Wawaloam Campground";
            title4 = "Oak Embers";
            title5 = "Waters Edge";
            focus = new LatLng(41.556676, -71.624209);
        }else if (state.equals("VT")) {
            loc = new LatLng(43.930177, -72.702408);
            loc2 = new LatLng(43.927405, -72.882659);
            loc3 = new LatLng(43.895865, -72.818591);
            loc4 = new LatLng(43.825605, -72.909824);
            loc5 = new LatLng(44.101045, -72.548622);
            title = "Abel Mountain";
            title2 = "Little Emma's Sweet Retreat";
            title3 = "Moosalamoo";
            title4 = "Chittenden Brook";
            title5 = "Limehurst Lake";
            focus = new LatLng(43.930177, -72.702408);
        }else if (state.equals("NH")) {
            loc = new LatLng(43.954313, -71.213878);
            loc2 = new LatLng(43.534652, -71.215210);
            loc3 = new LatLng(43.471035, -71.231471);
            loc4 = new LatLng(43.546096, -71.361974);
            loc5 = new LatLng(43.687460, -71.377418);
            title = "White Ledge";
            title2 = "Greentops";
            title3 = "Viewland";
            title4 = "Gunstock Mountain";
            title5 = "Bear's Pine Woods";
            focus = new LatLng(43.687460, -71.377418);
        }


        //sets marker title
        mapAPI.addMarker(new MarkerOptions().position(loc).title(title));
        mapAPI.addMarker(new MarkerOptions().position(loc2).title(title2));
        mapAPI.addMarker(new MarkerOptions().position(loc3).title(title3));
        mapAPI.addMarker(new MarkerOptions().position(loc4).title(title4));
        mapAPI.addMarker(new MarkerOptions().position(loc5).title(title5));

        //sets camera location and zoom
        mapAPI.moveCamera(CameraUpdateFactory.newLatLngZoom(focus, zoom));
        mapAPI.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }

    // saves state on pause
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    // destroys threads associated to activity
    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    // release unnecessary resources if low on memory
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
