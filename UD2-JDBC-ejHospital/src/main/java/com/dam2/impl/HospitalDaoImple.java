package com.dam2.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types; // Importante para el NULL
import java.util.ArrayList;
import java.util.List;

import com.dam2.dao.HospitalDao;
import com.dam2.model.Paciente;

public class HospitalDaoImple implements HospitalDao {
	
	private Connection connection; // Mejor ponerlo private

	public HospitalDaoImple(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void ingresarPaciente(Paciente paciente) {
		// CORRECCIÓN 1: Usamos PreparedStatement. Es OBLIGATORIO si quieres manejar NULLs correctamente.
		// CORRECCIÓN 2: La tabla es 'pacientes', no 'departamentos'.
		String sql = "INSERT INTO pacientes (nombre, enfermedad, medico_id) VALUES (?, ?, ?)";
		
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setString(1, paciente.getNombre());
			ps.setString(2, paciente.getEnfermedad());
			
			// LÓGICA DEL NULL:
			// Si el ID es 0, significa que no tiene médico.
			if (paciente.getMedicoId() == 0) {
				ps.setNull(3, Types.INTEGER); // Guardamos NULL en la BD
			} else {
				ps.setInt(3, paciente.getMedicoId()); // Guardamos el número
			}
			
	        ps.executeUpdate();
	        System.out.println("✅ Paciente ingresado correctamente");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void asignarMedico(int pacienteId, int medicoId) {
		// Aquí usas Statement, está bien, pero cuidado con las comillas si fueran Strings.
		// Al ser int, funciona bien así.
		try (Statement sentencia = connection.createStatement()) {
			
			String sql = "UPDATE pacientes SET medico_id = " + medicoId + " WHERE id = " + pacienteId;
			
			int filasAfectadas = sentencia.executeUpdate(sql);
			
			if (filasAfectadas > 0) {
				System.out.println("✅ Se ha actualizado el paciente");
			} else {
				System.out.println("⚠️ No se ha encontrado el paciente " + pacienteId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void darDeAltaPaciente(int pacienteId) {
		try (Statement sentencia = connection.createStatement()) {
			// CORRECCIÓN: Faltaba el FROM. "DELETE FROM tabla..."
			String sql = "DELETE FROM pacientes WHERE id = " + pacienteId;
			
			int filasAfectadas = sentencia.executeUpdate(sql);
			
			if (filasAfectadas > 0) {
				System.out.println("✅ Se ha eliminado correctamente el paciente");
			} else {
				System.out.println("⚠️ No se ha encontrado ningun paciente con ese id");
			}
			
		} catch (SQLException e) {
			System.out.println("❌ NO SE PUDO BORRAR EL PACIENTE (Posiblemente tenga datos relacionados)");
			e.printStackTrace();
		}
	}

	@Override
	public List<Paciente> listarPacienteMedico(int medicoId) {
		List<Paciente> pacientes = new ArrayList<>();
		
		try (Statement sentencia = connection.createStatement()){
			String sql = "SELECT * FROM pacientes WHERE medico_id = " + medicoId;
			
			ResultSet registros = sentencia.executeQuery(sql);
			
			while (registros.next()) {
				Paciente paciente = new Paciente();
				
				paciente.setId(registros.getInt("id"));
				paciente.setNombre(registros.getString("nombre"));
				paciente.setEnfermedad(registros.getString("enfermedad"));
				paciente.setMedicoId(registros.getInt("medico_id"));
				
				pacientes.add(paciente);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return pacientes; // Correcto
	}

	@Override
	public List<Paciente> listarPacientesEspcialidad(String especialidad) {
		
		List<Paciente> lista = new ArrayList<>();
		
		// CORRECCIÓN: Usamos '?' para el PreparedStatement.
		// No concatenamos la variable aquí.
		String sql = "SELECT p.* " +
				"FROM pacientes p "+
				"JOIN medicos m ON p.medico_id = m.id " +
				"WHERE m.especialidad = ?"; 
		
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			
			// Ahora sí, sustituimos el '?' por el valor
			pst.setString(1, especialidad);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Paciente pac = new Paciente();
				
				pac.setId(rs.getInt("id"));
				pac.setNombre(rs.getString("nombre"));
				pac.setEnfermedad(rs.getString("enfermedad"));
				pac.setMedicoId(rs.getInt("medico_id"));
				
				// CORRECCIÓN IMPRESCINDIBLE: ¡Hay que añadirlo a la lista!
				lista.add(pac);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista; // CORRECCIÓN: Devolvemos la lista, no null.
	}

	@Override
	public List<Paciente> buscarPacientes() {
		List<Paciente> pacientes = new ArrayList<>();
		
		try (Statement sentencia = connection.createStatement()) {
			String sql = "SELECT * FROM pacientes";
			
			ResultSet registros = sentencia.executeQuery(sql);
			
			while (registros.next()) {
				Paciente paciente = new Paciente();
				
				paciente.setId(registros.getInt("id"));
				paciente.setNombre(registros.getString("nombre"));
				paciente.setEnfermedad(registros.getString("enfermedad"));
				paciente.setMedicoId(registros.getInt("medico_id"));
				
				pacientes.add(paciente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pacientes; // CORRECCIÓN: Devolvemos la lista, no null.
	}
}