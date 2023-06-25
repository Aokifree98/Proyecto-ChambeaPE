package com.example.chambeape.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chambeape.R;
import com.example.chambeape.entidades.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConcluirOferta extends AppCompatActivity {
    Spinner spEncargadoCO,spCalificacionCO;
    EditText edtComentarioCO;
    Button btnTerminarCO;
    String usuarioSeleccionado="";


    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concluir_oferta);
        spEncargadoCO = findViewById(R.id.spEncargadoCO);
        spCalificacionCO = findViewById(R.id.spCalificacionCO);
        edtComentarioCO = findViewById(R.id.edtComentarioCO);
        btnTerminarCO = findViewById(R.id.btnTerminarCO);
        cargarUsuarios();
        String dniuser = getIntent().getExtras().getString("dniuser");
        String idpubanun = getIntent().getExtras().getString("idpubanun");
        String idanun = getIntent().getExtras().getString("idanun");
        btnTerminarCO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String com = edtComentarioCO.getText().toString();
                String punt = spCalificacionCO.getSelectedItem().toString();;
                if(dniuser.equals(usuarioSeleccionado)){
                    Toast.makeText(ConcluirOferta.this, "No puedes seleccionarte a ti mismo como responsable", Toast.LENGTH_SHORT).show();
                }
                else{
                    mDatabase.child("DetalleAnuncio").child(idanun).child("idResponsableAnuncio").setValue(usuarioSeleccionado);
                    mDatabase.child("DetalleAnuncio").child(idanun).child("estadoAnuncio").setValue("Concluido");
                    mDatabase.child("DetalleAnuncio").child(idanun).child("comTraAnuncio").setValue(com);
                    mDatabase.child("DetalleAnuncio").child(idanun).child("puntTrabAnuncio").setValue(punt);
                    //Falta obtener la cantidad de ofertas terminadas y sacar una formula para obtener el promedio de la calificacion como trabajador
                    /*
                    mDatabase.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(idpubanun)){

                                String getpuntra = snapshot.child(idpubanun).child("calUsuarioTra").getValue(String.class);
                                Float punttra = Float.parseFloat(getpuntra);
                            }
                            else {
                                Toast.makeText(ConcluirOferta.this,"Error al cargar información",Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }

                    });
                     */
                    mDatabase.child("Usuarios").child(usuarioSeleccionado).child("calUsuarioTra").setValue(punt);
                    String idpubanun1 = getIntent().getExtras().getString("idpubanun");
                    String idanun1 = getIntent().getExtras().getString("idanun");
                    String dniuser1 = getIntent().getExtras().getString("dniuser");
                    Intent i = new Intent(ConcluirOferta.this, MenuInicio.class);
                    i.putExtra("dniuser", dniuser1);
                    i.putExtra("idpubanun", idpubanun1);
                    i.putExtra("idanun", idanun1);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                }


            }
        });

    }

    public void cargarUsuarios(){
        List usuarios = new ArrayList<>();
        mDatabase.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot ds: snapshot.getChildren()){
                        String id = ds.getKey();
                        //String nom = ds.child("nomUsuario").getValue(String.class);
                        //String apa = ds.child("apepatUsuario").getValue(String.class);
                        //String ama = ds.child("apematUsuario").getValue(String.class);
                        usuarios.add(id);

                    }
                    ArrayAdapter<Usuario> arrayAdapter = new ArrayAdapter<>(ConcluirOferta.this, android.R.layout.simple_dropdown_item_1line,usuarios);
                    spEncargadoCO.setAdapter(arrayAdapter);
                    spEncargadoCO.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            usuarioSeleccionado = parent.getItemAtPosition(position).toString();
                            //String dnires = usuarios.get(position).getDniUsuario();
                            Toast.makeText(ConcluirOferta.this, usuarioSeleccionado, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}