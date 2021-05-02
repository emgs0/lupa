package com.emgs.milupainteligente.ui.galeria;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.emgs.milupainteligente.R;
import com.emgs.milupainteligente.Resultado;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;


public class GaleriaFragment extends Fragment {
    ImageView imageViewGaleria;
    TextView textViewGaleria;
    int SELECT_PHOTO=12345;
    Uri uri;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_galeria, container, false);
        CardView card = root.findViewById(R.id.cardGaleria);
        imageViewGaleria = root.findViewById(R.id.imageViewGaleria);
        textViewGaleria = root.findViewById(R.id.textoResutaldoGaleria);

        card.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent iPhotoPic = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                iPhotoPic.setType("image/");
                startActivityForResult(iPhotoPic.createChooser(iPhotoPic,"Seleccionar Aplicacion"),10);
            }
        });
        return root;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                imageViewGaleria.setImageBitmap(bitmap);

                //procesar imagen
                // Crear objeto FirebaseVisionImage desde bitmap
                FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(bitmap);
                //Instancia de Firebase
                FirebaseVision firebaseVision = FirebaseVision.getInstance();
                // Crear instancia de FirebaseVisionTextRecognizer
                FirebaseVisionTextRecognizer firebaseVisionTextRecognizer = firebaseVision.getOnDeviceTextRecognizer();
                // Crear tarea para procesar imagen
                Task<FirebaseVisionText> task = firebaseVisionTextRecognizer.processImage(firebaseVisionImage);
                //tarea exitosa
                task.addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {

                    @Override
                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                        String s = firebaseVisionText.getText();
                        textViewGaleria.setText(s);
                        if(textViewGaleria != null){
                            Intent intent = new Intent(getActivity(), Resultado.class);
                            intent.putExtra("key",s);
                            startActivity(intent);
                        }
                    }
                    });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}