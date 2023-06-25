package com.example.chambeape.entidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chambeape.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImagenAnuncioAdapter /*extends RecyclerView.Adapter<ImagenAnuncioAdapter.ImagenViewHolder>*/ {
/*
    Context mcontext;
    List<Anuncio> listaanuncios;

    public ImagenAnuncioAdapter(Context context, List<Anuncio> anuncios){
        mcontext = context;
        listaanuncios = anuncios;
    }
    @NonNull
    @Override
    public ImagenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.itemanuncio,parent,false);
        RecyclerView.LayoutParams formaLayout= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //RecyclerView.LayoutParams formaLayout= new RecyclerView.LayoutParams(350,450);
        v.setLayoutParams(formaLayout);
        return new ImagenViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImagenViewHolder holder, int position) {
        Anuncio anuncioactual = listaanuncios.get(position);
        holder.txtItem.setText(anuncioactual.getDescrAnuncio());
        Picasso.get().load(anuncioactual.getIdFotoAnuncio()).fit().centerCrop().into(holder.imgItem);
    }

    @Override
    public int getItemCount() {
        return listaanuncios.size();
    }

    public class ImagenViewHolder extends RecyclerView.ViewHolder {
        TextView txtItem;
        ImageView imgItem;
        public ImagenViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.txtItem);
            imgItem = itemView.findViewById(R.id.imgItem);
        }
    }
 */
}
