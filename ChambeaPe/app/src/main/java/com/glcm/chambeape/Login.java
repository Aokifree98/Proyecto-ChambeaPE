package com.glcm.chambeape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Login extends AppCompatActivity {

    EditText edtUsuario, edtPass;
    Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario = findViewById(R.id.edtDni);
        edtPass = findViewById(R.id.edtPassword);
        btnIniciar = findViewById(R.id.btnIngresar);
    }

    public void Llamar_OlvidePass(View view) {
        Intent i = new Intent(this, OlvidePass.class);
        startActivity(i);
    }

    public void Llamar_Perfil(View view) {

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