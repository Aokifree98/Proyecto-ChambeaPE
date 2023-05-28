package com.example.chambeape.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chambeape.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText edtUsuario, edtPass;
    Button btnIniciarSesion,btnCrearUsuario;

    //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://console.firebase.google.com/u/1/project/chambeape-aaf69/database/chambeape-aaf69-default-rtdb/data/~2F");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario = findViewById(R.id.edtDni);
        edtPass = findViewById(R.id.edtPassword);
        btnIniciarSesion = findViewById(R.id.btnIngresar);
        btnCrearUsuario= findViewById(R.id.btnCrearCuenta);

        btnCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CrearUsuario.class);
                startActivity(intent);
            }
        });

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                String dni = edtUsuario.getText().toString();
                String pass = edtPass.getText().toString();
                if(dni.isEmpty()||pass.isEmpty())
                {
                    Toast.makeText(Login.this,"Por favor ingrese su dni y su contraseña",Toast.LENGTH_SHORT).show();
                }
                else{
                    mDatabase.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(dni)){
                                String getPassword=snapshot.child(dni).child("passUsuario").getValue(String.class);
                                if(getPassword.equals(pass)){
                                    Intent i = new Intent(Login.this, MenuInicio.class);
                                    String iduser = dni;
                                    i.putExtra("dniuser", iduser);
                                    Toast.makeText(Login.this,"Exito al ingresar.",Toast.LENGTH_SHORT).show();
                                    //startActivity(new Intent(Login.this,MenuInicio.class));
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(i);
                                    //finish();
                                }


                                else {
                                    Toast.makeText(Login.this,"Contraseña incorrecta",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(Login.this,"No hay el dni",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });
    }



    public void llamarOlvidePass(View view) {
        //Intent i = new Intent(this, OlvidePass.class);
        //startActivity(i);
    }



    private void ingresar() {
        Intent i = new Intent(this, PerfilUsuario.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}