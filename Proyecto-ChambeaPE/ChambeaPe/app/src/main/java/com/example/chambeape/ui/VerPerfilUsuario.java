package com.example.chambeape.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chambeape.R;
import com.example.chambeape.entidades.Anuncio;
import com.example.chambeape.entidades.AnuncioAdapter;
import com.example.chambeape.ui.historial.HistorialFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class VerPerfilUsuario extends AppCompatActivity {
    ListView lstAnunciosOferta;
    List<Anuncio> listAnuncio = new ArrayList<Anuncio>();
    ArrayAdapter<Anuncio> arrayAdapterAnuncios;
    AnuncioAdapter anuncioAdapter;
    ImageView imgPerfilUser;
    TextView txtNombreUser,txtDescripcionUser,txtPunEmpPU,txtPunTraPU;
    Button btnRedesSoci,btnContactarPU,btnHistorialPU;
    String tel;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil_usuario);

        imgPerfilUser=findViewById(R.id.imgFotoPU);
        txtNombreUser=findViewById(R.id.txtNombrePU);
        txtDescripcionUser=findViewById(R.id.txtDescripcionPU);
        txtPunEmpPU=findViewById(R.id.txtPunEmpPU);
        txtPunTraPU=findViewById(R.id.txtPunTraPU);
        btnRedesSoci=findViewById(R.id.btnRedesSociPU);
        btnContactarPU=findViewById(R.id.btnContactarPU);
        btnHistorialPU=findViewById(R.id.btnHistorialPU);
        lstAnunciosOferta=findViewById(R.id.lstAnunciosOfertaPU);
        String dniuser = getIntent().getExtras().getString("dniuser");
        String idpubanun = getIntent().getExtras().getString("idpubanun");


        mDatabase.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(idpubanun)){
                    String getnombre = snapshot.child(idpubanun).child("nomUsuario").getValue(String.class);
                    String getapepat = snapshot.child(idpubanun).child("apepatUsuario").getValue(String.class);
                    String getapemat = snapshot.child(idpubanun).child("apematUsuario").getValue(String.class);
                    String getdesc = snapshot.child(idpubanun).child("desUsuario").getValue(String.class);
                    String url = snapshot.child(idpubanun).child("fotUsuario").getValue(String.class);
                    String gettel = snapshot.child(idpubanun).child("telUsuario").getValue(String.class);
                    String getpunemp = snapshot.child(idpubanun).child("calUsuarioEmp").getValue(String.class);
                    String getpuntra = snapshot.child(idpubanun).child("calUsuarioTra").getValue(String.class);
                    tel = gettel;
                    String nombresperfil = getnombre+" "+getapepat+" "+getapemat;
                    txtNombreUser.setText(nombresperfil);
                    txtDescripcionUser.setText(getdesc);
                    txtPunEmpPU.setText(getpunemp);
                    txtPunTraPU.setText(getpuntra);
                    if(url.isEmpty())
                    {
                        Toast.makeText(VerPerfilUsuario.this,"No se encontro una foto de perfil",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Picasso.get().load(url).fit().centerCrop().into(imgPerfilUser);
                    }

                }
                else {
                    Toast.makeText(VerPerfilUsuario.this,"Error al cargar información",Toast.LENGTH_SHORT).show();
                }

                //Query productosxestado = reference.orderByChild("tipo").equalTo(tipo);
                Query anunciossxdni = mDatabase.child("DetalleAnuncio");
                listAnuncio.clear();
                anunciossxdni.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot resultados) {
                        for(DataSnapshot itemsdata: resultados.getChildren()){
                            Anuncio a = itemsdata.getValue(Anuncio.class);
                            if(a.getIdPublicadorAnuncio().equals(idpubanun)){
                                listAnuncio.add(a);
                                anuncioAdapter = new AnuncioAdapter(VerPerfilUsuario.this, R.layout.itemanuncio,listAnuncio);
                                anuncioAdapter.setActiveUser(dniuser);
                            }
                        }
                        arrayAdapterAnuncios = new ArrayAdapter<Anuncio>
                                (VerPerfilUsuario.this, android.R.layout.simple_list_item_1,listAnuncio);
                        lstAnunciosOferta.setAdapter(anuncioAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        btnContactarPU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono = tel;
                //No funciona la comprobación de inslado del whatsapp
                //boolean installed = appInstalledOrNot("com.whatsapp");
                //if (installed){
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://api.whatsapp.com/send?phone=51"+telefono+"&text=Hola necesito más información sobre una oferta que publicaste en ChambeaPE"));
                    startActivity(i);
                //}else {
                    //Toast.makeText(VerPerfilUsuario.this,"Whatsapp no esta instalado",Toast.LENGTH_SHORT).show();
                //}
                /*
                Intent i = new Intent(VerPerfilUsuario.this, EditarPerfilUsuario.class);
                String iduser = dniuser;
                i.putExtra("dniuser", iduser);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                */
            }
        });
        btnHistorialPU.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idpubanun1 = getIntent().getExtras().getString("idpubanun");
                String idanun1 = getIntent().getExtras().getString("idanun");
                String dniuser1 = getIntent().getExtras().getString("dniuser");
                Intent i = new Intent(VerPerfilUsuario.this, HistorialFragment.class);
                i.putExtra("dniuser", dniuser1);
                i.putExtra("idpubanun", idpubanun1);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        }));
    }
    /*
    private boolean appInstalledOrNot(String url) {
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try{
            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed= false;
        }
        return app_installed;
    }
    */

}