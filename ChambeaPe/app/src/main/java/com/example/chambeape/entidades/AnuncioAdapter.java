package com.example.chambeape.entidades;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chambeape.R;
import com.example.chambeape.ui.ListaOfertasTrabajo;
import com.example.chambeape.ui.VerMiAnuncioOfertaTrabajo;

import java.util.List;

public class AnuncioAdapter extends ArrayAdapter<Anuncio> {
    private List<Anuncio> listAnuncio;
    private Context pcontext;
    private int resourcelayaout;
    private String dniActiveUser;

    public void setActiveUser(String dniActiveUser) {
        this.dniActiveUser = dniActiveUser;
    }
    public AnuncioAdapter(@NonNull Context context, int resource, List<Anuncio> objects){
        super(context, resource, objects);
        this.listAnuncio = objects;
        this.pcontext = context;
        this.resourcelayaout = resource;


    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(pcontext).inflate(resourcelayaout, null);
        }

        Anuncio anuncio = listAnuncio.get(position);

        ImageView imgItem;
        VideoView vidItem;
        TextView txtIdAnun,txtDescripcionAnun,txtHabilidad1,txtHabilidad2,txtHabilidad3,txtEstadoAnun,txtFechaAnun;

        txtIdAnun = view.findViewById(R.id.txtIdAnun);
        txtDescripcionAnun = view.findViewById(R.id.txtDescripcionAnun);
        txtHabilidad1 = view.findViewById(R.id.txtHabilidad1);
        txtHabilidad2 = view.findViewById(R.id.txtHabilidad2);
        txtHabilidad3 = view.findViewById(R.id.txtHabilidad3);
        txtEstadoAnun = view.findViewById(R.id.txtEstadoAnun);
        txtFechaAnun = view.findViewById(R.id.txtFechaAnun);
        //imgItem = view.findViewById(R.id.imgItem);
        //vidItem = view.findViewById(R.id.vidItem);

        txtIdAnun.setText(anuncio.getIdAnuncio());
        txtDescripcionAnun.setText(anuncio.getTituloAnuncio());
        txtHabilidad1.setText(anuncio.getHabilidad1Anuncio());
        txtHabilidad2.setText(anuncio.getHabilidad2Anuncio());
        txtHabilidad3.setText(anuncio.getHabilidad3Anuncio());
        txtEstadoAnun.setText(anuncio.getEstadoAnuncio());
        txtFechaAnun.setText(anuncio.getFecchaPublicacionAnuncio());
        //String url = anuncio.getIdFotoAnuncio().toString();
        //Picasso.get().load(url).fit().centerCrop().into(imgItem);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(), VerMiAnuncioOfertaTrabajo.class);
                //String dniuser = anuncio.getIdPublicadorAnuncio().toString();
                //String dniuser = i.getStringExtra("dniuser");
                i.putExtra("idanun",txtIdAnun.getText().toString());
                i.putExtra("dniuser",dniActiveUser);
                //i.putExtra("dniuser",dniuser);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                view.getContext().startActivity(i);
            }
        });
        return view;
    }
}
