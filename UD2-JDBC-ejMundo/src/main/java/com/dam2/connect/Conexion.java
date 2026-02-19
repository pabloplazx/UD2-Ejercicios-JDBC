package com.dam2.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection con;

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                String url = "jdbc:mysql://localhost:3306/rpg_db?useSSL=false&serverTimezone=UTC";
                String user = "root";
                String pass = "1234"; 
                
                con = DriverManager.getConnection(url, user, pass);
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }
}