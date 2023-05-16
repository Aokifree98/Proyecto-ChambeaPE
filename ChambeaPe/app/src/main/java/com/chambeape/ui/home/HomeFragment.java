package com.chambeape.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.chambeape.AnuncioOfertaTrabajo;
import com.chambeape.MenuDesplegable;
import com.chambeape.PerfilUsuario;
import com.chambeape.R;
import com.chambeape.databinding.FragmentHomeBinding;
import com.chambeape.ui.Perfil.PerfilFragment;

public class HomeFragment extends Fragment {

    ImageView imgPefil;
    Button ofertar;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View vista;

        vista = inflater.inflate(R.layout.fragment_home, container, false);
        imgPefil = vista.findViewById(R.id.imgFHPerfil);
        ofertar = vista.findViewById(R.id.MCreaAOfer);

        return vista;
    }

    public void llamarPerfilUsuario(View view) {
        Intent c = new Intent(getContext(), PerfilFragment.class);
        startActivity(c);
    }


    public void llamarAnuncioOferta(View view) {
        Intent c = new Intent(getContext(), AnuncioOfertaTrabajo.class);
        startActivity(c);
    }


}