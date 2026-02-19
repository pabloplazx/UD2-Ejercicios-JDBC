package com.dam2.model;

public class Gremio {
    private int id;
    private String nombre;
    private int tesoroOro;

    public Gremio() {}

    public Gremio(int id, String nombre, int tesoroOro) {
        this.id = id;
        this.nombre = nombre;
        this.tesoroOro = tesoroOro;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getTesoroOro() { return tesoroOro; }
    public void setTesoroOro(int tesoroOro) { this.tesoroOro = tesoroOro; }

    @Override
    public String toString() {
        return "Gremio [id=" + id + ", nombre=" + nombre + ", tesoroOro=" + tesoroOro + "]";
    }
}