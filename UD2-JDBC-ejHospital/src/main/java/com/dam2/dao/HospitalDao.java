package com.dam2.dao;

import java.util.List;

import com.dam2.model.Paciente;

public interface HospitalDao {
	
	void ingresarPaciente(Paciente paciente);
	
	List<Paciente> buscarPacientes();
	
	void asignarMedico(int pacienteId, int medicoId);
	
	void darDeAltaPaciente(int paciente);
	
	List<Paciente> listarPacienteMedico(int medicoId);
	
	List<Paciente> listarPacientesEspcialidad(String espcialidad);
	
	

}
