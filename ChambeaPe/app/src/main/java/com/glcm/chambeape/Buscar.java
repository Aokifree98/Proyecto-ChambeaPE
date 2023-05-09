package com.glcm.chambeape;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

public class Buscar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

    }

    public void llamarItemTopEmpleo(View view) {
        Intent i = new Intent(this, ItemMostrarTopTrabajos.class);
        startActivity(i);

    }
    public void llamarItemTopTrabajos(View view) {
        Intent i = new Intent(this, ItemMostrarTopTrabajador.class);
        startActivity(i);

    }
}