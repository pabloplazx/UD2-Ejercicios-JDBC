package com.dam2.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dam2.dao.BibliotecaDao;
import com.dam2.model.Libro;

public class BibliotecaDaoImpl implements BibliotecaDao{
	
	Connection connection;

	public BibliotecaDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void registrarLibro(Libro libro) {
	    String sql = "INSERT INTO libros (titulo, genero, precio, autor_id) VALUES (?, ?, ?, ?)";
	    
	    try (PreparedStatement sentencia = connection.prepareStatement(sql)) {
	        sentencia.setString(1, libro.getTitulo());
	        sentencia.setString(2, libro.getGenero());
	        sentencia.setDouble(3, libro.getPrecio());
	        
	        if (libro.getAutorId() == 0) {
	            sentencia.setNull(4, java.sql.Types.INTEGER); 
	        } else {
	            sentencia.setInt(4, libro.getAutorId());
	        }
	        
	        int filas = sentencia.executeUpdate(); 
	        if (filas > 0) {
	            System.out.println("Libro añadido correctamente");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al registrar el libro: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	@Override
	public List<Libro> mostrarLibroNacionalidad(String nacionalidad) {
		List<Libro> librosNacionalidad = new ArrayList<>();
		
		// ¡CORREGIDO! Fíjate en las comillas simples rodeando la variable nacionalidad
		String sql = "SELECT l.id, l.titulo, l.genero, l.precio, l.autor_id \r\n"
				+ "FROM libros l\r\n"
				+ "INNER JOIN autores a ON l.autor_id = a.id\r\n"
				+ "WHERE a.nacionalidad = '" + nacionalidad + "'";
		
		try (Statement sentencia = connection.createStatement()) {
			ResultSet resultados = sentencia.executeQuery(sql);
			
			while (resultados.next()) {
				Libro libro = new Libro();
				libro.setLibroId(resultados.getInt("id"));
				libro.setTitulo(resultados.getString("titulo"));
				libro.setGenero(resultados.getString("genero"));
				libro.setPrecio(resultados.getDouble("precio"));
				libro.setAutorId(resultados.getInt("autor_id"));
				
				librosNacionalidad.add(libro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return librosNacionalidad;
	}

	@Override
	public void aplicarDescuentoGenero(String genero, int descuento) {
		// ¡CORREGIDO! Se resta el precio y se añaden las comillas simples al género
		String sql = "UPDATE libros SET precio = precio - " + descuento + " WHERE genero = '" + genero + "'";
		
		try (Statement sentencia = connection.createStatement()) {
			int filasAfectadas = sentencia.executeUpdate(sql);
			
			if (filasAfectadas > 0) {
				System.out.println("Se han rebajado " + filasAfectadas + " libros.");
			} else {
				System.out.println("No se ha encontrado el género especificado.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void retirarAutoryLibros(int autorId) {
		// ¡CORREGIDO! Nombres de variables y columnas correctas
		String sqlLibros = "DELETE FROM libros WHERE autor_id = " + autorId;
		String sqlAutor = "DELETE FROM autores WHERE id = " + autorId; 

		

		try {
			connection.setAutoCommit(false); // Iniciamos transacción manual

			try (Statement sentencia = connection.createStatement()) {
				
				// 1. Borramos los libros primero (Clave Foránea)
				int librosBorrados = sentencia.executeUpdate(sqlLibros);

				// 2. Luego borramos el autor
				int autorBorrado = sentencia.executeUpdate(sqlAutor);

				if (autorBorrado > 0) {
					connection.commit(); // Todo fue bien, guardamos
					System.out.println("Se ha borrado el autor y sus " + librosBorrados + " libros.");
				} else {
					System.out.println("No se encontró el autor.");
					connection.rollback(); 
				}
			}
		} catch (SQLException e) {
			System.out.println("Error fatal. Haciendo Rollback... " + e.getMessage());
			try {
				connection.rollback(); // Si hay fallo, deshacemos
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				connection.setAutoCommit(true); // Devolvemos el control automático
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}