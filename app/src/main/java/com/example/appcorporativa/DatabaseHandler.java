package com.example.appcorporativa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Personajes.db";
    public static final String TABLE_NAME = "personajes_table";
    public static final String[] columnas = {"ID", "NOMBRE", "DESCRIPCION", "IMAGEN", "ALMAS"};

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, DESCRIPCION TEXT, IMAGEN TEXT, ALMAS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertarPersonaje(String nombre, String descripcion, String imagen, long almas) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_NAME + " (NOMBRE, DESCRIPCION, IMAGEN, ALMAS) VALUES ('" + nombre + "', '" + descripcion + "', '" + imagen + "', " + almas + ")");
    }

    public void modificarPersonaje(int id, String nombre, String descripcion, String imagen, long almas) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET NOMBRE = '" + nombre + "', DESCRIPCION = '" + descripcion + "', IMAGEN = '" + imagen + "', ALMAS = " + almas + " WHERE ID = " + id);
    }

    public void eliminarPersonaje(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE ID = " + id);
    }

    public void eliminarPersonajes() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public void mostrarPersonajes() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("SELECT * FROM " + TABLE_NAME);
    }

    public void mostrarPersonajes(String nombre) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("SELECT * FROM " + TABLE_NAME + " WHERE NOMBRE = '" + nombre + "'");
    }

    public void mostrarPersonajes(long almas) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("SELECT * FROM " + TABLE_NAME + " WHERE ALMAS = " + almas);
    }

    public void mostrarPersonaje(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("SELECT * FROM " + TABLE_NAME + " WHERE ID = " + id);
    }

    public void mostrarPersonaje(String nombre) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("SELECT * FROM " + TABLE_NAME + " WHERE NOMBRE = '" + nombre + "'");
    }

    public void mostrarPersonaje(long almas) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("SELECT * FROM " + TABLE_NAME + " WHERE ALMAS = " + almas);
    }
}
