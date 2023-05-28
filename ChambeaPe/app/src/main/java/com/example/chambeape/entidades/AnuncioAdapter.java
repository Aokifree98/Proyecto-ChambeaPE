package com.example.chambeape.entidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chambeape.R;

import java.util.List;

public class AnuncioAdapter extends ArrayAdapter<Anuncio> {
    private List<Anuncio> listAnuncio;
    private Context pcontext;
    private int resourcelayaout;
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

        TextView txtIdAnun,txtDescripcionAnun,txtHabilidad1,txtHabilidad2,txtHabilidad3,txtEstadoAnun,txtFechaAnun;

        txtIdAnun = view.findViewById(R.id.txtIdAnun);
        txtDescripcionAnun = view.findViewById(R.id.txtDescripcionAnun);
        txtHabilidad1 = view.findViewById(R.id.txtHabilidad1);
        txtHabilidad2 = view.findViewById(R.id.txtHabilidad2);
        txtHabilidad3 = view.findViewById(R.id.txtHabilidad3);
        txtEstadoAnun = view.findViewById(R.id.txtEstadoAnun);
        txtFechaAnun = view.findViewById(R.id.txtFechaAnun);

        txtIdAnun.setText(anuncio.getIdAnuncio());
        txtDescripcionAnun.setText(anuncio.getDescrAnuncio());
        txtHabilidad1.setText(anuncio.getHabilidad1());
        txtHabilidad2.setText(anuncio.getHabilidad2());
        txtHabilidad3.setText(anuncio.getHabilidad3());
        txtEstadoAnun.setText(anuncio.getEstadoAnuncio());
        txtFechaAnun.setText(anuncio.getFecAnuncio());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent i = new Intent(view.getContext(), LugarEntrega.class);
                i.putExtra("d1",txtLatLista.getText().toString());
                i.putExtra("d2",txtLongLista.getText().toString());
                i.putExtra("d3",txtTipo.getText().toString());
                i.putExtra("d4",txtDescripcion.getText().toString());
                i.putExtra("d5",txtDestinatario.getText().toString());
                view.getContext().startActivity(i);
                 */
            }
        });
        return view;
    }
}
