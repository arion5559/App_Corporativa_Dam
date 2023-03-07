package com.example.appcorporativa.DatabaseHandlers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "appcorporativa.db";
    public static String TABLE_NAME = "";

    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, String table_name) {
        super(context, name, factory, version);
        this.TABLE_NAME = table_name;
    }

    public DbHelper(@Nullable Context context, String table_name) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.TABLE_NAME = table_name;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        if (TABLE_NAME.equals("Usuarios")) {
            sqLiteDatabase.execSQL("CREATE TABLE Usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, username TEXT NOT NULL, email TEXT, password TEXT NOT NULL)");
        } else if (TABLE_NAME.equals("Personajes")) {
            sqLiteDatabase.execSQL("CREATE TABLE Personajes (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, descripcion TEXT NOT NULL, " +
                    "imagen TEXT NOT NULL, id_usuario INTEGER NOT NULL, FOREIGN KEY (id_usuario) REFERENCES Usuarios(id))");
        }
    }
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
