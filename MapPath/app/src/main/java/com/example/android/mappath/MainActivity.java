package com.example.android.mappath;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Button btn_dublin,btn_tokyo,btn_seattle;
    GoogleMap m_map;
    boolean mapReady=false;
    static final CameraPosition NEWYORK= CameraPosition.builder()
            .target(new LatLng(40.784,-739857))
            .zoom(17)
            .bearing(0)
            .tilt(45)
            .build();
    static final CameraPosition SEATTLE= CameraPosition.builder()
            .target(new LatLng(47.6204,-122.3491)).zoom(17).bearing(0).tilt(45).build();
    static  final CameraPosition DUBLIN= CameraPosition.builder()
            .target(new LatLng(53.3478,-6.2597)).zoom(17).tilt(45).bearing(90).build();
    static  final CameraPosition TOKYO= CameraPosition.builder()
            .target(new LatLng(35.6895,139.6917)).zoom(17).tilt(45).bearing(90).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_seattle=(Button) findViewById(R.id.btn_seattle);
        btn_dublin=(Button)findViewById(R.id.btn_dublin);
        btn_tokyo=(Button)findViewById(R.id.btn_tokyo);

        btn_seattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady)
                    flyTo(SEATTLE);
            }
        });

        btn_dublin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady)
                    flyTo(DUBLIN);
            }
        });

        btn_tokyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady)
                    flyTo(TOKYO);
            }
        });

        MapFragment mapFragment=(MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady=true;
        m_map=googleMap;
        m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        flyTo(NEWYORK);
    }


    private void flyTo(CameraPosition target) {
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target),1000,null);
    }
}
