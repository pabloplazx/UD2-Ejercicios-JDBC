package com.dam2.services;

import java.util.List;

import com.dam2.dao.VideojuegosDao;
import com.dam2.model.Videojuego;

public class VideojuegoServices {
	
	VideojuegosDao dao;

	public VideojuegoServices(VideojuegosDao dao) {
		super();
		this.dao = dao;
	}
	
	public void crearJuego(Videojuego videojuego) {
		dao.save(videojuego);
	}
	
	public List<Videojuego> verJuegos() {
		
		List<Videojuego> videojuegos = dao.findAll();
		
		return videojuegos;
	}
	
	public Videojuego verVideojuego(int id) {
		
		Videojuego videojuego = dao.findById(id);
		
		return videojuego;
	}
	
	public void borrarId(int id) {
		dao.deleteById(id);
	}

}
