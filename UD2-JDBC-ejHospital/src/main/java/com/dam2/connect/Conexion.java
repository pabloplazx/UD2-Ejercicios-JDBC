package com.dam2.connect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

    // Singleton simple: Una única conexión para toda la app
    private static Connection con = null;

    public static Connection getConnection() {
        
        // Si la conexión no existe o se ha cerrado, creamos una nueva
        try {
            if (con == null || con.isClosed()) {
                
                Properties prop = new Properties();
                // Cargamos el fichero de propiedades
                try {
                    prop.load(Conexion.class.getClassLoader().getResourceAsStream("bd.properties"));
                } catch (IOException e) {
                    System.err.println("❌ ERROR: No se encuentra el archivo 'bd.properties'");
                    e.printStackTrace();
                    return null;
                }

                // Creamos la conexión directa (Sin Pool, Sin Hikari, Sin Logs molestos)
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String pass = prop.getProperty("passwd");
                
                con = DriverManager.getConnection(url, user, pass);
                // System.out.println("✅ Conexión establecida (Modo Clásico)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return con;
    }
}