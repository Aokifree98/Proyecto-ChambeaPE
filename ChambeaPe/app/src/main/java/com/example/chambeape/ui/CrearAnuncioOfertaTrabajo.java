package com.example.chambeape.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chambeape.R;
import com.example.chambeape.entidades.Anuncio;
import com.example.chambeape.entidades.FCMSend;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CrearAnuncioOfertaTrabajo extends AppCompatActivity {
    Spinner servicio, habilidad1, habilidad2, habilidad3;
    EditText tituloServ, direccion;
    Button crear;

    String servicioSeleccionado="";
    private DatabaseReference miDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_anuncio_oferta_trabajo);
        servicio = findViewById(R.id.spAOTServicio);
        tituloServ = findViewById(R.id.edtAOTTituloOferta);
        direccion = findViewById(R.id.edtAOTDireccion);
        habilidad1 = findViewById(R.id.spAOTHabilidaad1);
        habilidad2 = findViewById(R.id.spAOTHabilidaad2);
        habilidad3 = findViewById(R.id.spAOTHabilidaad3);
        crear = findViewById(R.id.btnAOTCrear);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        miDatabase=database.getReference();
        cargarServicios();
        cargarHabilidades();

    }
    public void crearAOT(View view) {
        crearAnuncioOfertar();

    }

    private void crearAnuncioOfertar() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd", Locale.CANADA);
        Date now = new Date();
        String fechaActual = formatter.format(now);

        String id = miDatabase.push().getKey();
        String Habilidad1 = habilidad1.getSelectedItem().toString();
        String Habilidad2 = habilidad2.getSelectedItem().toString();
        String Habilidad3 = habilidad3.getSelectedItem().toString();
        String descrAnuncio = tituloServ.getText().toString();
        String ubiAnuncio = direccion.getText().toString();
        String idServicio = servicio.getSelectedItem().toString();
        String dniuser = getIntent().getExtras().getString("dniuser");
        //String iduser = dniuser;


        Anuncio detalleAnuncio = new Anuncio(id,Habilidad1, Habilidad2, Habilidad3,
                dniuser, descrAnuncio, "Activo", ubiAnuncio, fechaActual,"",
                "",idServicio,"","",
                "","","","","");

        miDatabase.child("DetalleAnuncio").child(id).setValue(detalleAnuncio);
        Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
        //Buscar la forma para que se envie el mensaje no solo a un usuario en especifico sino a un grupo de usuarios, en especifico a los contactos seguidos.
        String toDeviceToken = "d4Y6yhVKS-aySy875bLJjS:APA91bGRJ7KeRZBJiBxIzqIK565S1obbVc1KVsku_8cme9fiA1FXOScBsmUplpyF7FcSREixFPTwjzAwOdAefgBKbBM4T2_a1z-VwB46dU6Cue7ONEdxauL4GVbqUH-hNlUsTjbOfGLD";
        String serverKey = "AAAAZK0vx0w:APA91bHgHH8lCbMnHNQhAfqwp1AJY74WtXFvaU5xo5hP2jUDIv-xXUvJgUCfmdDy34NN-UJGxY7lyBGto7uNMQzBkgvE8sI4VF0ULjTfHqZkPQLkmJgqQF_o0WacEPhc3Hhhu7geevRW";
        String title ="Nueva oferta de trabajo";
        String message = "Un contacto ha publicado una nueva oferta";
        if (!title.equals("") && !message.equals("")){
            FCMSend.SetServerKey(CrearAnuncioOfertaTrabajo.this, serverKey).pushNotification(
                    toDeviceToken,
                    title,
                    message
            );
        }

        Intent i = new Intent(this, SubirFotoAnuncio.class);
        String iduser = dniuser;
        String idanun = id;
        i.putExtra("dniuser", iduser);
        i.putExtra("idanun", idanun);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
    public void cargarServicios(){
        List servicios = new ArrayList<>();
        miDatabase.child("TipoServicio").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot ds: snapshot.getChildren()){
                        String id = ds.getKey();
                        //String nom = ds.child("nomUsuario").getValue(String.class);
                        //String apa = ds.child("apepatUsuario").getValue(String.class);
                        //String ama = ds.child("apematUsuario").getValue(String.class);
                        servicios.add(id);

                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter<>(CrearAnuncioOfertaTrabajo.this, android.R.layout.simple_dropdown_item_1line,servicios);
                    servicio.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void cargarHabilidades(){
        List habilidades = new ArrayList<>();
        miDatabase.child("Habilidades").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot ds: snapshot.getChildren()){
                        String id = ds.getKey();
                        //String nom = ds.child("nomUsuario").getValue(String.class);
                        //String apa = ds.child("apepatUsuario").getValue(String.class);
                        //String ama = ds.child("apematUsuario").getValue(String.class);
                        habilidades.add(id);

                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter<>(CrearAnuncioOfertaTrabajo.this, android.R.layout.simple_dropdown_item_1line,habilidades);
                    habilidad1.setAdapter(arrayAdapter);
                    habilidad2.setAdapter(arrayAdapter);
                    habilidad3.setAdapter(arrayAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}