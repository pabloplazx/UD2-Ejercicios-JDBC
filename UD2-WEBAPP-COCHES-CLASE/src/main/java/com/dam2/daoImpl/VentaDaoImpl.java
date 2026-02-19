package com.dam2.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
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
			String sql = "SELECT * FROM ventas WHERE id = "+id;
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
		
		List<Venta> ventas = new LinkedList<>();
		
		try(PreparedStatement ps = conection.prepareStatement("SELECT * FROM ventas");
				ResultSet rs = ps.executeQuery()){
			
			while(rs.next()) {
				Venta v = new Venta();
				v.setId(rs.getInt("id"));
				v.setCoche(rs.getString("coche"));
				v.setColor(rs.getString("color"));
				v.setNifComprador(rs.getString("nif_comprador"));
				ventas.add(v);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return ventas;
	}
		
	@Override
	public boolean deleteVentaById(Integer id) {
		
		try(PreparedStatement ps 
				= conection.prepareStatement("DELETE FROM ventas WHERE id = ?")){
			ps.setInt(1, id);
			return ps.executeUpdate() == 1;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}

}
