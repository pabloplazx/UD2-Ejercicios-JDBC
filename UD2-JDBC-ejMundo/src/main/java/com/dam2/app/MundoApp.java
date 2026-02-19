package com.dam2.app;

import java.util.List;

import com.dam2.connect.Conexion;
import com.dam2.dao.MundoDao;
import com.dam2.daoImpl.MundoDaoImpl;
import com.dam2.model.Heroe;
import com.dam2.services.MundoService;

import consola.Leer;

public class MundoApp {
	
	public static void main(String[] args) {
		MundoDao dao = null;
		
		dao = new MundoDaoImpl(Conexion.getConnection());
		
		MundoService service = new MundoService(dao);
		
		int op = 0;
		
		do {
			mostrarMenu();
			System.out.print("Introduzca opcion: ");
			op = Leer.datoInt();
			
			switch (op) {
			case 1: 
				Heroe heroe = añadirHeroe();
				service.reclutarHeroe(heroe);
				break;
			case 2:
				System.out.print("Introduzca nombre del gremio: ");
				String nombreGremio = Leer.dato();
				List<Heroe> heroesGremio = service.mostrarHeroesGremio(nombreGremio);
				
				for (Heroe h : heroesGremio) {
					System.out.println(h);
				}
				break;
			case 3:
				System.out.print("Id del gremio: ");
				int idGremio = Leer.datoInt();
				
				System.out.print("Cuantos niveles subimos?: ");
				int nivelesSubir = Leer.datoInt();
				
				service.subirNiverGremio(idGremio, nivelesSubir);
				break;
			
			case 4:
				System.out.print("Introduzca Id del gremio que desea disolver: ");
				int idGremioDisolver = Leer.datoInt();
				
				service.disolverGremio(idGremioDisolver);
				break;
			case 5:
				System.out.println("Saliendo...");
				break;
			}
		} while (op != 5);
	}
	
	private static void mostrarMenu() {
		System.out.println("1. Reclutar heroe");
		System.out.println("2. Mostrar heroes por gremio");
		System.out.println("3. Subir nivel gremio");
		System.out.println("4. Disolver gremio");
		System.out.println("5. SALIR");
	}
	
	private static Heroe añadirHeroe() {
		System.out.print("Nombre: ");
		String nombre = Leer.dato();
		
		System.out.print("Clase: ");
		String clase = Leer.dato();
		
		System.out.println("Nivel: ");
		int nivel = Leer.datoInt();
		
		System.out.println("Id gremio: ");
		int idGremio = Leer.datoInt();
		
		Heroe heroe = new Heroe(0, nombre, clase, nivel, idGremio);
		
		return heroe;
	}

}