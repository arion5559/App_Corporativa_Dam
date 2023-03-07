package com.example.appcorporativa.Crontrollers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appcorporativa.Modelo.Personaje;
import com.example.appcorporativa.R;

public class MuestraPersonaje extends AppCompatActivity {
    Personaje personaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_personaje);

        personaje = (Personaje) getIntent().getSerializableExtra("idPersonaje");

    }
}