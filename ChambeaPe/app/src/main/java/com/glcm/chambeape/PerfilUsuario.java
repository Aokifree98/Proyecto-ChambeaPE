package com.glcm.chambeape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;


public class PerfilUsuario extends AppCompatActivity {

    ImageView imgEdit, imgLlamar;
    ListView listaMisServicios, listaMisOfertas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        imgEdit = findViewById(R.id.imgPEditaPerfil);
        imgLlamar = findViewById(R.id.imgPLlamar);
        listaMisServicios = findViewById(R.id.listaMisServicios);
        listaMisOfertas = findViewById(R.id.listaMisOfertas);


    }

    public void Llamar_editar(View view) {
        Intent i = new Intent(this, EditarPerfil.class);
        startActivity(i);
    }

    public void Llamar_hacerLlamada(View view) {
        String phoneNumber = "tel:1234567890";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(phoneNumber));
        startActivity(intent);
    }
}