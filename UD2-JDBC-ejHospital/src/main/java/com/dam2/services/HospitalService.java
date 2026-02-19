package com.dam2.services;

import java.util.List;

import com.dam2.dao.HospitalDao;
import com.dam2.model.Paciente;

public class HospitalService {
	
	HospitalDao dao;
	
	
	public HospitalService(HospitalDao dao) {
		super();
		this.dao = dao;
	}

	public void ingresarPaciente(Paciente paciente)  {
		dao.ingresarPaciente(paciente);
	}
	
	public void asignarMedico(int pacienteId, int medicoId) {
		dao.asignarMedico(pacienteId, pacienteId);
	}
	
	public void darAlta(int paciente) {
		dao.darDeAltaPaciente(paciente);
	}
	
	public List<Paciente> listaPacienteMedico(int medicoId) {
		return dao.listarPacienteMedico(medicoId);
	}
	
	public List<Paciente> listarPacienteEspecialidad(String especialidad) {
		return dao.listarPacientesEspcialidad(especialidad);
	}
	
	public List<Paciente> buscarPacientes() {
		return dao.buscarPacientes();
	}
	
	

}
