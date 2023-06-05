package com.example.chambeape.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.chambeape.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class VerMiAnuncioOfertaTrabajo extends AppCompatActivity {

    TextView txtUsuarioOferta,txtTipoOferta,txtTituloOferta,txtHabilidadOferta1,txtHabilidadOferta2,txtHabilidadOferta3;
    ImageView imgFotoOferta;
    VideoView vidVideoOferta;
    Button btnContactarOferta;

    DatabaseReference mDatabaseanu = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mDatabaseusu = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_mi_anuncio_oferta_trabajo);

        txtUsuarioOferta= findViewById(R.id.txtUsuarioOferta);
        txtTipoOferta= findViewById(R.id.txtTipoOferta);
        txtTituloOferta= findViewById(R.id.txtTituloOferta);
        txtHabilidadOferta1= findViewById(R.id.txtHabilidadOferta1);
        txtHabilidadOferta2= findViewById(R.id.txtHabilidadOferta2);
        txtHabilidadOferta3= findViewById(R.id.txtHabilidadOferta3);
        imgFotoOferta= findViewById(R.id.imgFotoOferta);
        vidVideoOferta= findViewById(R.id.vidVideoOferta);
        btnContactarOferta= findViewById(R.id.btnContactarOferta);
        String idanun = getIntent().getExtras().getString("idanun");
        String dniuser = getIntent().getExtras().getString("dniuser");
        Toast.makeText(VerMiAnuncioOfertaTrabajo.this,dniuser,Toast.LENGTH_SHORT).show();
        Toast.makeText(VerMiAnuncioOfertaTrabajo.this,idanun,Toast.LENGTH_SHORT).show();

        mDatabaseanu.child("DetalleAnuncio").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(idanun)){
                    String getusuario = snapshot.child(idanun).child("idPublicadorAnuncio").getValue(String.class);
                    String gettipofer = snapshot.child(idanun).child("tipoAnuncio").getValue(String.class);
                    String gettitulo = snapshot.child(idanun).child("tituloAnuncio").getValue(String.class);
                    String gethab1 = snapshot.child(idanun).child("habilidad1Anuncio").getValue(String.class);
                    String gethab2 = snapshot.child(idanun).child("habilidad2Anuncio").getValue(String.class);
                    String gethab3 = snapshot.child(idanun).child("habilidad3Anuncio").getValue(String.class);
                    String urlfoto = snapshot.child(idanun).child("idFotoAnuncio").getValue(String.class);
                    String urlvideo = snapshot.child(idanun).child("idVideoAnuncio").getValue(String.class);
                    txtTipoOferta.setText(gettipofer);
                    txtTituloOferta.setText(gettitulo);
                    txtHabilidadOferta1.setText(gethab1);
                    txtHabilidadOferta2.setText(gethab2);
                    txtHabilidadOferta3.setText(gethab3);

                    String videoUrl = urlvideo;
                    Uri videoUri = Uri.parse(videoUrl);
                    vidVideoOferta.setVideoURI(videoUri);
                    MediaController mediaController = new MediaController(VerMiAnuncioOfertaTrabajo.this);
                    vidVideoOferta.setMediaController(mediaController);
                    mediaController.setAnchorView(vidVideoOferta);
                    //vidVideoOferta.start();
                    if(urlfoto.isEmpty())
                    {
                        Toast.makeText(VerMiAnuncioOfertaTrabajo.this,"No se encontro una foto de perfil",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Picasso.get().load(urlfoto).fit().centerCrop().into(imgFotoOferta);
                    }
                    mDatabaseusu.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(getusuario)){
                                String nomuser = snapshot.child(getusuario).child("nomUsuario").getValue(String.class);
                                String apepatuser = snapshot.child(getusuario).child("apepatUsuario").getValue(String.class);
                                String apematuser = snapshot.child(getusuario).child("apematUsuario").getValue(String.class);
                                txtUsuarioOferta.setText(nomuser+" "+apepatuser+" "+apematuser);
                            }
                            else {
                                Toast.makeText(VerMiAnuncioOfertaTrabajo.this,"Error al cargar información",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });

                }
                else {
                    Toast.makeText(VerMiAnuncioOfertaTrabajo.this,"Error al cargar información",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
}