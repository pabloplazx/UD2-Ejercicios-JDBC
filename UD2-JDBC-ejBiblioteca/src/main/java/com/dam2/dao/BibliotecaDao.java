package com.dam2.dao;

import java.util.List;

import com.dam2.models.Libro;

public interface BibliotecaDao {
	
	public List<Libro> findAll();
	
	public Libro findById(int id);
	
	public void save(Libro libro);
	
	public void delete(String isbn);
	

}
