package com.emgs.milupainteligente.ui.camara;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import com.emgs.milupainteligente.R;
import com.emgs.milupainteligente.Resultado;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import static android.Manifest.permission.CAMERA;

public class CamaraFragment extends Fragment {
    ImageView imageView;
    TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_camara, container, false);
        CardView card = root.findViewById(R.id.cardCamara);
        imageView = root.findViewById(R.id.imageViewCamara);
        textView = root.findViewById(R.id.textoResutaldo);

        //verifica permiso de camara
        if (ContextCompat.checkSelfPermission(getActivity(), CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{CAMERA}, 101);
        }

        card.setOnClickListener(v -> {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, 101);
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle bundle = data.getExtras();
        //extraer imagen de bundle
        Bitmap bitmap = (Bitmap) bundle.get("data");
        //Insertar en imageView
        imageView.setImageBitmap(bitmap);
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
                textView.setText(s);
                if(textView != null){
                    Intent intent = new Intent(getActivity(), Resultado.class);
                    intent.putExtra("key",s);
                    startActivity(intent);
                }
            }
        });
    }
}