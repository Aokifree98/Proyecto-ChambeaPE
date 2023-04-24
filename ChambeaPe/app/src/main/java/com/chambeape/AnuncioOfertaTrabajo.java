package com.chambeape;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class AnuncioOfertaTrabajo extends AppCompatActivity {

    Spinner categoria;
    EditText servicio, direccion;
    ImageView fotoServicio;
    Button crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncio_oferta_trabajo);
        categoria = findViewById(R.id.spAOTCategoria);
        servicio = findViewById(R.id.edtAOTServicio);
        direccion = findViewById(R.id.edtAOTDireccion);
        fotoServicio = findViewById(R.id.imgAOTFoto);
        crear = findViewById(R.id.btnAOTCrear);


    }

    public void crearAOT(View view) {
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.categoriaServicio, android.R.layout.simple_spinner_item);
        categoria.setAdapter(adapter);
    }
}