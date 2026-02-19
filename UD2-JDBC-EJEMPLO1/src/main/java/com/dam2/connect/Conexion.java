package com.dam2.connect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.dam2.app.VentasApp;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Conexion {
	
	//CLASE SINGLETON PARA OBTENER LA CONEXIÓN;
	
	static Connection con = null;
	
	static DataSource pool = null;
	
	public static Connection getConnection() {
		if(con == null) {
			Properties prop = new Properties();
			try {
				prop.load(Conexion.class.getClassLoader().getResourceAsStream("bd.properties"));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			con = null;
			try {
				con = DriverManager
						.getConnection(prop.getProperty("url"),
								prop.getProperty("user"),
								prop.getProperty("passwd"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return con;
	}
	
	public static DataSource getPool(){
		
		
		if(pool == null) {
			
			Properties prop = new Properties();
			try {
				prop.load(Conexion.class.getClassLoader().getResourceAsStream("bd.properties"));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(prop.getProperty("url"));
			config.setUsername(prop.getProperty("user"));
			config.setPassword(prop.getProperty("passwd"));
			config.setMaximumPoolSize(10);      // máximo de conexiones
			config.setMinimumIdle(2);           // conexiones mínimas a la espera
			config.setConnectionTimeout(30000); // tiempo máx esperando conexión

			pool = new HikariDataSource(config);
		}
		return pool;
		
	}

}
