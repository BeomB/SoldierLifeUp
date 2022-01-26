package com.soldier.soldierlifeup;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class ArmyServiceInformation extends AppCompatActivity implements OnMapReadyCallback {
    TextView tDivision, tFacility, tAddress, tTel;
    private GoogleMap mMap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.armyserviceinformation);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_library2);
        mapFragment.getMapAsync(this);
        tDivision = (TextView)findViewById(R.id.tDivision);
        tFacility = (TextView)findViewById(R.id.tFacility);
        tAddress = (TextView)findViewById(R.id.tAddress);
        tTel = (TextView)findViewById(R.id.tTel);

        tDivision.setText(ArmyService.rDivision);
        tFacility.setText(ArmyService.rFacility);
        tAddress.setText(ArmyService.rAddress);
        tTel.setText(ArmyService.rTel);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        LatLng MAP_point = new LatLng(Double.parseDouble(ArmyService.rLatitude), Double.parseDouble(ArmyService.rLongitude));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(MAP_point));
        //(성결관)
        MarkerOptions markerOptions_Home = new MarkerOptions();
        markerOptions_Home.position(new LatLng(Double.parseDouble(ArmyService.rLatitude), Double.parseDouble(ArmyService.rLongitude)));
        markerOptions_Home.snippet("공과 대학");
        mMap.addMarker(markerOptions_Home);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
    }
}