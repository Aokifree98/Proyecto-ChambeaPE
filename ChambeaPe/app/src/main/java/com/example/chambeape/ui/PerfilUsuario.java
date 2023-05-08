package com.example.chambeape.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chambeape.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PerfilUsuario extends AppCompatActivity {
    ImageView imgPerfilUser, imgNotiUser;
    TextView txtNombreUser,txtDireccionUser;
    Button btnEditPerfi, btnRedesSoci,btnContactarUser,btnAgregarServicio;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        imgNotiUser=findViewById(R.id.imgNotifUser);
        imgPerfilUser=findViewById(R.id.imgPerfilUser);
        txtNombreUser=findViewById(R.id.txtNombreUser);
        txtDireccionUser=findViewById(R.id.txtDireccionUser);
        btnEditPerfi=findViewById(R.id.btnEditPerfi);
        btnRedesSoci=findViewById(R.id.btnRedesSoci);
        btnContactarUser=findViewById(R.id.btnContactarUser);
        btnAgregarServicio=findViewById(R.id.btnAgregarServicio);

        String dni = getIntent().getExtras().getString("dni");
        mDatabase.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(dni)){
                    String getnombre = snapshot.child(dni).child("nomUsuario").getValue(String.class);
                    String getapepat = snapshot.child(dni).child("apepatUsuario").getValue(String.class);
                    String getapemat = snapshot.child(dni).child("apematUsuario").getValue(String.class);
                    String getdirec = snapshot.child(dni).child("dirUsuario").getValue(String.class);
                    String url = snapshot.child(dni).child("fotUsuario").getValue(String.class);
                    String nombresperfil = getnombre+" "+getapepat+" "+getapemat;
                    txtNombreUser.setText(nombresperfil);
                    txtDireccionUser.setText(getdirec);
                    if(url.isEmpty())
                    {
                        Toast.makeText(PerfilUsuario.this,"No se encontro una foto de perfil",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Picasso.get().load(url).fit().centerCrop().into(imgPerfilUser);
                    }

                }
                else {
                    Toast.makeText(PerfilUsuario.this,"Error al cargar información",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
}