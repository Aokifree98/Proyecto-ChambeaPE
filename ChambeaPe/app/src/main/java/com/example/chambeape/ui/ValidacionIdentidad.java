package com.example.chambeape.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chambeape.R;
import com.example.chambeape.entidades.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ValidacionIdentidad extends AppCompatActivity {
    Button btnSubirFoto,btnSubirDNI,btnRegistrar;
    ImageView imgFoto,imgDNI;
    Uri imagenUri;
    StorageReference miStorageRef;
    DatabaseReference miDatabaseRef;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacion_identidad);
        imgFoto = findViewById(R.id.imgFoto);
        imgDNI = findViewById(R.id.imgDNI);
        btnSubirFoto = findViewById(R.id.btnSubirFoto);
        btnSubirDNI=findViewById(R.id.btnSubirDNI);
        btnRegistrar= findViewById(R.id.btnRegistrar);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        miDatabaseRef =database.getReference();

        btnSubirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionFoto();
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    private void registrar() {
        //String id = miDatabaseRef.push().getKey();
        String id = "7777777";
        if(imagenUri !=null)
        {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Cargando imagen....");
            progressDialog.show();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
            Date now = new Date();
            String fileName = formatter.format(now);
            miStorageRef = FirebaseStorage.getInstance().getReference(fileName);

            miStorageRef.putFile(imagenUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            miStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Usuario usuario = new Usuario(id,"Test","Test","999999999","Test","Test","Test","Test","Test","link",uri.toString(),"0");
                                    miDatabaseRef.child("Usuarios").child(id).setValue(usuario);
                                    Toast.makeText(ValidacionIdentidad.this,"Subido Correctamente",Toast.LENGTH_SHORT).show();
                                    if (progressDialog.isShowing())
                                        progressDialog.dismiss();
                                    imagenUri=null;
                                    imgFoto.setImageURI(null);
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            Toast.makeText(ValidacionIdentidad.this,"Falla al subir",Toast.LENGTH_SHORT).show();
                        }
                    });
        }else{
            Toast.makeText(ValidacionIdentidad.this,"No ha seleccionado una imagen",Toast.LENGTH_SHORT).show();
        }

    }

    private void seleccionFoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null){
            imagenUri = data.getData();
            imgFoto.setImageURI(imagenUri);
        }
    }
}