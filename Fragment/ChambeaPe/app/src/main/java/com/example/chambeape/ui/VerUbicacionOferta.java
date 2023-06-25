package com.example.chambeape.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.chambeape.R;
import com.example.chambeape.databinding.ActivityVerUbicacionOfertaBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class VerUbicacionOferta extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {
    Button btnVolverVUO, btnContactarVUO;
    String lati,longi,idanun1,idpubanun1,dniuser1;
    double mLat, mLong;
    private GoogleMap mMap;
    private ActivityVerUbicacionOfertaBinding binding;

    LatLng destino;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVerUbicacionOfertaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        lati = getIntent().getStringExtra("lat");
        longi = getIntent().getStringExtra("lon");
        idanun1 = getIntent().getExtras().getString("idanun");
        idpubanun1 = getIntent().getExtras().getString("idpubanun");
        dniuser1 = getIntent().getExtras().getString("dniuser");
        //Toast.makeText(VerUbicacionOferta.this,lati+"-"+longi+"-"+ idanun1+"-"+idpubanun1+"-"+dniuser1,Toast.LENGTH_SHORT).show();
        mLat=Double.parseDouble(lati);
        mLong=Double.parseDouble(longi);
        destino = new LatLng(mLat,mLong);
        btnVolverVUO=findViewById(R.id.btnVolverVUO);
        btnContactarVUO=findViewById(R.id.btnContactarVUO);
        btnVolverVUO.setOnClickListener(this);
        btnVolverVUO.setOnClickListener(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        }

        CameraPosition cameraPosition = CameraPosition.builder().target(destino).zoom(18).build();
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador))
                .title("Titulo").snippet("tipo"+" / "+"descripcion")
                .position(destino).anchor(0.5F, 0.5f));
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
         */
    }

    @Override
    public void onClick(View v) {

    }
}