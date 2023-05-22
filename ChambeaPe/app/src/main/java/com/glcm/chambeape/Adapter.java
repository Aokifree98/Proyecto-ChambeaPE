package com.glcm.chambeape;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
   Context context;
   ArrayList<Anuncio> list;

    public Adapter(Context context, ArrayList<Anuncio> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_item_busco_empleo,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    Anuncio anuncio=list.get(position);
    holder.txtBEProfesionServicio.setText(anuncio.getHabilidad1());
    holder.txtBEDescriciónServicio.setText(anuncio.getDescrAnuncio());
    holder.txtBEUbicaciónServicio.setText(anuncio.getUbiAnuncio());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtBEProfesionServicio, txtBEDescriciónServicio, txtBEUbicaciónServicio;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBEProfesionServicio = itemView.findViewById(R.id.txtBEProfesionServicio);
            txtBEDescriciónServicio = itemView.findViewById(R.id.txtBEDescriciónServicio);
            txtBEUbicaciónServicio = itemView.findViewById(R.id.txtBEUbicaciónServicio);
        }
    }
}
