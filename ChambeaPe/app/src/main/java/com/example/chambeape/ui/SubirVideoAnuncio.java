package com.example.chambeape.ui;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chambeape.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SubirVideoAnuncio extends AppCompatActivity {
    Button btnSubirVideoCA,btnConinuarCA;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_video_anuncio);
        btnSubirVideoCA = findViewById(R.id.btnSubirVideoCA);
        btnConinuarCA = findViewById(R.id.btnContinuarCA);
        btnSubirVideoCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for showing progressDialog while uploading
                progressDialog = new ProgressDialog(SubirVideoAnuncio.this);
                choosevideo();
            }
        });
        btnConinuarCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idanun = getIntent().getExtras().getString("idanun");
                String dniuser= getIntent().getExtras().getString("dniuser");
                Intent i = new Intent(SubirVideoAnuncio.this, SeleccionarUbicacionCrearAnuncioOferta.class);
                String iduser = dniuser;
                i.putExtra("dniuser", iduser);
                i.putExtra("idanun", idanun);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

    }
    private void choosevideo() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 5);
    }

    Uri videouri;

    // startActivityForResult is used to receive the result, which is the selected video.
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            videouri = data.getData();
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            uploadvideo();
        }
    }

    private String getfiletype(Uri videouri) {
        ContentResolver r = getContentResolver();
        // get the file type ,in this case its mp4
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(r.getType(videouri));
    }

    private void uploadvideo() {
        String idanun = getIntent().getExtras().getString("idanun");
        String dniuser= getIntent().getExtras().getString("dniuser");

        if (videouri != null) {
            // save the selected video in Firebase storage
            //final StorageReference reference = FirebaseStorage.getInstance().getReference("Videos/" + System.currentTimeMillis() + "." + getfiletype(videouri));
            final StorageReference reference = FirebaseStorage.getInstance().getReference("Videos/vidAnuncio_"+dniuser+"_" + idanun + "." + getfiletype(videouri));
            reference.putFile(videouri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isSuccessful()) ;
                    // get the link of video
                    String downloadUri = uriTask.getResult().toString();
                    //DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("DetalleAnuncio").child("-NW3vE99MJV3iNyXeS0a").child("idVideoAnuncio");
                    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference();
                    //HashMap<String, String> map = new HashMap<>();
                    //map.put("videolink", downloadUri);
                    reference1.child("DetalleAnuncio").child(idanun).child("idVideoAnuncio").setValue(downloadUri);
                    // Video uploaded successfully
                    // Dismiss dialog
                    progressDialog.dismiss();
                    Toast.makeText(SubirVideoAnuncio.this, "Video Subido Correctamente!!", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Error, Image not uploaded
                    progressDialog.dismiss();
                    Toast.makeText(SubirVideoAnuncio.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                // Progress Listener for loading
                // percentage on the dialog box
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    // show the progress bar
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded " + (int) progress + "%");
                }
            });
        }
    }
}