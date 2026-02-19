package com.dam2.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.dam2.dao.VentaDao;
import com.dam2.model.Venta;

public class VentaDaoImpl implements VentaDao{

	Connection conection;
	
	public VentaDaoImpl(Connection con){
		conection = con;
	}
	
	@Override
	public void insertVenta(Venta v) {
		
		
		try (Statement sentencia = conection.createStatement()){
				String sql = "INSERT INTO ventas(nif_comprador,coche,color) "
				+ "VALUES ('"+v.getNifComprador()+"','"
				+v.getCoche()+"','"+
				v.getColor()+"')";
				sentencia.executeUpdate(sql);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		
	}

	@Override
	public Venta findById(Integer id) {
		
		Venta v = null;
		try (Statement sentencia = conection.createStatement()){
			String sql = "SELECT *FROM ventas WHERE id = "+id;
			ResultSet registros = sentencia.executeQuery(sql);
			while(registros.next()) {
				v = new Venta();
				v.setId(registros.getInt("id"));
				v.setCoche(registros.getString("coche"));
				v.setColor(registros.getString("color"));
				v.setNifComprador(registros.getString("nif_comprador"));
			}
			
	}catch(SQLException ex) {
		ex.printStackTrace();
	}
	
		return v;
	}

	@Override
	public List<Venta> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVentaById(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
