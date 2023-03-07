package com.example.appcorporativa.Crontrollers;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.example.appcorporativa.DatabaseHandlers.DbHelper;
import com.example.appcorporativa.HelpingClasses.OnSwipeTouchListener;
import com.example.appcorporativa.R;

public class StartController extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        MotionLayout imageView = findViewById(R.id.start);

        imageView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeTop() {
                DbHelper dbHelper = new DbHelper(StartController.this, "Usuarios");
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (db == null) {
                    Toast.makeText(StartController.this, "Error al crear la base de datos", Toast.LENGTH_SHORT).show();
                }
                super.onSwipeTop();
                Intent intent = new Intent(StartController.this, LogInController.class);
                startActivity(intent);
            }
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
