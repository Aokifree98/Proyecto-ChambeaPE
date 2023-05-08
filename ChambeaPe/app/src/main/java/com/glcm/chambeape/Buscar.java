package com.glcm.chambeape;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.SearchView;

public class Buscar extends AppCompatActivity {
    SearchView buscador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        buscador = findViewById(R.id.buscadorB);
    }

    public void searchView() {
        buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                textSearch(s);
                return false;
            }


            @Override
            public boolean onQueryTextChange(String s) {
                textSearch(s);
                return false;
            }

        });
    }

        public void textSearch(String s) {
        //Hacer un query
        }
    }
