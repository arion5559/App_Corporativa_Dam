package com.example.appcorporativa.HelpingClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcorporativa.Modelo.Personaje;
import com.example.appcorporativa.R;

import java.util.ArrayList;

public class AdaptadorPersonajes extends BaseAdapter {

    private final Context context;
    private final ArrayList<Personaje> personajes;

    public AdaptadorPersonajes(Context context, ArrayList<Personaje> personajes) {
        this.context = context;
        this.personajes = personajes;
    }


    @Override
    public int getCount() {
        return personajes.size();
    }

    @Override
    public Object getItem(int i) {
        return personajes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return personajes.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.card_personaje, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Personaje personaje = personajes.get(i);
        int imgId = context.getResources().getIdentifier(personaje.getImagen(), "drawable", context.getPackageName());
        holder.imagen.setImageResource(imgId);
        holder.nombre.setText(personaje.getNombre());
        holder.almas.setText((int) personaje.getAlmas());

        return view;
    }

    private static class ViewHolder {
        private final ImageView imagen;
        private final TextView nombre;
        private final TextView almas;

        public ViewHolder(View view) {
            imagen = view.findViewById(R.id.character_image);
            nombre = view.findViewById(R.id.character_name);
            almas = view.findViewById(R.id.character_souls);
        }
    }
}
