package com.example.appcorporativa.Crontrollers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.appcorporativa.DatabaseHandlers.DbPersonajes;
import com.example.appcorporativa.Modelo.Personaje;
import com.example.appcorporativa.R;
import com.example.appcorporativa.Static.UsuarioActual;

import java.io.File;
import java.io.FileOutputStream;

public class RegistrarPersonajeController extends AppCompatActivity {

    String path;
    Button btnGuardar;

    EditText nombre;
    EditText descripcion;
    EditText almas;

    Personaje personaje;
    int idPersonaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_personaje);

        ImageView imagen = findViewById(R.id.img_character_avatar);
        imagen.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/");
            startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 3);
        });
        nombre = findViewById(R.id.input_character_name);
        descripcion = findViewById(R.id.input_character_description);
        almas = findViewById(R.id.input_character_souls);

        btnGuardar = findViewById(R.id.btn_save_character);
        btnGuardar.setOnClickListener(
                view -> {
                    personaje = new Personaje();
                    personaje.setNombre(nombre.getText().toString());
                    personaje.setDescripcion(descripcion.getText().toString());
                    personaje.setAlmas(Integer.parseInt(almas.getText().toString()));
                    personaje.setImagen(path.substring(path.lastIndexOf("/") + 1));
                    personaje.setIdUsuario(UsuarioActual.getUsuarioactual().getId());

                    DbPersonajes dbPersonajes = new DbPersonajes(this);
                    dbPersonajes.registrarPersonaje(personaje);
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3 && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            ImageView imagen = findViewById(R.id.img_character_avatar);
            imagen.setImageURI(selectedImage);
            path = selectedImage.getPath();
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            createDirectoryAndSaveFile(bitmap, path.substring(path.lastIndexOf("/") + 1));
        }
    }

    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {

        File direct = new File(Environment.getExternalStorageDirectory() + "@res/img");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/@res/img/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File("/sdcard/@res/img/", fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}