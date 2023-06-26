package com.example.chambeape.entidades;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chambeape.R;
import com.example.chambeape.ui.VerPerfilUsuario;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactosAdapter extends ArrayAdapter<Usuario> {

    private List<Usuario> listContactos;
    private Context pcontext;
    private int resourcelayaout;
    private String dniActiveUser;

    public void setActiveUser(String dniActiveUser) {
        this.dniActiveUser = dniActiveUser;
    }

    public ContactosAdapter(@NonNull Context context, int resource, List<Usuario> objects) {
        super(context, resource);
        this.listContactos= objects;
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

        Usuario contacto = listContactos.get(position);

        ImageView imgViewContacto;
        TextView txtIdContacto,txtNomContacto,txtApeContacto;

        txtIdContacto = view.findViewById(R.id.txtIdContacto);
        txtNomContacto = view.findViewById(R.id.txtNomContacto);
        txtApeContacto = view.findViewById(R.id.txtApeContacto);
        imgViewContacto = view.findViewById(R.id.imgViewContacto);

        txtIdContacto.setText(contacto.getDniUsuario());
        txtNomContacto.setText(contacto.getNomUsuario());
        txtApeContacto.setText(contacto.getApepatUsuario());
        String url = contacto.getFotUsuario().toString();
        Picasso.get().load(url).fit().centerCrop().into(imgViewContacto);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(), VerPerfilUsuario.class);
                //String dniuser = anuncio.getIdPublicadorAnuncio().toString();
                //String dniuser = i.getStringExtra("dniuser");
                i.putExtra("idpubanun",txtIdContacto.getText().toString());
                i.putExtra("dniuser",dniActiveUser);
                //i.putExtra("dniuser",dniuser);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                view.getContext().startActivity(i);
            }
        });
        return view;
    }
}
