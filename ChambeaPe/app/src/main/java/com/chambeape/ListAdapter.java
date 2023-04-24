package com.chambeape;

import static com.chambeape.R.layout.anuncio_servicio_user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListaAnuncioServicio> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<ListaAnuncioServicio> itemList, Context context)
    {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = mInflater.inflate(anuncio_servicio_user, null);
        return new ListAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position)
    {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListaAnuncioServicio> items) {mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView fotoperf;
        TextView servicio, descripcion;

        ViewHolder(View itemView)
        {
            super(itemView);
            fotoperf = itemView.findViewById(R.id.imgPerfServi);
            servicio = itemView.findViewById(R.id.txtServicioUser);
            descripcion = itemView.findViewById(R.id.txtDescriServiUser);
        }

        void bindData(final ListaAnuncioServicio item)
        {

            servicio.setText(item.getServicio());
            descripcion.setText(item.getDescripcion());
        }
    }
}
