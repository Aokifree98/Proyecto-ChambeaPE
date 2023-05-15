package com.chambeape.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.chambeape.MenuDesplegable;
import com.chambeape.PerfilUsuario;
import com.chambeape.R;
import com.chambeape.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    ImageView imgPefil;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View vista;

        vista = inflater.inflate(R.layout.fragment_home, container, false);
        imgPefil = vista.findViewById(R.id.imgFHPerfil);

        return vista;
    }


}