package com.emgs.milupainteligente;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.ListViewHolder>{

    ArrayList<HistorialItem> listaHistorial;

    public HistorialAdapter(ArrayList<HistorialItem> listaHistorial){
        this.listaHistorial=listaHistorial;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_historial,null,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialAdapter.ListViewHolder holder, int position) {
        holder.fecha.setText(listaHistorial.get(position).getFecha());
        holder.resultado.setText(listaHistorial.get(position).getResultado());
    }

    @Override
    public int getItemCount() { return listaHistorial.size();}

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView fecha,resultado;
        
        public ListViewHolder (View itemView){
            super(itemView);
            fecha = (TextView) itemView.findViewById(R.id.idFechaItem);
            resultado = (TextView) itemView.findViewById(R.id.idResultadoItem);
        }
    }
}