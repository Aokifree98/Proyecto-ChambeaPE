package com.example.chambeape.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chambeape.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SubirFotoAnuncio extends AppCompatActivity {
    Button btnSubirFotoCA,btnRegistrarCA;
    ImageView imgFotoCA;
    Uri imagenUri;
    StorageReference miStorageRef;
    DatabaseReference miDatabaseRef;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_foto_anuncio);
        imgFotoCA = findViewById(R.id.imgFotoCA);
        btnSubirFotoCA = findViewById(R.id.btnSubirFotoCA);
        btnRegistrarCA = findViewById(R.id.btnRegistrarCA);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        miDatabaseRef =database.getReference();

        btnSubirFotoCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionFoto();
            }
        });
        btnRegistrarCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarFoto();
            }
        });
    }
    private void registrarFoto() {
        //String id = miDatabaseRef.push().getKey();
        String idAnuncio = getIntent().getExtras().getString("idanun");
        String idUser = getIntent().getExtras().getString("dniuser");

        if(imagenUri !=null)
        {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Cargando imagen....");
            progressDialog.show();

            //SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
            //Date now = new Date();
            //String fileName = "fotUsuario_"+id+"_"+formatter.format(now);
            String fileName = "fotAnuncio_"+idUser+"_"+idAnuncio;

            miStorageRef = FirebaseStorage.getInstance().getReference("Fotos/" +fileName);
            miStorageRef.putFile(imagenUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            miStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //Usuario usuario = new Usuario(id,"Test","Test","Test","Test","Test","Test","Test","Test","link","0","0",uri.toString(),"0");
                                    miDatabaseRef.child("DetalleAnuncio").child(idAnuncio).child("idFotoAnuncio").setValue(uri.toString());
                                    Toast.makeText(SubirFotoAnuncio.this,"Subido Correctamente",Toast.LENGTH_SHORT).show();
                                    if (progressDialog.isShowing())
                                        progressDialog.dismiss();
                                    imagenUri=null;
                                    imgFotoCA.setImageURI(null);
                                    Intent i = new Intent(SubirFotoAnuncio.this, SubirVideoAnuncio.class);
                                    String iduser = idUser;
                                    String idanu = idAnuncio;
                                    i.putExtra("dniuser", iduser);
                                    i.putExtra("idanun", idanu);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(i);
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            Toast.makeText(SubirFotoAnuncio.this,"Falla al subir",Toast.LENGTH_SHORT).show();
                        }
                    });


        }else{
            Toast.makeText(SubirFotoAnuncio.this,"No ha seleccionado una imagen",Toast.LENGTH_SHORT).show();
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
            imgFotoCA.setImageURI(imagenUri);
        }
    }
}