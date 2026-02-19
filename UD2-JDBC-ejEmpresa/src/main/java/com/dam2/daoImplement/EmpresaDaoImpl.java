package com.dam2.daoImplement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dam.dao.EmpresaDao;
import com.dam2.model.Departamento;
import com.dam2.model.Empleado;


public class EmpresaDaoImpl implements EmpresaDao{
	
	Connection connection;
	

	public EmpresaDaoImpl(Connection connection) {
		super();
		this.connection = connection;
	}


	@Override
	public void crearDepartamento(Departamento dept) {
	    
	    // Fíjate en el lío de comillas.
	    // Estructura: " ... VALUES ('" + variable + "', '" + variable + "')"
	    
	    try (Statement sentencia = connection.createStatement()) {
	        
	        String sql = "INSERT INTO departamentos (nombre, ubicacion) " +
	                     "VALUES ('" + dept.getNombre() + "', '" + dept.getUbicacion() + "')";
	        
	        // Descomenta esto si quieres ver "la magia" (y el error) en la consola
	        // System.out.println("SQL Generado: " + sql);
	        
	        sentencia.executeUpdate(sql);
	        System.out.println("✅ Departamento creado correctamente");
	        
	    } catch (SQLException e) {
	        // Reto 1: Capturar duplicados
	        if (e.getErrorCode() == 1062) { // 1062 es el código de error de MySQL para "Duplicate entry"
	            System.err.println("❌ Error: Ya existe un departamento con ese nombre.");
	        } else {
	            e.printStackTrace();
	        }
	    }
	}


	@Override
	public void crearEmpleado(Empleado empleado) {
		
		try (Statement sentencia = connection.createStatement()){
	        String sql = "INSERT INTO empleados(id, nombre, puesto, salario, fecha_alta, dept_id) "
	                + "VALUES ('" + empleado.getId()+ "','" 
	                + empleado.getNombre() + "',"
	    	        + empleado.getPuesto() + "',"
	    	        + empleado.getSalario() + "',"
	    	        + empleado.getFechaAlta() + "',"
	                + empleado.getDepartamentoId() + ")"; // Sin comillas y cerramos paréntesis
	        
	        sentencia.executeUpdate(sql);
	        System.out.println("Empreado creado correctamente");
		} catch (SQLException e) {
			System.err.println("NO SE PUDO CREAR EL EMPLEADO");
			e.printStackTrace();
		}
		
	}


	@Override
	public List<Empleado> listarEmpleadosPorDepartament(int deptId) {
		List<Empleado> empleados = new ArrayList<>();
		
		try (Statement sentencia = connection.createStatement()) {
			String sql = "SELECT * FROM empleados WHERE dept_id = "+deptId;
			
			ResultSet registros = sentencia.executeQuery(sql);
			
			while (registros.next()) {
				Empleado empleado = new Empleado();
				
				empleado.setId(registros.getInt("id"));
				empleado.setNombre(registros.getString("nombre"));
				empleado.setPuesto(registros.getString("puesto"));
				empleado.setSalario(registros.getDouble("salario"));
				empleado.setFechaAlta(registros.getDate("fecha_alta").toLocalDate());
				empleado.setDepartamentoId(registros.getInt("dept_id"));
				
				empleados.add(empleado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empleados;
	}


	@Override
	public void modificarSalario(int empleadoId, double nuevoSalario) {
		// 1. Construimos la SQL
		
		String sql = "UPDATE empleados SET salario ="+nuevoSalario+" WHERE id = "+empleadoId;
		
		try (Statement sentencia = connection.createStatement()) {
			
			
			//2. Ejecutamos al orden es una modificacion, asi que usaremos executeUpdate
			int filasAfectadas = sentencia.executeUpdate(sql);
			
			if (filasAfectadas > 0) {
				System.out.println("Salario actualizado correctamente");
			} else {
				System.out.println("No se encontro ningun empleado con ese id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void eliminarEmpleado(int idEmpleado) {	
		
		try (Statement sentencia = connection.createStatement()) {
			String sql = "DELETE empleados WHERE id = "+idEmpleado;
			
			int filasAfectadas = sentencia.executeUpdate(sql);
			
			if (filasAfectadas > 0) {
				System.out.println("Se ha eliminado el empleado correctamente");
			} else {
				System.out.println("No se encontro el id del empleado");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}


	@Override
	public void eliminarDepartamento(int idDepartamento) {
		
		try (Statement sentencia = connection.createStatement()) {
			
			String sql = "DELETE departamentos WHERE id = "+idDepartamento;
			
			int filasAfectadas = sentencia.executeUpdate(sql);
			
			if (filasAfectadas > 0) {
				System.out.println("El departamento se borro correctamente");
			} else {
				System.out.println("No se encontro el id de dicho departamento");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}