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
        Intent c = new Intent(this, MenuInicio.class);
        startActivity(c);
    }

    private void crearAnuncioOfertar() {
        String id = miDatabase.push().getKey();
        String Habilidad1 = habilidad1.getSelectedItem().toString();
        String Habilidad2 = habilidad2.getSelectedItem().toString();
        String Habilidad3 = habilidad3.getSelectedItem().toString();
        String descrAnuncio = tituloServ.getText().toString();
        String ubiAnuncio = direccion.getText().toString();
        String idServicio = servicio.getSelectedItem().toString();
        String dni = getIntent().getExtras().getString("dni");
        String idUsuario = dni;


        Anuncio detalleAnuncio = new Anuncio(id,Habilidad1, Habilidad2, Habilidad3,
                idUsuario, descrAnuncio, "", ubiAnuncio, "","",
                "",idServicio,"","","",
                "","","");

        miDatabase.child("DetalleAnuncio").child(id).setValue(detalleAnuncio);
        Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
    }
}