package com.dam2.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dam2.dao.BibliotecaDao;
import com.dam2.models.Libro;

public class BibliotecaDaoImplement implements BibliotecaDao{
	
	Connection conection;
	

	public BibliotecaDaoImplement(Connection conection) {
		super();
		this.conection = conection;
	}

	@Override
	public List<Libro> findAll() {
		
		List<Libro> libros = new ArrayList<>();
		
		try (Statement sentencia = conection.createStatement()){
			String sql = "SELECT * FROM libros";
			
			ResultSet registros = sentencia.executeQuery(sql);
			
			while (registros.next()) {
				Libro libro = new Libro();
				
				libro.setId(registros.getInt("id"));
				libro.setTitulo(registros.getString("titulo"));
				libro.setIsbn(registros.getString("isbn"));
				libro.setPrecio(registros.getDouble("precio"));
				libro.setAutorId(registros.getInt("autor_id"));
				
				libros.add(libro);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libros;
	}

	@Override
	public Libro findById(int id) {
		
		Libro libro = new Libro();
		try (Statement sentencia = conection.createStatement()) {
			String sql = "SELECT * FROM libros WHERE id ="+id;
			
			ResultSet registros = sentencia.executeQuery(sql);
			
			while (registros.next()) {
				libro.setId(registros.getInt("id"));
				libro.setTitulo(registros.getString("titulo"));
				libro.setIsbn(registros.getString("isbn"));
				libro.setPrecio(registros.getDouble("precio"));
				libro.setAutorId(registros.getInt("autor_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libro;
	}

	@Override
	public void save(Libro libro) {
	    
	    // Usamos executeUpdate para INSERT, UPDATE o DELETE
	    try (Statement sentencia = conection.createStatement()) {
	        
	        // 1. Quitamos el 'id' (MySQL lo pone solo).
	        // 2. Quitamos las comillas simples ' ' en precio y autor_id (son números).
	        // 3. Cerramos bien el paréntesis final.
	        String sql = "INSERT INTO libros(titulo, isbn, precio, autor_id) "
	                + "VALUES ('" + libro.getTitulo() + "','" 
	                + libro.getIsbn() + "'," // Coma, sin comilla de apertura siguiente
	                + libro.getPrecio() + "," // Sin comillas
	                + libro.getAutorId() + ")"; // Sin comillas y cerramos paréntesis
	        
	        // Para verificar qué estás mandando, descomenta esto:
	        System.out.println("SQL Generado: " + sql);

	        sentencia.executeUpdate(sql); // Usamos executeUpdate
	        
	        System.out.println("Libro guardado correctamente");
	        
	    } catch (SQLException e) {
	        System.out.println("Error al guardar el libro:");
	        e.printStackTrace();
	    }
	}

	@Override
	public void delete(String isbn) {
		// TODO Auto-generated method stub
		
		String sql = "DELETE FROM libros WHERE isbn = '"+isbn+"'";
		
		try (Statement sentencia = conection.createStatement()) {
			
			int filasAfectadas = sentencia.executeUpdate(sql);
			
			if (filasAfectadas > 0	) {
				System.out.println("El libro con ibsn "+isbn+" ha sido borrado");
			} else {
				System.out.println("No se ha borrado nada ");
			}
		} catch (SQLException e) {
			System.out.println("Error al intentar ");
			e.printStackTrace();
		}
		
	}

}
