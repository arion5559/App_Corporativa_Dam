package com.example.appcorporativa.Modelo;

public class Personaje {
    private int id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private long almas;
    private int idUsuario;

    public Personaje(int id, String nombre, String descripcion, String imagen, long almas, int idUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.almas = almas;
        this.idUsuario = idUsuario;
    }

    public Personaje(String nombre, String descripcion, String imagen, long almas, int idUsuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.almas = almas;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public long getAlmas() {
        return almas;
    }

    public void setAlmas(long almas) {
        this.almas = almas;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", almas=" + almas +
                '}';
    }
}
