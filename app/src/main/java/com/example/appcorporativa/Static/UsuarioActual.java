package com.example.appcorporativa.Static;

import com.example.appcorporativa.Modelo.Usuario;

public class UsuarioActual {
    private static Usuario usuarioactual;

    public static Usuario getUsuarioactual() {
        return usuarioactual;
    }

    public static void setUsuarioactual(Usuario usuarioactual) {
        UsuarioActual.usuarioactual = usuarioactual;
    }

    public static void resetUsuarioactual() {
        UsuarioActual.usuarioactual = null;
    }

    public static boolean isLogged() {
        return UsuarioActual.usuarioactual != null;
    }

    public static int getId() {
        return UsuarioActual.usuarioactual.getId();
    }

    public static String getNombre() {
        return UsuarioActual.usuarioactual.getNombre();
    }

    public static String getUsername() {
        return UsuarioActual.usuarioactual.getUsername();
    }

    public static String getEmail() {
        return UsuarioActual.usuarioactual.getEmail();
    }
}
