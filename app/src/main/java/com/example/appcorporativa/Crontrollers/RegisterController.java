package com.example.appcorporativa.Crontrollers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcorporativa.DatabaseHandlers.DbHelper;
import com.example.appcorporativa.DatabaseHandlers.DbUsuarios;
import com.example.appcorporativa.Modelo.Usuario;
import com.example.appcorporativa.R;

public class RegisterController extends AppCompatActivity {

    private EditText nombre;
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText password2;
    private Button registrar;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = findViewById(R.id.et_name);
        username = findViewById(R.id.et_username);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        password2 = findViewById(R.id.et_confirm_password);
        registrar = findViewById(R.id.btn_register);
        login = findViewById(R.id.btn_login);

        registrar.setOnClickListener(v -> {
            String nombre = this.nombre.getText().toString();
            String username = this.username.getText().toString();
            String email = this.email.getText().toString();
            String password = this.password.getText().toString();
            String password2 = this.password2.getText().toString();

            if (nombre.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(password2)) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            } else {
                DbUsuarios dbUsuarios = new DbUsuarios(this);
                dbUsuarios.registrarUsuario(nombre, username, email, password);
                Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LogInController.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(v -> {
            Intent intent = new Intent(this, LogInController.class);
            startActivity(intent);
        });
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