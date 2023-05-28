package com.example.chambeape.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.chambeape.R;
import com.example.chambeape.entidades.Anuncio;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CrearAnuncioOfertaTrabajo extends AppCompatActivity {
    Spinner servicio, habilidad1, habilidad2, habilidad3;
    EditText tituloServ, direccion;
    ImageButton fotoServicio, videoServicio;
    Button crear;

    private DatabaseReference miDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_anuncio_oferta_trabajo);
        servicio = findViewById(R.id.spAOTServicio);
        tituloServ = findViewById(R.id.edtAOTTituloOferta);
        direccion = findViewById(R.id.edtAOTDireccion);
        fotoServicio = findViewById(R.id.imgbAOTFoto);
        videoServicio = findViewById(R.id.imgbAOTVideo);
        habilidad1 = findViewById(R.id.spAOTHabilidaad1);
        habilidad2 = findViewById(R.id.spAOTHabilidaad2);
        habilidad3 = findViewById(R.id.spAOTHabilidaad3);
        crear = findViewById(R.id.btnAOTCrear);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        miDatabase=database.getReference();


    }
    public void crearAOT(View view) {
        crearAnuncioOfertar();

    }

    private void crearAnuncioOfertar() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd", Locale.CANADA);
        Date now = new Date();
        String fechaActual = formatter.format(now);

        String id = miDatabase.push().getKey();
        String Habilidad1 = habilidad1.getSelectedItem().toString();
        String Habilidad2 = habilidad2.getSelectedItem().toString();
        String Habilidad3 = habilidad3.getSelectedItem().toString();
        String descrAnuncio = tituloServ.getText().toString();
        String ubiAnuncio = direccion.getText().toString();
        String idServicio = servicio.getSelectedItem().toString();
        String dniuser = getIntent().getExtras().getString("dniuser");
        //String iduser = dniuser;


        Anuncio detalleAnuncio = new Anuncio(id,Habilidad1, Habilidad2, Habilidad3,
                dniuser, descrAnuncio, "Activo", ubiAnuncio, fechaActual,"",
                "",idServicio,"","",
                "","","");

        miDatabase.child("DetalleAnuncio").child(id).setValue(detalleAnuncio);
        Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, SubirFotoAnuncio.class);
        String iduser = dniuser;
        String idanun = id;
        i.putExtra("dniuser", iduser);
        i.putExtra("idanun", idanun);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

}