package com.example.chambeape.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chambeape.R;

public class Login extends AppCompatActivity {
    EditText edtUsuario, edtPass;
    Button btnIniciarSesion,btnCrearUsuario;
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
    }
    public void llamarOlvidePass(View view) {
        //Intent i = new Intent(this, OlvidePass.class);
        //startActivity(i);
    }

    public void llamarPerfil(View view) {

        String uname= edtUsuario.getText().toString();
        String pas=edtPass.getText().toString();
        boolean verificado=false;

        if(uname.isEmpty() | uname.length()<8){
            edtUsuario.setError("Verifique su DNI");
        }
        else
            verificado = true;

        if(pas.isEmpty() | pas.length()<8){
            edtPass.setError("Formato password incorrecto");
            verificado = false;
        }

        if(verificado) ingresar();

    }

    private void ingresar() {
        Intent i = new Intent(this, PerfilUsuario.class);
        startActivity(i);
    }
}