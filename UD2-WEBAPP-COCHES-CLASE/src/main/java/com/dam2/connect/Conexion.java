package com.dam2.connect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;


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
			
			//LA SENTENCIA SIGUIENTE ES LA DE REGISTRO DEL DRIVER
			//REGISTRAR EL DRIVER SIGNIFICA AÑADIR UNA INSTANCIA
			//DEL DRIVER A DRIVERMANAGER
			//EN LOS DRIVERS MODERNOS SE HACE SOLO LA PRIMERA VEZ QUE
			//SE USA DRIVERMANAGER
			//PERO EN NUESTRA APPWEB TENEMOS DOS CONTEXTOS DE CARGA DE OBJETOS
			//(CLASSLOADER) EL DE LA APP Y EL DE TOMCAT. SI NO LO PONEMOS SOLO
			//SE HA REGISTRADO EN LA APP, LO TENEMOS QUE REGISTRAR TAMBIÉN
			//EN LA DEL TOMCAT
			//TOMCAT ARRANCA ANTES QUE LA APP. Y CARGA DRIVERMANAGER. 
			//LUEGO ARRANCA LA APP, Y CARGA EL DRIVER EN EL CONTEXTO DE OBJETOS
			//DE LA APP
			//PERO DRIVERMANAGER ESTÁ VIVIENDO EN EL CONTEXTO DE TOMCAT, LUEGO NO ENCUENTRA
			//LO QUE SE HA CARGADO EN LA APP, HAY QUE FORZAR EL REGISTRO
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
