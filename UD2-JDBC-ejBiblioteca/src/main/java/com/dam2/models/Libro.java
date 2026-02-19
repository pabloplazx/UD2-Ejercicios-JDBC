package com.dam2.models;

import java.util.Objects;

public class Libro {
	
	private int id;
	private String titulo;
	private String isbn;
	private double precio;
	private int autorId;
	public Libro() {
		super();
	}
	public Libro(int id, String titulo, String isbn, double precio, int autorId) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.isbn = isbn;
		this.precio = precio;
		this.autorId = autorId;
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
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
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
	public int hashCode() {
		return Objects.hash(autorId, id, isbn, precio, titulo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return autorId == other.autorId && id == other.id && Objects.equals(isbn, other.isbn)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(titulo, other.titulo);
	}
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", isbn=" + isbn + ", precio=" + precio + ", autorId="
				+ autorId + "]";
	}
	
	

}
