package com.example.chambeape.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chambeape.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditarMiAnuncioOferta extends AppCompatActivity {
    Spinner spServicio, spHabilidad1, spHabilidad2, spHabilidad3;
    EditText edtTitulo, edtDireccion;
    Button btnActualizar;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

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
        String dniuser = getIntent().getExtras().getString("dniuser");
        String idanun = getIntent().getExtras().getString("idanun");
        Toast.makeText(this, idanun, Toast.LENGTH_SHORT).show();

        mDatabase.child("DetalleAnuncio").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(idanun)) {
                    String gettitulo = snapshot.child(idanun).child("tituloAnuncio").getValue(String.class);
                    String getservicio = snapshot.child(idanun).child("tipoAnuncio").getValue(String.class);
                    String getdireccion = snapshot.child(idanun).child("direccionAnuncio").getValue(String.class);
                    String gethabi1 = snapshot.child(idanun).child("habilidad1Anuncio").getValue(String.class);
                    String gethabi2 = snapshot.child(idanun).child("habilidad2Anuncio").getValue(String.class);
                    String gethabi3 = snapshot.child(idanun).child("habilidad3Anuncio").getValue(String.class);
                    String getestado = snapshot.child(idanun).child("estadoAnuncio").getValue(String.class);
                    //String nombresperfil = getnombre+" "+getapepat+" "+getapemat;
                    edtTitulo.setText(gettitulo);
                    edtDireccion.setText(getdireccion);

                    for (int i = 0; i < spServicio.getAdapter().getCount(); i++) {
                        if (spServicio.getAdapter().getItem(i).toString().contains(getservicio)) {
                            spServicio.setSelection(i);
                        }
                    }
                    for (int i = 0; i < spHabilidad1.getAdapter().getCount(); i++) {
                        if (spHabilidad1.getAdapter().getItem(i).toString().contains(gethabi1)) {
                            spHabilidad1.setSelection(i);
                        }
                    }

                    for (int i = 0; i < spHabilidad2.getAdapter().getCount(); i++) {
                        if (spHabilidad2.getAdapter().getItem(i).toString().contains(gethabi2)) {
                            spHabilidad2.setSelection(i);
                        }
                    }
                    for (int i = 0; i < spHabilidad3.getAdapter().getCount(); i++) {
                        if (spHabilidad3.getAdapter().getItem(i).toString().contains(gethabi3)) {
                            spHabilidad3.setSelection(i);
                        }
                    }
                    for (int i = 0; i < spHabilidad3.getAdapter().getCount(); i++) {
                        if (spHabilidad3.getAdapter().getItem(i).toString().contains(gethabi3)) {
                            spHabilidad3.setSelection(i);
                        }
                    }



                } else {
                    Toast.makeText(EditarMiAnuncioOferta.this, "Error al cargar información", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar(idanun);
            }
        });
    }

    private void actualizar(String idanun) {
        String tit = edtTitulo.getText().toString();
        String dir = edtDireccion.getText().toString();
        String ser = spServicio.getSelectedItem().toString();
        String hab1 = spHabilidad1.getSelectedItem().toString();
        String hab2 = spHabilidad2.getSelectedItem().toString();
        String hab3 = spHabilidad3.getSelectedItem().toString();

        mDatabase.child("DetalleAnuncio").child(idanun).child("tituloAnuncio").setValue(tit.toString());
        mDatabase.child("DetalleAnuncio").child(idanun).child("tipoAnuncio").setValue(ser.toString());
        mDatabase.child("DetalleAnuncio").child(idanun).child("direccionAnuncio").setValue(dir.toString());
        mDatabase.child("DetalleAnuncio").child(idanun).child("habilidad1Anuncio").setValue(hab1.toString());
        mDatabase.child("DetalleAnuncio").child(idanun).child("habilidad2Anuncio").setValue(hab2.toString());
        mDatabase.child("DetalleAnuncio").child(idanun).child("habilidad3Anuncio").setValue(hab3.toString());
        Toast.makeText(EditarMiAnuncioOferta.this, "Datos Actualizados Correctamente", Toast.LENGTH_SHORT).show();
    }
}