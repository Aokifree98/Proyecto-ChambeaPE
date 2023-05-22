package com.example.chambeape.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chambeape.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class EditarPerfilUsuario extends AppCompatActivity {

    ImageView imgEPFoto, imgedtFotoPerfil;
    EditText edtEPNombre, edtEPApellidoMat,edtEPApellidoPat,edtEPDistrito,edtEPDescripcion, edtEPTelefono, edtEPEmail;
    TextView txtEPTelefono;
    Button btnEPVerificarnumero,btnEPActualizar;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil_usuario);
        txtEPTelefono= findViewById(R.id.txtEPTelefono);
        imgEPFoto = findViewById(R.id.imgEPFoto);
        imgedtFotoPerfil = findViewById(R.id.imgedtFotoPerfil);
        edtEPNombre = findViewById(R.id.edtEPNombre);
        edtEPApellidoMat = findViewById(R.id.edtEPApellidoMat);
        edtEPApellidoPat = findViewById(R.id.edtEPApellidoPat);
        edtEPDistrito = findViewById(R.id.edtEPDistrito);
        edtEPDescripcion = findViewById(R.id.edtEPDescripcion);
        edtEPTelefono = findViewById(R.id.edtEPTelefono);
        edtEPEmail = findViewById(R.id.edtEPEmail);
        btnEPVerificarnumero = findViewById(R.id.btnEPVerificarNumero);
        btnEPActualizar = findViewById(R.id.btnEPActualizar);


        String dni = getIntent().getExtras().getString("dni");
        mDatabase.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(dni)){
                    String getnombre = snapshot.child(dni).child("nomUsuario").getValue(String.class);
                    String getapepat = snapshot.child(dni).child("apepatUsuario").getValue(String.class);
                    String getapemat = snapshot.child(dni).child("apematUsuario").getValue(String.class);
                    String getdirec = snapshot.child(dni).child("dirUsuario").getValue(String.class);
                    String gettele = snapshot.child(dni).child("telUsuario").getValue(String.class);
                    String getdescrip = snapshot.child(dni).child("desUsuario").getValue(String.class);
                    String getmail = snapshot.child(dni).child("mailUsuario").getValue(String.class);
                    String getVerTel = snapshot.child(dni).child("verTelefono").getValue(String.class);
                    String url = snapshot.child(dni).child("fotUsuario").getValue(String.class);

                    edtEPNombre.setText(getnombre);
                    edtEPApellidoPat.setText(getapepat);
                    edtEPApellidoMat.setText(getapemat);
                    edtEPDistrito.setText(getdirec);
                    edtEPDescripcion.setText(getdescrip);
                    edtEPTelefono.setText(gettele);
                    edtEPEmail.setText(getmail);
                    txtEPTelefono.setText("Telefono("+getVerTel+")");

                    if(url.isEmpty())
                    {
                        Toast.makeText(EditarPerfilUsuario.this,"No se encontro una foto de perfil",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Picasso.get().load(url).fit().centerCrop().into(imgEPFoto);
                    }

                }
                else {
                    Toast.makeText(EditarPerfilUsuario.this,"Error al cargar información",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        imgedtFotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditarPerfilUsuario.this, SubirFotoUsusario.class);
                String id = dni;
                i.putExtra("dni", id);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        btnEPActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarFoto(dni);
            }
        });
        btnEPVerificarnumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditarPerfilUsuario.this, EnviarCodigoSMS.class);
                String id = dni;
                i.putExtra("dni", id);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }



    private void actualizarFoto(String dni) {
        String nom = edtEPNombre.getText().toString();
        String apepat = edtEPApellidoPat.getText().toString();
        String apemat = edtEPApellidoMat.getText().toString();
        String dis = edtEPDistrito.getText().toString();
        String tel = edtEPTelefono.getText().toString();
        String des = edtEPDescripcion.getText().toString();
        String mail = edtEPEmail.getText().toString();
        mDatabase.child("Usuarios").child(dni).child("nomUsuario").setValue(nom.toString());
        mDatabase.child("Usuarios").child(dni).child("apepatUsuario").setValue(apepat.toString());
        mDatabase.child("Usuarios").child(dni).child("apematUsuario").setValue(apemat.toString());
        mDatabase.child("Usuarios").child(dni).child("dirUsuario").setValue(dis.toString());
        mDatabase.child("Usuarios").child(dni).child("telUsuario").setValue(tel.toString());
        mDatabase.child("Usuarios").child(dni).child("desUsuario").setValue(des.toString());
        mDatabase.child("Usuarios").child(dni).child("mailUsuario").setValue(mail.toString());
        Toast.makeText(EditarPerfilUsuario.this,"Datos Actualizados Correctamente",Toast.LENGTH_SHORT).show();
    }


}