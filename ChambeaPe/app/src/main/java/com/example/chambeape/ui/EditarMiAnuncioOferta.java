package com.example.chambeape.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.chambeape.R;
import com.example.chambeape.entidades.Anuncio;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditarMiAnuncioOferta extends AppCompatActivity {
    Spinner spServicio, spHabilidad1, spHabilidad2, spHabilidad3;
    EditText edtTitulo, edtDireccion;
    Button btnActualizar;

    private DatabaseReference miDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_mi_anuncio_oferta);

        spServicio = findViewById(R.id.spServicioEAO);
        edtTitulo = findViewById(R.id.edtTituloEAO);
        edtDireccion = findViewById(R.id.edtDireccionEAO);
        spHabilidad1 = findViewById(R.id.spHabilidaad1EAO);
        spHabilidad2 = findViewById(R.id.spHabilidaad2EAO);
        spHabilidad3 = findViewById(R.id.spHabilidaad3EAO);
        btnActualizar = findViewById(R.id.btnActualizarEAO);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("DetalleAnuncio").addListenerForSingleValueEvent(new ValueEventListener() {
            String idanun = "12345";
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(idanun)){
                    /*String getnombre = snapshot.child(idanun).child("nomUsuario").getValue(String.class);
                    String getapepat = snapshot.child(idanun).child("apepatUsuario").getValue(String.class);
                    String getapemat = snapshot.child(idanun).child("apematUsuario").getValue(String.class);
                    String url = snapshot.child(idanun).child("fotUsuario").getValue(String.class);
                    //String nombresperfil = getnombre+" "+getapepat+" "+getapemat;
                    txtMINombreUsuario.setText(nombresperfil);*/


                }
                else {
                    Toast.makeText(EditarMiAnuncioOferta.this,"Error al cargar información",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

}