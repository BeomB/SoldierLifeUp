package com.soldier.soldierlifeup;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class
MapsLibraryActivity extends AppCompatActivity implements OnMapReadyCallback {
    Button yongsan,dongseoul;
    private GoogleMap mMap;
    static LatLng MAP_point;
    static double we=53.4630589,ke=-2.2935288;
    static int buttonnumber=1;
    TextView tTmoName, tTelNum, twdayTime, twendTime, tLocationexplane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.armytmoinformation);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_library);
        mapFragment.getMapAsync(this);
       ;
        TextView tTmoName, tTelNum, twdayTime, twendTime, tLocationexplane;

            tTmoName = (TextView)findViewById(R.id.tTmoName);
            tTelNum = (TextView)findViewById(R.id.tTelNum);
            twdayTime = (TextView)findViewById(R.id.twdayTime);
            twendTime = (TextView)findViewById(R.id.twendTime);
            tLocationexplane = (TextView)findViewById(R.id.tLocationexplane);

            tTmoName.setText(ArmyTmo.rTmoName);
            tTelNum.setText(ArmyTmo.rTelNum);
            twdayTime.setText(ArmyTmo.rwdayTime);
            twendTime.setText(ArmyTmo.rwendTime);
            tLocationexplane.setText(ArmyTmo.rLocationexplane);
        }




    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        LatLng MAP_point = new LatLng(Double.parseDouble(ArmyTmo.rLatitude), Double.parseDouble(ArmyTmo.rLongitude));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(MAP_point));
        //(성결관)
        MarkerOptions markerOptions_Home = new MarkerOptions();
        markerOptions_Home.position(new LatLng(Double.parseDouble(ArmyTmo.rLatitude), Double.parseDouble(ArmyTmo.rLongitude)));
        markerOptions_Home.snippet("공과 대학");
        mMap.addMarker(markerOptions_Home);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
        //-------------------------------------------------Library-------------------------------------------------------------//
    }

}