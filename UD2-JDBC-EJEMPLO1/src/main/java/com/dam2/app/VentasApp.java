package com.dam2.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.dam2.connect.Conexion;
import com.dam2.dao.VentaDao;
import com.dam2.daoImpl.VentaDaoImpl;
import com.dam2.model.Venta;
import com.dam2.service.VentasService;

public class VentasApp {

	public static void main(String[] args) {
		
		
		//VentaDao dao = new VentaDaoImpl(Conexion.getConnection());
		VentaDao dao = null;
		try {
			dao = new VentaDaoImpl(Conexion.getPool().getConnection());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		VentasService service = new VentasService(dao);
		
		
//		Venta venta = new Venta();
//		venta.setNifComprador("6666");
//		venta.setCoche("Seat");
//		venta.setColor("blanco");
//		service.createVenta(venta);
		
		System.out.println(service.getVenta(1));

	}

}
