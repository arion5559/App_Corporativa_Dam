package com.example.appcorporativa.DatabaseHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.appcorporativa.Modelo.Personaje;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public void registrarPersonaje(Personaje personaje) {
        try {
            DbHelper dbHelper = new DbHelper(context, "Personajes");
            SQLiteDatabase db = this.getWritableDatabase();
            if (!checkIfPersonajeExistsForThisUser(personaje.getNombre(), personaje.getIdUsuario())) {
                ContentValues values = new ContentValues();
                values.put("nombre", personaje.getNombre());
                values.put("descripcion", personaje.getDescripcion());
                values.put("imagen", personaje.getImagen());
                values.put("almas", personaje.getAlmas());
                values.put("idUsuario", personaje.getIdUsuario());
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

    public ArrayList<Personaje> mostrarPersonajes(int idUsuario) {
        DbHelper dbHelper = new DbHelper(context, "Personajes");
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Personaje> personajes = null;
        Personaje personaje;
        Cursor cursorPersonajes = null;

        try {
            cursorPersonajes = db.rawQuery("SELECT * FROM Personajes WHERE idUsuario = ?", new String[]{String.valueOf(idUsuario)});
            if (cursorPersonajes.moveToFirst()) {
                do {
                    personaje = new Personaje();
                    personaje.setId(cursorPersonajes.getInt(0));
                    personaje.setNombre(cursorPersonajes.getString(1));
                    personaje.setDescripcion(cursorPersonajes.getString(2));
                    personaje.setImagen(cursorPersonajes.getString(3));
                    personaje.setAlmas(cursorPersonajes.getLong(4));
                    personaje.setIdUsuario(cursorPersonajes.getInt(5));
                    personajes.add(personaje);
                } while (cursorPersonajes.moveToNext());
            } else {
                Toast.makeText(context, "No hay personajes registrados", Toast.LENGTH_SHORT).show();
            }
            cursorPersonajes.close();
        } catch (Exception e) {
            Toast.makeText(context, "Error al mostrar los personajes", Toast.LENGTH_SHORT).show();
        }
        return personajes;
    }

    public Personaje mostrarPersonajePorId(int id) {
        DbHelper dbHelper = new DbHelper(context, "Personajes");
        SQLiteDatabase db = this.getWritableDatabase();
        Personaje personaje = null;
        Cursor cursorPersonaje = null;

        try {
            cursorPersonaje = db.rawQuery("SELECT * FROM Personajes WHERE id = ?", new String[]{String.valueOf(id)});
            if (cursorPersonaje.moveToFirst()) {
                personaje = new Personaje();
                personaje.setId(cursorPersonaje.getInt(0));
                personaje.setNombre(cursorPersonaje.getString(1));
                personaje.setDescripcion(cursorPersonaje.getString(2));
                personaje.setImagen(cursorPersonaje.getString(3));
                personaje.setAlmas(cursorPersonaje.getLong(4));
                personaje.setIdUsuario(cursorPersonaje.getInt(5));
            } else {
                Toast.makeText(context, "No hay personajes registrados", Toast.LENGTH_SHORT).show();
            }
            cursorPersonaje.close();
        } catch (Exception e) {
            Toast.makeText(context, "Error al mostrar el personaje", Toast.LENGTH_SHORT).show();
        }
        return personaje;
    }
}
