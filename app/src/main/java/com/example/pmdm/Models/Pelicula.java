package com.example.pmdm.Models;

/**
 * Modelo Clase Pelicula - Se almacenará la información de cada película mostrada en la lista
 */
public class Pelicula {


    private String titulo;
    private String descripcion;
    private int    imagenId;


    public Pelicula(String titulo, String descripcion, int imagenId) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagenId = imagenId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }
}
