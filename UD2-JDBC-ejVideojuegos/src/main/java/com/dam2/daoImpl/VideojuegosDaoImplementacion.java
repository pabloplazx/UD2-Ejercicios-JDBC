package com.dam2.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dam2.dao.VideojuegosDao;
import com.dam2.model.Videojuego;

public class VideojuegosDaoImplementacion implements VideojuegosDao{
	
	Connection connection;

	public VideojuegosDaoImplementacion(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public void save(Videojuego videojuego) {
		try (Statement sentencia  = connection.createStatement()) {
			String sql = "INSERT INTO juegos(id, titulo, plataforma, precio, genero) "
					+ "VALUES ('" + videojuego.getId()+"','"+videojuego.getTitulo()+"','"
					+ videojuego.getPlataforma() +"','" + videojuego.getPrecio()+"','" + videojuego.getGenero()+"')";
			sentencia.executeUpdate(sql);	
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public List<Videojuego> findAll() {
		List<Videojuego> videojuegos = new ArrayList<>();
		
		try (Statement sentencia = connection.createStatement()) {
			
			String sql = "SELECT * FROM juegos";
			ResultSet registros = sentencia.executeQuery(sql);
			
			while (registros.next()) {
				
				Videojuego v = new Videojuego();
				
				v.setId(registros.getInt("id"));
				v.setTitulo(registros.getString("titulo"));
				v.setPlataforma(registros.getString("plataforma"));
				v.setPrecio(registros.getDouble("precio"));
				v.setGenero(registros.getString("genero"));
				
				videojuegos.add(v);
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return videojuegos;
	}

	@Override
	public Videojuego findById(int id) {
		Videojuego videojuego = new Videojuego();
		
		String sql = "SELECT * FROM juegos WHERE id = "+id;
		
		try (Statement sentencia = connection.createStatement()){
			ResultSet registros = sentencia.executeQuery(sql);
			
			while (registros.next()) {
				videojuego.setId(registros.getInt("id"));
				videojuego.setTitulo(registros.getString("titulo"));
				videojuego.setPlataforma(registros.getString("plataforma"));
				videojuego.setPrecio(registros.getDouble("precio"));
				videojuego.setGenero(registros.getString("genero"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return videojuego;
	}

	@Override
	public void deleteById(int id) {
		
		//1. Preparamos el SQL
		String sql = "DELETE FROM juegos WHERE id = "+id;
		
		try (Statement sentencia  = connection.createStatement()) {
			
			//2. Ejecutamos la orden
			//Fijate que usamos executeUpdate, No executeQuery
			int filasAfectadas = sentencia.executeUpdate(sql);
			
			//3. (Opcional) Podemos coprobar si borró algo
			if (filasAfectadas > 0) {
				System.out.println("Juego borrado con éxito");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
