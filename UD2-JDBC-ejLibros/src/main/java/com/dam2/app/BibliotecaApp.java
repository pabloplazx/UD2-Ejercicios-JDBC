package com.dam2.app;

import java.util.List;

import com.dam2.connect.Conexion;
import com.dam2.dao.BibliotecaDao;
import com.dam2.daoImpl.BibliotecaDaoImpl;
import com.dam2.model.Libro;
import com.dam2.services.BibliotecaService;

import consola.Leer;

public class BibliotecaApp {

	public static void main(String[] args) {
		
		BibliotecaDao dao = null;
		
		dao = new BibliotecaDaoImpl(Conexion.getConnection());
		
		BibliotecaService service = new BibliotecaService(dao);
		
		int op = 0;
		
		do {
			mostrarMenu();
			System.out.print("Introduzca opcion: ");
			op = Leer.datoInt();
			
			switch (op) {
			case 1:
				Libro libro = añadirLibro();
				service.registrarLibro(libro);
				break;
			case 2:
				System.out.print("Introduzca nacionalidad: ");
				String nacionalidad = Leer.dato();
				
				List<Libro> librosNacionalidad = service.mostrarLibroNacionalidad(nacionalidad);
				
				for (Libro l : librosNacionalidad) {
					System.out.println(l);
				}
				break;
			case 3:
				System.out.print("Introduzca genero: ");
				String genero = Leer.dato();
				
				System.out.print("Introduzca precio descontado: ");
				int precioDescontado = Leer.datoInt();
				
				service.aplicarDescuentoGenero(genero, precioDescontado);
				break;
			case 4:
				System.out.println("Id del autor a eliminar: ");
				int idEliminar = Leer.datoInt();
				
				service.retirarAutorYLibros(idEliminar);
				break;
			case 5:
				System.out.println("Saliendo...");
				break;
			case 6:
				System.out.println("Opción no válida");
				
			}
		} while (op != 5);
		
		
	}
	
	private static void mostrarMenu() {
		System.out.println("1. Registrar libro");
		System.out.println("2. Mostrar libro por nacionalidad");
		System.out.println("3. Aplicar descuento a un genero");
		System.out.println("4. Retirar libro y autor");
		System.out.println("5. SALIR");
	}
	
	private static Libro añadirLibro() {
		System.out.print("Titulo: ");
		String titulo = Leer.dato();
		
		System.out.print("Genero: ");
		String genero = Leer.dato();
		
		System.out.print("Precio: ");
		double precio = Leer.datoDouble();
		
		System.out.print("Id del autor: : ");
		int autorId = Leer.datoInt();
		
		Libro libro = new Libro(0, titulo, genero, precio, autorId);
		return libro;
		
	}
}
