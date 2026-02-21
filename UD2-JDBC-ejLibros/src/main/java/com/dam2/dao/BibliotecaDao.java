package com.dam2.dao;

import java.util.List;

import com.dam2.model.Libro;

public interface BibliotecaDao {
	
	public void registrarLibro(Libro libro);
	public List<Libro> mostrarLibroNacionalidad(String nacionalidad);
	public void aplicarDescuentoGenero(String genero, int descuento);
	public void retirarAutoryLibros(int autorId);

}
