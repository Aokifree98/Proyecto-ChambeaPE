package com.glcm.chambeape;

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

public class CrearAnuncio extends AppCompatActivity {

    Button btnSubirFotoPU,btnRegistrarPU;
    ImageView imgFotoPU;
    Uri imagenUri;
    StorageReference miStorageRef;
    DatabaseReference miDatabaseRef;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_anuncio);

        imgFotoPU = findViewById(R.id.imgFotoPU);
        btnSubirFotoPU = findViewById(R.id.btnSubirFotoCA);
        btnRegistrarPU = findViewById(R.id.btnRegistrarCA);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        miDatabaseRef =database.getReference();

        btnSubirFotoPU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionFoto();
            }
        });
        btnRegistrarPU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarFoto();
            }
        });
    }
    private void registrarFoto() {
        //String id = miDatabaseRef.push().getKey();
        String dni = getIntent().getExtras().getString("dni");
        String id = dni;
        if(imagenUri !=null)
        {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Cargando imagen....");
            progressDialog.show();

            //SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
            //Date now = new Date();
            //String fileName = "fotUsuario_"+id+"_"+formatter.format(now);
            String fileName = "fotUsuario_"+id;

            miStorageRef = FirebaseStorage.getInstance().getReference(fileName);
            miStorageRef.putFile(imagenUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            miStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //Usuario usuario = new Usuario(id,"Test","Test","Test","Test","Test","Test","Test","Test","link","0","0",uri.toString(),"0");
                                    miDatabaseRef.child("Anuncio").child(id).child("fotAnuncio").setValue(uri.toString());
                                    Toast.makeText(CrearAnuncio.this,"Subido Correctamente",Toast.LENGTH_SHORT).show();
                                    if (progressDialog.isShowing())
                                        progressDialog.dismiss();
                                    imagenUri=null;
                                    imgFotoPU.setImageURI(null);
                                    Intent i = new Intent(CrearAnuncio.this, PerfilUsuario.class);
                                    String id = dni;
                                    i.putExtra("dni", id);
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
                            Toast.makeText(CrearAnuncio.this,"Falla al subir",Toast.LENGTH_SHORT).show();
                        }
                    });


        }else{
            Toast.makeText(CrearAnuncio.this,"No ha seleccionado una imagen",Toast.LENGTH_SHORT).show();
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
            imgFotoPU.setImageURI(imagenUri);
        }
    }
}