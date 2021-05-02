package com.emgs.milupainteligente;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListViewHolder>{

    ArrayList<ListItem> listaItem;

    public ListItemAdapter(ArrayList<ListItem> listaItem){
        this.listaItem=listaItem;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.descripcion.setText(listaItem.get(position).getDescripcion());
        holder.icono.setImageResource(listaItem.get(position).getIconResource());
    }

    @Override
    public int getItemCount() {return listaItem.size(); }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView descripcion;
        ImageView icono;

        public ListViewHolder (View itemView){
            super(itemView);
            descripcion = (TextView) itemView.findViewById(R.id.descripcionIcono);
            icono = (ImageView) itemView.findViewById(R.id.idIconos);
        }
    }
}