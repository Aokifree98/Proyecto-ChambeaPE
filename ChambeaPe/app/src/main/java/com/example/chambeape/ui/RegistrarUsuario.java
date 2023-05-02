package com.example.chambeape.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chambeape.entidades.Usuario;
import com.google.android.material.textfield.TextInputLayout;
import com.example.chambeape.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class RegistrarUsuario extends AppCompatActivity {

    TextInputLayout tilIdUsuario, tilnomUsuario, tilapepatUsuario, tilapematUsuario, tilmailUsuario,tildirusuario,tilfecUsuario;
    EditText edtIdUsuario, edtnomUsuario, edtapepatUsuario, edtapematUsuario, edtmailUsuario,edtdirUsuario,edtfecUsuario;
    Button btnRegistrarUsuario;

    private DatabaseReference miDatabase;
    public RegistrarUsuario(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        tilIdUsuario = findViewById(R.id.tilIdUsuario);
        tilnomUsuario = findViewById(R.id.tilnomUsuario);
        tilapepatUsuario = findViewById(R.id.tilapepatUsuario);
        tilapematUsuario = findViewById(R.id.tilapematUsuario);
        tilmailUsuario= findViewById(R.id.tilmailUsuario);
        tildirusuario = findViewById(R.id.tildirUsuario);
        tilfecUsuario = findViewById(R.id.tilfecUsuario);
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarVideojuego);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        miDatabase=database.getReference();

        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });
        edtIdUsuario = tilIdUsuario.getEditText().findViewById(R.id.edtIdUsuario);
        edtnomUsuario= tilnomUsuario.getEditText().findViewById(R.id.edtnomUsuario);
        edtapepatUsuario= tilapepatUsuario.getEditText().findViewById(R.id.edtapepatUsuario);
        edtapematUsuario= tilapematUsuario.getEditText().findViewById(R.id.edtapematUsuario);
        edtmailUsuario= tilmailUsuario.getEditText().findViewById(R.id.edtmailUsuario);
        edtdirUsuario= tildirusuario.getEditText().findViewById(R.id.edtdirUsuario);
        edtfecUsuario= tilfecUsuario.getEditText().findViewById(R.id.edtfecUsuario);
    }
    private void registrarUsuario() {
        String id = miDatabase.push().getKey();
        String nom = edtnomUsuario.getText().toString();
        String apep = edtapepatUsuario.getText().toString();
        String apem = edtapematUsuario.getText().toString();
        String mail = edtmailUsuario.getText().toString();
        String dire = edtdirUsuario.getText().toString();
        String fecha =edtfecUsuario.getText().toString();
        Usuario usuario = new Usuario(id,nom,"12345","999999999",apep,apem,mail,dire,fecha,"link","link","0");
        miDatabase.child("Usuarios").child(id).setValue(usuario);
        Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();

    }

}