package com.dam2.app;

import java.sql.SQLException;
import java.util.List;

import com.dam2.connect.Conexion;
import com.dam2.dao.VideojuegosDao;
import com.dam2.daoImpl.VideojuegosDaoImplementacion;
import com.dam2.model.Videojuego;
import com.dam2.services.VideojuegoServices;

import consola.Leer;

public class VideojuegosApp {

	public static void main(String[] args) {
		
		VideojuegosDao dao = null;
		try {
			dao = new VideojuegosDaoImplementacion(Conexion.getPool().getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		VideojuegoServices servicios = new VideojuegoServices(dao);
		
		int op = 0;
		
		do {
			mostrarMenu();
			System.out.print("Introduzca opcion: ");
			op = Leer.datoInt();
			
			switch (op) {
			case 1:
				System.out.println("Introduzca los datos de su videojuego");
				System.out.print("Id: ");
				int id = Leer.datoInt();
				
				System.out.print("Titulo: ");
				String titulo = Leer.dato();
				
				System.out.print("Plataforma: ");
				String plataforma = Leer.dato();
				
				System.out.print("Precio: ");
				double precio = Leer.datoDouble();
				
				System.out.print("Genero: ");
				String genero = Leer.dato();
				
				Videojuego videojuego = new Videojuego(id, titulo, plataforma, precio, genero);
				servicios.crearJuego(videojuego);
				
				System.out.println("Videojuego creado");
				break;
			case 2:
				List<Videojuego> videojuegos = servicios.verJuegos();
				
				for (Videojuego v : videojuegos) {
					System.out.println(v);
				}
				
				break;
			case 3:
				System.out.print("Introduzca id para buscar: ");
				int idBuscar = Leer.datoInt();
				
				Videojuego videojuegoBuscado = servicios.verVideojuego(idBuscar);
				
				System.out.println(videojuegoBuscado);
				
				break;
			case 4:
				System.out.println("Id borrado :");
				int idBorrar = Leer.datoInt();
				
				servicios.borrarId(idBorrar);

				break;
			case 5:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opcion no valida");
				
				
			}
		} while (op != 5);
		
	}
	
	private static void mostrarMenu() {
		System.out.println("1. Guardar un juego nuevo en la base de datos");
		System.out.println("2. Recuperar todos los juegos");
		System.out.println("3. Buscar un juego por id");
		System.out.println("4. Borrar un juego por su id");
		System.out.println("5. SALIR");
	}
}
