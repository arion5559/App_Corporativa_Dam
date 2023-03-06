package com.example.appcorporativa.DatabaseHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.appcorporativa.Modelo.Usuario;

public class DbUsuarios extends DbHelper {
    Context context;

    public DbUsuarios(Context context) {
        super(context, "Usuarios");
        this.context = context;
    }

    public void registrarUsuario(String nombre, String username, String email, String password) {
        try {
            DbHelper dbHelper = new DbHelper(context, "Usuarios");
            SQLiteDatabase db = this.getWritableDatabase();
            if (checkIfUserExists(username, email)) {
                Toast.makeText(context, "Este usuario ya se encuentra registrado", Toast.LENGTH_SHORT).show();
            } else {
                ContentValues values = new ContentValues();
                values.put("nombre", nombre);
                values.put("username", username);
                values.put("email", email);
                values.put("password", password);
                db.insert("Usuarios", null, values);
                Toast.makeText(context, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkIfUserExists(String username, String email) {
        boolean exists = false;
        SQLiteDatabase db = this.getReadableDatabase();

        if (db.rawQuery("SELECT * FROM Usuarios WHERE username = ? OR email = ?", new String[]{username, email}).moveToFirst()) {
            exists = true;
        }

        return exists;
    }

    public Usuario logIn(String username, String password) {
        Usuario usuario = null;
        SQLiteDatabase db = this.getReadableDatabase();

        if (db.rawQuery("SELECT * FROM Usuarios WHERE username = ? AND password = ?", new String[]{username, password}).moveToFirst()) {
            usuario = new Usuario();
            usuario.setNombre(db.rawQuery("SELECT * FROM Usuarios WHERE username = ? AND password = ?", new String[]{username, password}).getString(1));
            usuario.setUsername(db.rawQuery("SELECT * FROM Usuarios WHERE username = ? AND password = ?", new String[]{username, password}).getString(2));
            usuario.setEmail(db.rawQuery("SELECT * FROM Usuarios WHERE username = ? AND password = ?", new String[]{username, password}).getString(3));
            usuario.setPassword(db.rawQuery("SELECT * FROM Usuarios WHERE username = ? AND password = ?", new String[]{username, password}).getString(4));
        }

        return usuario;
    }
}
