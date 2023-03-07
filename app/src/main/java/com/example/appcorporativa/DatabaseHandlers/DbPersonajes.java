package com.example.appcorporativa.DatabaseHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DbPersonajes extends DbHelper {
    private Context context;

    public DbPersonajes(Context context) {
        super(context, "Personajes");
        this.context = context;
    }

    public void registrarPersonaje(String nombre, String descripcion, String imagen, long almas, int idUsuario) {
        try {
            DbHelper dbHelper = new DbHelper(context, "Personajes");
            SQLiteDatabase db = this.getWritableDatabase();
            if (!checkIfPersonajeExistsForThisUser(nombre, idUsuario)) {
                ContentValues values = new ContentValues();
                values.put("nombre", nombre);
                values.put("descripcion", descripcion);
                values.put("imagen", imagen);
                values.put("almas", almas);
                values.put("idUsuario", idUsuario);
                db.insert("Personajes", null, values);
                Toast.makeText(context, "Personaje registrado con éxito", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Este personaje ya se encuentra registrado", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al registrar el personaje", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkIfPersonajeExistsForThisUser(String nombre, int idUsuario) {
        try {
            DbHelper dbHelper = new DbHelper(context, "Personajes");
            SQLiteDatabase db = this.getWritableDatabase();
            return db.rawQuery("SELECT * FROM Personajes WHERE nombre = ? AND idUsuario = ?", new String[]{nombre, String.valueOf(idUsuario)}).moveToFirst();
        } catch (Exception e) {
            Toast.makeText(context, "Error al verificar si el personaje existe", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void actualizarPersonaje(String nombre, String descripcion, String imagen, long almas, int idUsuario) {
        try {
            DbHelper dbHelper = new DbHelper(context, "Personajes");
            SQLiteDatabase db = this.getWritableDatabase();
            if (checkIfPersonajeExistsForThisUser(nombre, idUsuario)) {
                ContentValues values = new ContentValues();
                values.put("nombre", nombre);
                values.put("descripcion", descripcion);
                values.put("imagen", imagen);
                values.put("almas", almas);
                db.update("Personajes", values, "nombre = ? AND idUsuario = ?", new String[]{nombre, String.valueOf(idUsuario)});
                Toast.makeText(context, "Personaje actualizado con éxito", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Este personaje no se encuentra registrado", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al actualizar el personaje", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarPersonaje(String nombre, int idUsuario) {
        try {
            DbHelper dbHelper = new DbHelper(context, "Personajes");
            SQLiteDatabase db = this.getWritableDatabase();
            if (checkIfPersonajeExistsForThisUser(nombre, idUsuario)) {
                db.delete("Personajes", "nombre = ? AND idUsuario = ?", new String[]{nombre, String.valueOf(idUsuario)});
                Toast.makeText(context, "Personaje eliminado con éxito", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Este personaje no se encuentra registrado", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al eliminar el personaje", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarPersonajes(int idUsuario) {
        try {
            DbHelper dbHelper = new DbHelper(context, "Personajes");
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("Personajes", "idUsuario = ?", new String[]{String.valueOf(idUsuario)});
            Toast.makeText(context, "Personajes eliminados con éxito", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al eliminar los personajes", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarPersonajePorId(int id) {
        try {
            DbHelper dbHelper = new DbHelper(context, "Personajes");
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("Personajes", "id = ?", new String[]{String.valueOf(id)});
            Toast.makeText(context, "Personaje eliminado con éxito", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al eliminar el personaje", Toast.LENGTH_SHORT).show();
        }
    }
}
