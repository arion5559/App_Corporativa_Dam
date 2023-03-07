package com.example.appcorporativa.Crontrollers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcorporativa.DatabaseHandlers.DbUsuarios;
import com.example.appcorporativa.Modelo.Usuario;
import com.example.appcorporativa.R;
import com.example.appcorporativa.Static.UsuarioActual;

public class LogInController extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

        login.setOnClickListener(v -> {
            String username = this.username.getText().toString();
            String password = this.password.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            } else {
                DbUsuarios dbUsuarios = new DbUsuarios(this);
                Usuario usuario = dbUsuarios.logIn(username, password);
                if (usuario != null) {
                    Toast.makeText(this, "Bienvenido " + usuario.getNombre(), Toast.LENGTH_SHORT).show();
                    UsuarioActual.setUsuarioactual(usuario);
                    Intent intent = new Intent(this, ListaPersonajesController.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void limpiarCampos() {
        username.setText("");
        password.setText("");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        menu.findItem(R.id.itemLista).setVisible(false);
        menu.findItem(R.id.optCV).setVisible(false);
        menu.findItem(R.id.optLogOut).setVisible(false);
        return true;
    }
}