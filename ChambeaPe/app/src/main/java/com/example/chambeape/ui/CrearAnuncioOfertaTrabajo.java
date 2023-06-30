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
import com.google.firebase.database.Query;
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
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
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
        notificar(descrAnuncio);
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

    private void notificar(String titulo){
        String dniuser = getIntent().getExtras().getString("dniuser");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbContactos = database.getReference("Contactos"); // Reemplaza "referencia1" con la referencia a tu primera tabla
        DatabaseReference dbUsuarios = database.getReference("Usuarios");
        Query contactos = dbContactos.orderByChild("idSeguido").equalTo(dniuser);

        /*
        mDatabase.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dniuser1 ="11111111";
                if(snapshot.hasChild(dniuser1)){
                    String getnombre = snapshot.child(dniuser).child("nomUsuario").getValue(String.class);
                    String gettoken = snapshot.child(dniuser1).child("tokenUsuario").getValue(String.class);
                    //String toDeviceToken = "d4Y6yhVKS-aySy875bLJjS:APA91bGRJ7KeRZBJiBxIzqIK565S1obbVc1KVsku_8cme9fiA1FXOScBsmUplpyF7FcSREixFPTwjzAwOdAefgBKbBM4T2_a1z-VwB46dU6Cue7ONEdxauL4GVbqUH-hNlUsTjbOfGLD";
                    String serverKey = "AAAAZK0vx0w:APA91bHgHH8lCbMnHNQhAfqwp1AJY74WtXFvaU5xo5hP2jUDIv-xXUvJgUCfmdDy34NN-UJGxY7lyBGto7uNMQzBkgvE8sI4VF0ULjTfHqZkPQLkmJgqQF_o0WacEPhc3Hhhu7geevRW";
                    String title ="Nueva oferta de trabajo: "+titulo;
                    String message = "Tu contacto "+getnombre+" ha publicado una nueva oferta";
                    if (!title.equals("") && !message.equals("")){
                        FCMSend.SetServerKey(CrearAnuncioOfertaTrabajo.this, serverKey).pushNotification(
                                gettoken,
                                title,
                                message
                        );
                    }
                }
                else {
                    Toast.makeText(CrearAnuncioOfertaTrabajo.this,"Error al cargar información",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
         */
        contactos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> contactosnoti= new ArrayList<>();

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    //String dato = childSnapshot.getValue(String.class);
                    String dato = childSnapshot.child("idSeguidor").getValue(String.class);
                    Toast.makeText(CrearAnuncioOfertaTrabajo.this,dato,Toast.LENGTH_SHORT).show();
                    contactosnoti.add(dato);
                }

                // Corroborar los datos en la segunda tabla y realizar una acción por cada dato
                for (String dato : contactosnoti) {
                    Query contactosXusuarios = dbUsuarios.orderByChild("dniUsuario").equalTo(dato); // Reemplaza "campo" con el campo que deseas verificar en la segunda tabla
                    contactosXusuarios.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // Realizar la acción deseada para el dato que coincide en la segunda tabla
                                // Ejemplo: Imprimir el dato
                                mDatabase.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.hasChild(dniuser)){
                                            String getnombre = snapshot.child(dniuser).child("nomUsuario").getValue(String.class);
                                            String gettoken = dataSnapshot.child(dato).child("tokenUsuario").getValue(String.class);
                                            //String toDeviceToken = "d4Y6yhVKS-aySy875bLJjS:APA91bGRJ7KeRZBJiBxIzqIK565S1obbVc1KVsku_8cme9fiA1FXOScBsmUplpyF7FcSREixFPTwjzAwOdAefgBKbBM4T2_a1z-VwB46dU6Cue7ONEdxauL4GVbqUH-hNlUsTjbOfGLD";
                                            String serverKey = "AAAAZK0vx0w:APA91bHgHH8lCbMnHNQhAfqwp1AJY74WtXFvaU5xo5hP2jUDIv-xXUvJgUCfmdDy34NN-UJGxY7lyBGto7uNMQzBkgvE8sI4VF0ULjTfHqZkPQLkmJgqQF_o0WacEPhc3Hhhu7geevRW";
                                            String title ="¡Nueva oferta!";
                                            String message = getnombre+" publicó: "+titulo;
                                            if (!title.equals("") && !message.equals("")){
                                                FCMSend.SetServerKey(CrearAnuncioOfertaTrabajo.this, serverKey).pushNotification(
                                                        gettoken,
                                                        title,
                                                        message
                                                );
                                            }
                                            System.out.println("Dato encontrado en la segunda tabla: " + dato);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }

                                });

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Manejar el error en caso de que la consulta sea cancelada o falle
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar el error en caso de que la consulta sea cancelada o falle
            }
        });

    }
}