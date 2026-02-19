package com.dam2.model;

import java.util.Objects;

public class Videojuego {
	
	private int id;
	private String titulo;
	private String plataforma;
	private double precio;
	private String genero;
	public Videojuego(int id, String titulo, String plataforma, double precio, String genero) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.plataforma = plataforma;
		this.precio = precio;
		this.genero = genero;
	}
	
	public Videojuego() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	@Override
	public int hashCode() {
		return Objects.hash(genero, id, plataforma, precio, titulo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Videojuego other = (Videojuego) obj;
		return Objects.equals(genero, other.genero) && id == other.id && Objects.equals(plataforma, other.plataforma)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(titulo, other.titulo);
	}
	@Override
	public String toString() {
		return "Videojuego [id=" + id + ", titulo=" + titulo + ", plataforma=" + plataforma + ", precio=" + precio
				+ ", genero=" + genero + "]";
	}
	
	

}
