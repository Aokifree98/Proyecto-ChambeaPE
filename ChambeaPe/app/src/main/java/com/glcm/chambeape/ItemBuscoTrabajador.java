package com.glcm.chambeape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ItemBuscoTrabajador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_busco_trabajador);
    }
    public void llamarVolver(View view) {
        Intent i = new Intent(this, PerfilUsuario.class);
        startActivity(i);
    }
}