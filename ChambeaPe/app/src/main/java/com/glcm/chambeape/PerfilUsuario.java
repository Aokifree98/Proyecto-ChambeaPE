package com.glcm.chambeape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PerfilUsuario extends AppCompatActivity {

    ImageView imgEdit, imgLlamar;
   // ListView listaMisOfertas;
    RecyclerView listatrabajos, listaEmpleados;
    ArrayList<Anuncio> list;
    DatabaseReference databaseReference;
    Adapter adapter;

    Button btnAñadirAnuncio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        imgEdit = findViewById(R.id.imgPEditaPerfil);
        imgLlamar = findViewById(R.id.imgPLlamar);
        //listaMisServicios = findViewById(R.id.listaMisServicios);
       // listaMisOfertas = findViewById(R.id.listaMisOfertas);



        listatrabajos = findViewById(R.id.listatrabajos);
        databaseReference = FirebaseDatabase.getInstance().getReference("DetalleAnuncio");
        list = new ArrayList<>();
        listatrabajos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, list);
        listatrabajos.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Anuncio anuncio = dataSnapshot.getValue(Anuncio.class);
                    list.add(anuncio);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btnAñadirAnuncio=findViewById(R.id.btnNuevoAnuncio);
        btnAñadirAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


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

    public void llamarItemTrabajador(View view) {
        Intent i = new Intent(this, ItemBuscoTrabajador.class);
        startActivity(i);
    }
    public void llamarItemEmpleo(View view) {
        Intent i = new Intent(this, ItemBuscoTrabajador.class);
        startActivity(i);
    }

    public void llamarBusqueda(View view) {
        Intent i = new Intent(this, Buscar.class);
        startActivity(i);
    }
}