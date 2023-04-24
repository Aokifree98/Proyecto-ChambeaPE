package com.glcm.chambeape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class OlvidePass extends AppCompatActivity {

    EditText edtRCEmail, edtCode;
    Button btnRecuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvide_pass);

        btnRecuperar = findViewById(R.id.btnRecuperarPass);
        edtRCEmail = findViewById(R.id.edtRecuperarEmail);
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarEmail();
            }
        });

    }


    public void validarEmail() {
        String mail = edtRCEmail.getText().toString();

        if (mail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            edtRCEmail.setError("Correo inválido");
            return;
        }
        else {
            Intent intent = new Intent(OlvidePass.this, Login.class);
            startActivity(intent);
        }

       // enviarEmail(mail);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(OlvidePass.this, Login.class);
        startActivity(intent);
    }
    




}