package com.dam2.services;

import java.util.List;

import com.dam2.dao.BibliotecaDao;
import com.dam2.model.Libro;

public class BibliotecaService {
	
	private BibliotecaDao dao;

	public BibliotecaService(BibliotecaDao dao) {
		super();
		this.dao = dao;
	}
	
	public void registrarLibro(Libro libro) {
		dao.registrarLibro(libro);
	}
	
	public List<Libro> mostrarLibroNacionalidad(String nacionalidad) {
		return dao.mostrarLibroNacionalidad(nacionalidad);
	}
	
	public void aplicarDescuentoGenero(String genero, int descuento) {
		dao.aplicarDescuentoGenero(genero, descuento);
	}
	
	public void retirarAutorYLibros(int autorId) {
		dao.retirarAutoryLibros(autorId);
	}

}
