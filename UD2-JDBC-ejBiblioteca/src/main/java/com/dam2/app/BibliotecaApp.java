package com.dam2.app;

import java.sql.SQLException;
import java.util.List;

import com.dam2.connect.Conexion;
import com.dam2.dao.BibliotecaDao;
import com.dam2.daoImpl.BibliotecaDaoImplement;
import com.dam2.models.Libro;
import com.dam2.services.BibliotecaServices;

import consola.Leer;

public class BibliotecaApp {
	
	public static void main(String[] args) {
		
		BibliotecaDao dao = null;
		try {
			dao = new BibliotecaDaoImplement(Conexion.getPool().getConnection());
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		BibliotecaServices service = new BibliotecaServices(dao);
		
		int op = 0;
		
		do {
			mostrarMenu();
			System.out.print("Introduzca opcion: ");
			op = Leer.entero();
			
			switch (op) {
			case 1:
				List<Libro> libros = service.buscarLibros();
				
				for (Libro libro : libros) {
					System.out.println(libro);
				}
				break;
			case 2: 
				System.out.print("Introduzca id para buscar: ");
				int idBuscar = Leer.entero();
				
				Libro libro = service.buscarLibroId(idBuscar);
				
				System.out.println(libro);
				break;
			case 3:
			    // 1. ¡BORRA ESTO! No le pidas el ID al usuario
			    // System.out.print("Id: ");
			    // int id = Leer.entero();
			    
			    System.out.print("Titulo: ");
			    String titulo = Leer.linea();
			    
			    System.out.print("Isbn: ");
			    String isbn = Leer.linea();
			    
			    System.out.print("Precio: ");
			    double precio = Leer.decimal();
			    
			    System.out.print("Id del autor: ");
			    int idAutor = Leer.entero();
			    
			    // 2. Pasamos un 0 como primer argumento.
			    // Ese 0 significa "Todavía no tengo ID oficial".
			    Libro libroAñadir = new Libro(0, titulo, isbn, precio, idAutor);
			    
			    service.añadirLibro(libroAñadir);
			    System.out.println("Libro enviado a guardar. La base de datos le asignará su ID.");
			    break;
			case 4:
				System.out.println("Introduzca isbn del libro que quiere eliminar: ");
				String isbnEliminar = Leer.linea();
				
				service.borrarLibro(isbnEliminar);
				break;
			case 5:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida");
			}
		} while (op != 5);
		
	}
	
	private static void mostrarMenu() {
		System.out.println("1. Mostrar todos los libros");
		System.out.println("2. Buscar libro por id: ");
		System.out.println("3. Añadir un libro a la base de datos");
		System.out.println("4. Borrar un libro de la base de datos, con el isbn");
		System.out.println("5. SALIR");
	}

}
