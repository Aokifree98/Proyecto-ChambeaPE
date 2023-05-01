package com.chambeape;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class PerfilUsuario extends AppCompatActivity {

    List<ListaAnuncioServicio> elemnts;
    ImageView fotoPerfilUser;
    Button editarPerfil, actualizarRedesS, contactoDirecto;
    RecyclerView listaServicios;
    ImageButton notificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        fotoPerfilUser = findViewById(R.id.imgPerfilUser);
        listaServicios = findViewById(R.id.rclListaServicios);
        editarPerfil = findViewById(R.id.btnEditPerfi);
        actualizarRedesS = findViewById(R.id.btnRedesSoci);
        contactoDirecto = findViewById(R.id.btnContactarUser);
        notificacion = findViewById(R.id.imgNotifUser);

    }
}