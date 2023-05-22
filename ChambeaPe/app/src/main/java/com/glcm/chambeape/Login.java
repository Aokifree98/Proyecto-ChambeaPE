package com.glcm.chambeape;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class Login extends AppCompatActivity {

    EditText edtUsuario, edtPass;
    Button btnIniciar, btnIniciarGmail;
    FirebaseAuth authgmail;
    FirebaseDatabase database;
    GoogleSignInClient mGoogleSingInClient;
    int RC_SING_IN=20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario = findViewById(R.id.edtDni);
        edtPass = findViewById(R.id.edtPassword);
        btnIniciar = findViewById(R.id.btnIngresar);

        btnIniciarGmail = findViewById(R.id.btnIniciarGmail);
        authgmail= FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSingInClient = GoogleSignIn.getClient(this, gso);

        btnIniciarGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSingIn();
            }
        });

    }

    private void googleSingIn() {
        Intent intent = mGoogleSingInClient.getSignInIntent();
        startActivityForResult(intent, RC_SING_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RC_SING_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                fitbaseAuth(account.getIdToken());

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void fitbaseAuth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        authgmail.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser user = authgmail.getCurrentUser();

                            HashMap<String,Object> map = new HashMap<>();
                            map.put("id",user.getUid());
                            map.put("name",user.getDisplayName());
                            map.put("profile", user.getPhotoUrl().toString());

                            database.getReference().child("Usuarios").child(user.getUid()).setValue(map);
                            Intent intent = new Intent(Login.this, PerfilUsuario.class);
                        }
                        else{
                            Toast.makeText(Login.this, "Algo salió mal", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
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