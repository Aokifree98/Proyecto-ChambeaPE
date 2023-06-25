package com.example.chambeape.ui.ayuda;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chambeape.R;

public class AyudaFragment extends Fragment {

    Button btnReportar, btnSolicitudes, btnSoporteTecnico, btnPreguntasFrecuentes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista;
        vista = inflater.inflate(R.layout.fragment_ayuda, container, false);

        btnReportar = vista.findViewById(R.id.btnARportarProblema);
        btnSolicitudes = vista.findViewById(R.id.btnASolicitudes);
        btnSoporteTecnico = vista.findViewById(R.id.btnASoporteTecnico);
        btnPreguntasFrecuentes = vista.findViewById(R.id.btnAPreguntasFrecuentes);

       btnReportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ReportarProblema.class);
                startActivity(intent);
            }
        });

        btnSolicitudes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AyudarSolicitud.class);
                startActivity(intent);
            }
        });

        btnSoporteTecnico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SoporteTecnico.class);
                startActivity(intent);
            }
        });

        btnPreguntasFrecuentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PreguntasFrecuentes.class);
                startActivity(intent);
            }
        });

        return vista;
    }
}