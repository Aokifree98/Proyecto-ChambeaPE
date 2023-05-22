package com.glcm.chambeape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listatrabajos;
    ArrayList<Anuncio> list;
    DatabaseReference databaseReference;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listatrabajos = findViewById(R.id.listatrabajos1);
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






}}