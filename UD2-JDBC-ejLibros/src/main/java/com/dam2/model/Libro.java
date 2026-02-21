package com.dam2.model;

public class Libro {

	private int libroId;
	private String titulo;
	private String genero;
	private double precio;
	private int autorId;
	public Libro() {
		super();
	}
	public Libro(int libroId, String titulo, String genero, double precio, int autorId) {
		super();
		this.libroId = libroId;
		this.titulo = titulo;
		this.genero = genero;
		this.precio = precio;
		this.autorId = autorId;
	}
	public int getLibroId() {
		return libroId;
	}
	public void setLibroId(int libroId) {
		this.libroId = libroId;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getAutorId() {
		return autorId;
	}
	public void setAutorId(int autorId) {
		this.autorId = autorId;
	}
	@Override
	public String toString() {
		return "Libro [libroId=" + libroId + ", titulo=" + titulo + ", genero=" + genero + ", precio=" + precio
				+ ", autorId=" + autorId + "]";
	}
	
	
	
}
