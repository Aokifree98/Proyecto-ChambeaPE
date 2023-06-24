package com.example.chambeape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chambeape.ui.EnviarCodigoSMS;
import com.example.chambeape.ui.GestionarContactos;
import com.example.chambeape.ui.Notificaciones;
import com.example.chambeape.ui.ValidacionIdentidad;
import com.example.chambeape.ui.VerAnuncioTrabajador;
import com.example.chambeape.ui.VerOfertasCercanas;
import com.example.chambeape.ui.VerTrabajadoresCercanos;

public class MainActivity extends AppCompatActivity {
    Button btnGesCon,btnNot,btnValDat,btnValIde,btnVerAnuTra,btnVerOfeCer,btnVerTraCer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGesCon=findViewById(R.id.btnGesCon);
        btnNot=findViewById(R.id.btnNot);
        btnValDat=findViewById(R.id.btnValDat);
        btnValIde=findViewById(R.id.btnValIde);
        btnVerAnuTra=findViewById(R.id.btnVerAnuTra);
        btnVerOfeCer=findViewById(R.id.btnVerOfeCer);
        btnVerTraCer=findViewById(R.id.btnVerTraCer);

        btnGesCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GestionarContactos.class);

                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        btnNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Notificaciones.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        btnValDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, EnviarCodigoSMS.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        btnValIde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ValidacionIdentidad.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        btnVerAnuTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, VerAnuncioTrabajador.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        btnVerOfeCer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, VerOfertasCercanas.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        btnVerTraCer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, VerTrabajadoresCercanos.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
}