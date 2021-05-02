package com.emgs.milupainteligente.ui.ayuda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.emgs.milupainteligente.ListItemAdapter;
import com.emgs.milupainteligente.ListItem;
import com.emgs.milupainteligente.R;
import java.util.ArrayList;
import java.util.List;

public class AyudaFragment extends Fragment {

    List<ListItem> elements;
    RecyclerView recyclerAyuda;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_ayuda, container, false);
        elements = new ArrayList<>();
        recyclerAyuda=(RecyclerView) root.findViewById(R.id.listRecyclerView);
        recyclerAyuda.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarLista();
        ListItemAdapter adapter = new ListItemAdapter((ArrayList<ListItem>) elements);
        recyclerAyuda.setAdapter(adapter);

        return root;
    }

    private void llenarLista() {
        elements.add(new ListItem(R.drawable.ic_help,getString(R.string.descripcion_ayuda)));
        elements.add(new ListItem(R.drawable.ic_camera,getString(R.string.descripcion_camara)));
        elements.add(new ListItem(R.drawable.ic_gallery,getString(R.string.descripcion_galeria)));
        elements.add(new ListItem(R.drawable.ic_history,getString(R.string.descripcion_historial)));
        elements.add(new ListItem(R.drawable.ic_toggle,getString(R.string.descripcion_nocturno)));
        elements.add(new ListItem(R.drawable.ic_play,getString(R.string.descripcion_play)));
        elements.add(new ListItem(R.drawable.ic_copy,getString(R.string.descripcion_copiar)));
    }
}