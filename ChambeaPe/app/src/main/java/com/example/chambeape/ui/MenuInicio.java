package com.example.chambeape.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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


public class MenuInicio extends AppCompatActivity {
    ImageView imgMIPerfil;
    ImageButton imgBtnNotificaciones;
    Button btnMIBuscarTra, btnMIBuscarOfer,btnMICreATrab,btnMICreAOfer;

    TextView txtMINombreUsuario;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);

        imgMIPerfil = findViewById(R.id.imgMIPerfil);
        imgBtnNotificaciones=findViewById(R.id.imgBtnNotificaciones);
        btnMIBuscarTra= findViewById(R.id.btnMIBuscarTra);
        btnMIBuscarOfer= findViewById(R.id.btnMIBuscarOfer);
        btnMICreATrab= findViewById(R.id.btnMICreATrab);
        btnMICreAOfer= findViewById(R.id.btnMICreaAOfer);
        txtMINombreUsuario=findViewById(R.id.txtMINombreUsuario);

        //Toast.makeText(this,dni,Toast.LENGTH_SHORT).show();

        String dniuser = getIntent().getExtras().getString("dniuser");
        mDatabase.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(dniuser)){
                    String getnombre = snapshot.child(dniuser).child("nomUsuario").getValue(String.class);
                    String getapepat = snapshot.child(dniuser).child("apepatUsuario").getValue(String.class);
                    String getapemat = snapshot.child(dniuser).child("apematUsuario").getValue(String.class);
                    String url = snapshot.child(dniuser).child("fotUsuario").getValue(String.class);
                    String nombresperfil = getnombre+" "+getapepat+" "+getapemat;
                    txtMINombreUsuario.setText(nombresperfil);
                    if(url.isEmpty())
                    {
                        Toast.makeText(MenuInicio.this,"No se encontro una foto de perfil",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Picasso.get().load(url).fit().centerCrop().into(imgMIPerfil);
                    }

                }
                else {
                    Toast.makeText(MenuInicio.this,"Error al cargar información",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        imgMIPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent i = new Intent(MenuInicio.this, SubirFotoUsusario.class);
                String id = dni;
                i.putExtra("dni", id);
                startActivity(i);
                */
                Intent i = new Intent(MenuInicio.this, PerfilUsuario.class);
                String iduser = dniuser;
                i.putExtra("dniuser", iduser);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        txtMINombreUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuInicio.this, PerfilUsuario.class);
                String iduser = dniuser;
                i.putExtra("dniuser", iduser);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        btnMICreAOfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuInicio.this, CrearAnuncioOfertaTrabajo.class);
                String iduser = dniuser;
                i.putExtra("dniuser", iduser);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

    }
}