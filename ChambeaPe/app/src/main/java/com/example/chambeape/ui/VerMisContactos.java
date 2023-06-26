package com.example.chambeape.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chambeape.R;
import com.example.chambeape.entidades.ContactosAdapter;
import com.example.chambeape.entidades.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VerMisContactos extends AppCompatActivity {
    ListView lstContactos;
    Button btnVolverContactos;
    List<Usuario> listContacto = new ArrayList<Usuario>();
    ArrayAdapter<Usuario> arrayAdapterContactos;
    ContactosAdapter contactosAdapter;

    DatabaseReference contactoDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference usuarioDatabase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_mis_contactos);

        btnVolverContactos= findViewById(R.id.btnVolverContactos);
        lstContactos=findViewById(R.id.lstContactos);
        String dniuser = getIntent().getExtras().getString("dniuser");
        
        List contactos = new ArrayList<>();

        contactoDatabase.child("Contactos").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    for(DataSnapshot ds: snapshot.getChildren()){
                        String nombres;
                        String id = ds.getKey();
                        String id1 = ds.child("idSeguido").getValue(String.class);
                        String id2 = ds.child("idSeguidor").getValue(String.class);

                        if(dniuser.equals(id2))
                        {
                            contactos.add(id1);
                        }
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter<>(VerMisContactos.this, android.R.layout.simple_list_item_1,contactos);
                    lstContactos.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /*
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
         
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listContacto.clear();
                mDatabase.child("Usuarios").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot resultados) {
                        for(DataSnapshot itemsdata: resultados.getChildren()){

                            Usuario u = itemsdata.getValue(Usuario.class);
                            listContacto.add(u);
                            contactosAdapter = new ContactosAdapter(VerMisContactos.this, R.layout.itemcontacto,listContacto);
                            contactosAdapter.setActiveUser(dniuser);
                        }
                        arrayAdapterContactos = new ArrayAdapter<Usuario>
                                (VerMisContactos.this, android.R.layout.simple_list_item_1,listContacto);
                        lstContactos.setAdapter(contactosAdapter);
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

         */

        btnVolverContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VerMisContactos.this, MenuInicio.class);
                String iduser = dniuser;
                i.putExtra("dniuser", iduser);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
}