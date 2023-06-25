package com.example.chambeape.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.chambeape.R;
import com.example.chambeape.ui.CrearAnuncioOfertaTrabajo;
import com.example.chambeape.ui.ListaOfertasTrabajo;
import com.example.chambeape.ui.MiPerfilUsuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    ImageView imgMIPerfil;
    ImageButton imgBtnNotificaciones;
    Button btnMIBuscarTra, btnMIBuscarOfer,btnMICreATrab,btnMICreAOfer;

    TextView txtMINombreUsuario;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View vista;

        vista = inflater.inflate(R.layout.fragment_home, container, false);

        imgMIPerfil = vista.findViewById(R.id.imgMIPerfil);
        imgBtnNotificaciones = vista.findViewById(R.id.imgBtnNotificaciones);
        btnMIBuscarTra = vista.findViewById(R.id.btnMIBuscarTra);
        btnMIBuscarOfer = vista.findViewById(R.id.btnMIBuscarOfer);
        btnMICreATrab = vista.findViewById(R.id.btnMICreATrab);
        btnMICreAOfer = vista.findViewById(R.id.btnMICreaAOfer);
        txtMINombreUsuario = vista.findViewById(R.id.txtMINombreUsuario);

        String dniuser = getActivity().getIntent().getExtras().getString("dniuser");

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
                        Toast.makeText(getContext(),"No se encontro una foto de perfil",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Picasso.get().load(url).fit().centerCrop().into(imgMIPerfil);
                    }

                }
                else {
                    Toast.makeText(getContext(),"Error al cargar información",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        imgMIPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getContext(), MiPerfilUsuario.class);
                String iduser = dniuser;
                i.putExtra("dniuser", iduser);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        txtMINombreUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MiPerfilUsuario.class);
                String iduser = dniuser;
                i.putExtra("dniuser", iduser);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        btnMICreAOfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), CrearAnuncioOfertaTrabajo.class);
                String iduser = dniuser;
                i.putExtra("dniuser", iduser);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        btnMIBuscarOfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ListaOfertasTrabajo.class);
                String iduser = dniuser;
                i.putExtra("dniuser", iduser);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        return vista;
    }

}