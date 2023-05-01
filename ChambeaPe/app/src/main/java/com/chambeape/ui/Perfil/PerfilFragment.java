package com.chambeape.ui.Perfil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.chambeape.ListaAnuncioServicio;
import com.chambeape.R;

import java.util.List;

public class PerfilFragment extends Fragment {

    List<ListaAnuncioServicio> elemnts;
    ImageView fotoPerfilUser;
    Button editarPerfil, actualizarRedesS, contactoDirecto;
    RecyclerView listaServicios;
    ImageButton notificacion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista;
        vista = inflater.inflate(R.layout.fragment_perfil, container, false);
        fotoPerfilUser = vista.findViewById(R.id.imgPerfilUser);
        listaServicios = vista.findViewById(R.id.rclListaServicios);
        editarPerfil = vista.findViewById(R.id.btnEditPerfi);
        actualizarRedesS = vista.findViewById(R.id.btnRedesSoci);
        contactoDirecto = vista.findViewById(R.id.btnContactarUser);
        notificacion = vista.findViewById(R.id.imgNotifUser);
        //init();
        return vista;
    }

    /* public void init() {
        elemnts = new ArrayList<>();
        elemnts.add(new ListaAnuncioServicio("Electricista", "Tengo 5 años de experiencia"));
        elemnts.add(new ListaAnuncioServicio("Carpintero", "Tengo 15 años de experiencia"));

        ListAdapter listAdapter = new ListAdapter(elemnts, this);
        RecyclerView recyclerView = findViewById(R.id.crdvAnuncioServicio);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }*/
}