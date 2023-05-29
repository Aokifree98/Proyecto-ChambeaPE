package com.example.chambeape.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chambeape.R;
import com.example.chambeape.entidades.Anuncio;
import com.example.chambeape.entidades.AnuncioAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ListaOfertasTrabajo extends AppCompatActivity {
    ListView lstListarAnunciosOferta;
    Button btnVolver;
    List<Anuncio> listAnuncio = new ArrayList<Anuncio>();
    ArrayAdapter<Anuncio> arrayAdapterAnuncios;
    AnuncioAdapter anuncioAdapter;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ofertas_trabajo);
        btnVolver= findViewById(R.id.btnVolver);
        lstListarAnunciosOferta=findViewById(R.id.lstListarAnunciosOferta);
        String dniuser = getIntent().getExtras().getString("dniuser");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Query productosxestado = reference.orderByChild("tipo").equalTo(tipo);
                Query anunciossxestado = mDatabase.child("DetalleAnuncio");
                listAnuncio.clear();
                anunciossxestado.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot resultados) {
                        for(DataSnapshot itemsdata: resultados.getChildren()){
                            Anuncio a = itemsdata.getValue(Anuncio.class);
                            if(a.getEstadoAnuncio().equals("Activo")){
                                listAnuncio.add(a);
                                anuncioAdapter = new AnuncioAdapter(ListaOfertasTrabajo.this, R.layout.itemanuncio,listAnuncio);
                            }
                        }
                        arrayAdapterAnuncios = new ArrayAdapter<Anuncio>
                                (ListaOfertasTrabajo.this, android.R.layout.simple_list_item_1,listAnuncio);
                        lstListarAnunciosOferta.setAdapter(anuncioAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaOfertasTrabajo.this, MenuInicio.class);
                String iduser = dniuser;
                i.putExtra("dniuser", iduser);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
}