package com.emgs.milupainteligente;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.emgs.milupainteligente.ui.historial.HistorialFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Resultado extends AppCompatActivity {
    TextView texView;
    TextToSpeech tts;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    public static String date;
    public static String s;
    Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ImageView play = findViewById(R.id.play);
        ImageView copy = findViewById(R.id.copy);


        setSupportActionBar(toolbar);

        texView =findViewById(R.id.textViewResponse);
        button = findViewById(R.id.button);
        calendar= Calendar.getInstance();
        simpleDateFormat =new SimpleDateFormat("dd-MM-yy HH:mm");
        date=simpleDateFormat.format(calendar.getTime());


        Intent intent = getIntent();
        s = intent.getStringExtra("key");
        texView.setText(s);

        Switch tema = findViewById(R.id.switchTema2);

        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            tema.setChecked(true);
        }
        tema.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                restartApp();
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                restartApp();
            }
        });

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    Locale locSpanish = new Locale("spa", "MEX");
                    int resultado = tts.setLanguage(locSpanish);
                    if(resultado == TextToSpeech.LANG_MISSING_DATA || resultado == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","Lenguaje no soportado");
                    }else{
                        play.setEnabled(true);
                        copy.setEnabled(true);
                    }
                }else{
                    Log.e("TTS","Inicializacion fallida");
                }
            }
        });
        //boton agregar a lista
        button.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                texView.setText(date+ " " + s);
                //aqui se manda a la lista historial
                FragmentManager fm = getSupportFragmentManager();
                HistorialFragment fragment = new HistorialFragment();
                fm.beginTransaction().add(R.id.nav_host_fragment,fragment).commit();
            }
        });

    }

    public void restartApp(){
        Intent i = new Intent(getApplicationContext(), Resultado.class);
        startActivity(i);
        finish();
    }

    public void reproducir(View view) {
        speak();
    }

    private void speak() {
        String text = texView.getText().toString();
        tts.speak(text,TextToSpeech.QUEUE_FLUSH, null);
    }

    public void copiar(View view) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("TextView",texView.getText().toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "TEXTO COPIADO!", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy() {
        if(tts != null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    public String getDate(){
            return date;
    }
    public String getS() {
        return s;
    }
}
