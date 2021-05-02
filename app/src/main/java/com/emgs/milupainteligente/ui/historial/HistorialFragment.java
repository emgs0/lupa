package com.emgs.milupainteligente.ui.historial;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emgs.milupainteligente.HistorialAdapter;
import com.emgs.milupainteligente.HistorialItem;
import com.emgs.milupainteligente.R;
import com.emgs.milupainteligente.Resultado;

import java.util.ArrayList;
import java.util.List;

public class HistorialFragment extends Fragment {

    List<HistorialItem> elementsHistorial;
    RecyclerView recyclerHistorial;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_historial, container, false);
        elementsHistorial = new ArrayList<>();
        recyclerHistorial= (RecyclerView) root.findViewById(R.id.listHistorial);
        recyclerHistorial.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarHistorial();
        HistorialAdapter adapter = new HistorialAdapter((ArrayList<HistorialItem>) elementsHistorial);
        recyclerHistorial.setAdapter(adapter);

        return root;
    }

    private void llenarHistorial() {
        elementsHistorial.add(new HistorialItem(Resultado.date,Resultado.s));
    }

}