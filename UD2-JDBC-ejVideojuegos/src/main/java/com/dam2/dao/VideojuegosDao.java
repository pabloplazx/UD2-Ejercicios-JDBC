package com.dam2.dao;

import java.util.List;

import com.dam2.model.Videojuego;

public interface VideojuegosDao {
	
	//1. CREAR: Guadar un juego nuevo en la base de datos
	public void save(Videojuego videojuego);
	
	//2. READ (Lista): Recuperar TODOS los juegos
	public List<Videojuego> findAll();
	
	//3. READ (Uno): Buscar un juego por su ID
	public Videojuego findById(int id);
	
	//4. DELETE: Borrar un juego por su ID
	void deleteById(int id);
	
	

}
