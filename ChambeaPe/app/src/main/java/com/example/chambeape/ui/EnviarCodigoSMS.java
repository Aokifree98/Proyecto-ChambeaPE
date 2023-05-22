package com.example.chambeape.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.chambeape.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

public class EnviarCodigoSMS extends AppCompatActivity {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_codigo_sms);

        final EditText inputMobile = findViewById(R.id.numTelefonico);
        Button buttonGetSMS = findViewById(R.id.btnObtenerSMS);

        final ProgressBar progressBar = findViewById(R.id.progressBar);
        String dni = getIntent().getExtras().getString("dni");
        mDatabase.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(dni)){
                    String gettele = snapshot.child(dni).child("telUsuario").getValue(String.class);
                    inputMobile.setText(gettele);

                }
                else {
                    Toast.makeText(EnviarCodigoSMS.this,"Error al cargar información",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        buttonGetSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputMobile.getText().toString().trim().isEmpty()){
                    Toast.makeText(EnviarCodigoSMS.this, "Ingrese su telefono",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                buttonGetSMS.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+51"+inputMobile.getText().toString(),60, TimeUnit.SECONDS,
                        EnviarCodigoSMS.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                buttonGetSMS.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                buttonGetSMS.setVisibility(View.VISIBLE);
                                Toast.makeText(EnviarCodigoSMS.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificacionId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                buttonGetSMS.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(getApplicationContext(),ValidarCodigoSMS.class);
                                intent.putExtra("mobile", inputMobile.getText().toString());
                                intent.putExtra("verificacionId", verificacionId);
                                startActivity(intent);
                            }
                        }
                );

            }
        });


    }
}