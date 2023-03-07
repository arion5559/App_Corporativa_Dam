package com.example.appcorporativa.Crontrollers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.appcorporativa.R;
import com.example.appcorporativa.Static.UsuarioActual;

public class ListaPersonajesController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personajes);
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