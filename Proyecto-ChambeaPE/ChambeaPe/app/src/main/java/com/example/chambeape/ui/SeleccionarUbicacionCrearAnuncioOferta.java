package com.example.chambeape.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.chambeape.R;
import com.example.chambeape.databinding.ActivitySeleccionarUbicacionCrearAnuncioOfertaBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class SeleccionarUbicacionCrearAnuncioOferta extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {
    Button btnSeleccionarUbi, btnContinuarUbi;
    LatLng unLugar;
    private GoogleMap mMap;
    Boolean marca=false;
    double mLat, mLong;
    DatabaseReference miDatabaseRef;
    private ActivitySeleccionarUbicacionCrearAnuncioOfertaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySeleccionarUbicacionCrearAnuncioOfertaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        btnSeleccionarUbi=findViewById(R.id.btnSeleccionarUbi);
        btnContinuarUbi=findViewById(R.id.btnContinuarUbi);
        btnSeleccionarUbi.setOnClickListener(this);
        btnContinuarUbi.setOnClickListener(this);
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

        unLugar = new LatLng(-12.068068626097737, -75.20979986119212);
        CameraPosition cameraPosition = CameraPosition.builder().target(unLugar).zoom(18).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull @NotNull LatLng latLng) {
                marca=true;
                mMap.clear();
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador))
                        .title("Punto").snippet("Yo estube aqui")
                        .position(latLng).anchor(0.5F, 0.5f));
                mLat = latLng.latitude;
                mLong = latLng.longitude;
                Toast.makeText(SeleccionarUbicacionCrearAnuncioOferta.this, mLat + "<>" + mLong, Toast.LENGTH_SHORT).show();
            }
        });
        /*
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
         */
    }

    @Override
    public void onClick(View v) {
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        miDatabaseRef =database.getReference();
        String idanun = getIntent().getExtras().getString("idanun");
        String dniuser= getIntent().getExtras().getString("dniuser");
        String lat = String.valueOf(mLat);
        String lon = String.valueOf(mLong);
        switch (v.getId()){
            case R.id.btnSeleccionarUbi:{
                if(marca){
                    miDatabaseRef.child("DetalleAnuncio").child(idanun).child("latitudAnuncio").setValue(lat);
                    miDatabaseRef.child("DetalleAnuncio").child(idanun).child("longitudAnuncio").setValue(lon);
                    Toast.makeText(SeleccionarUbicacionCrearAnuncioOferta.this,"Subido Correctamente",Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(SeleccionarUbicacionCrearAnuncioOferta.this,"Debe marcar la ubicacion", Toast.LENGTH_SHORT).show();

                break;
            }
            case R.id.btnContinuarUbi:{
                Intent intent=new Intent(SeleccionarUbicacionCrearAnuncioOferta.this,MenuInicio.class);
                intent.putExtra("dniuser", dniuser);
                intent.putExtra("idanun", idanun);
                startActivity(intent);
                break;
            }

        }

    }
}