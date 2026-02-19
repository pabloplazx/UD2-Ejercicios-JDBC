package com.dam2.model;

public class Heroe {
    private int id;
    private String nombre;
    private String clase;
    private int nivel;
    private int gremioId;

    public Heroe() {}

    public Heroe(int id, String nombre, String clase, int nivel, int gremioId) {
        this.id = id;
        this.nombre = nombre;
        this.clase = clase;
        this.nivel = nivel;
        this.gremioId = gremioId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getClase() { return clase; }
    public void setClase(String clase) { this.clase = clase; }
    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }
    public int getGremioId() { return gremioId; }
    public void setGremioId(int gremioId) { this.gremioId = gremioId; }

    @Override
    public String toString() {
        return "Heroe [id=" + id + ", nombre=" + nombre + ", clase=" + clase + ", nivel=" + nivel + ", gremioId=" + gremioId + "]";
    }
}