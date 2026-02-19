package com.dam2.services;

import java.util.List;

import com.dam2.dao.BibliotecaDao;
import com.dam2.models.Libro;

public class BibliotecaServices {
	
	BibliotecaDao dao;
	
	
	public BibliotecaServices(BibliotecaDao dao) {
		super();
		this.dao = dao;
	}

	public List<Libro> buscarLibros() {
		return dao.findAll();
	}
	
	public Libro buscarLibroId(int id) {
		return dao.findById(id);
	}
	
	public void añadirLibro(Libro libro) {
		dao.save(libro);
	}
	
	public void borrarLibro(String isbn) {
		dao.delete(isbn);
	}

}
