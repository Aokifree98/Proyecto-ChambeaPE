package com.example.chambeape.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chambeape.R;
import com.example.chambeape.entidades.Anuncio;
import com.example.chambeape.entidades.AnuncioAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class VerHistorialUsuario extends AppCompatActivity {
    ListView lstOfertasHU;

    List<Anuncio> listAnuncio = new ArrayList<Anuncio>();
    ArrayAdapter<Anuncio> arrayAdapterAnuncios;
    AnuncioAdapter anuncioAdapter;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_historial_usuario);
        lstOfertasHU=findViewById(R.id.lstOfertasHU);
        String dniuser = getIntent().getExtras().getString("dniuser");
        String idpubanun= getIntent().getExtras().getString("idpubanun");
        Toast.makeText(VerHistorialUsuario.this,dniuser,Toast.LENGTH_SHORT).show();
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
                            if(a.getEstadoAnuncio().equals("Concluido")&&(a.getIdResponsableAnuncio().equals(idpubanun))){
                                listAnuncio.add(a);
                                anuncioAdapter = new AnuncioAdapter(VerHistorialUsuario.this, R.layout.itemanuncio,listAnuncio);
                                anuncioAdapter.setActiveUser(dniuser);
                            }
                        }
                        arrayAdapterAnuncios = new ArrayAdapter<Anuncio>
                                (VerHistorialUsuario.this, android.R.layout.simple_list_item_1,listAnuncio);
                        lstOfertasHU.setAdapter(anuncioAdapter);

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
    }
}