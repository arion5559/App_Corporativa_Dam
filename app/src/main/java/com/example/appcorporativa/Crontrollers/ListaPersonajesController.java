package com.example.appcorporativa.Crontrollers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.appcorporativa.DatabaseHandlers.DbPersonajes;
import com.example.appcorporativa.HelpingClasses.AdaptadorPersonajes;
import com.example.appcorporativa.Modelo.Personaje;
import com.example.appcorporativa.R;
import com.example.appcorporativa.Static.UsuarioActual;

import java.util.ArrayList;

public class ListaPersonajesController extends AppCompatActivity {
    ListView listaPersonajes;
    ArrayList<Personaje> personajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personajes);

        DbPersonajes dbPersonajes = new DbPersonajes(this);
        personajes = dbPersonajes.mostrarPersonajes(UsuarioActual.getUsuarioactual().getId());
        AdaptadorPersonajes adaptadorPersonajes = new AdaptadorPersonajes(this, personajes);

        listaPersonajes = findViewById(R.id.listViewPersonajes);
        listaPersonajes.setAdapter(adaptadorPersonajes);

        listaPersonajes.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(this, MuestraPersonaje.class);

            intent.putExtra("idPersonaje", personajes.get(i).getId());

            startActivity(intent);
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        menu.findItem(R.id.itemLista).setVisible(true);
        menu.findItem(R.id.optCV).setVisible(true);
        menu.findItem(R.id.optLogOut).setVisible(true);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.itemLista:
                intent = new Intent(this, ListaPersonajesController.class);
                startActivity(intent);
                return true;
            case R.id.optCV:
                intent = new Intent(this, CurriculumController.class);
                startActivity(intent);
                return true;
            case R.id.optLogOut:
                UsuarioActual.setUsuarioactual(null);
                intent = new Intent(this, LogInController.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}