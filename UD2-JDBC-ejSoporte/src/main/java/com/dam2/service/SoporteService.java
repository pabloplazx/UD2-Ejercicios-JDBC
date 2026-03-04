package com.dam2.service;

import java.time.LocalDate;
import java.util.List;

import com.dam2.dao.SoporteDAO;
import com.dam2.model.Clientes;
import com.dam2.model.Comentarios;
import com.dam2.model.Incidencias;

public class SoporteService {

	SoporteDAO dao;

	public SoporteService(SoporteDAO dao) {
		super();
		this.dao = dao;
	}
	
	public void insertarCliente(String nombre, String email, String telefono, LocalDate fecha_alta) {
		dao.insertarCliente(nombre, email, telefono, fecha_alta);
	}
	
	public void insertarAgente(String nombre, String email, Boolean activo) {
		dao.insertarAgente(nombre, email, activo);
	}
	
	public List<Clientes> listarClientes(Long id) {
		return dao.listarClientes(id);
	}
	
	public void actualizarTelefonoCliente(Long clienteId, String nuevoTelefono) {
		dao.actualizarTelefonoCliente(clienteId, nuevoTelefono);
	}
	
	public void borrarAgente(Long agenteId) {
		dao.borrarAgente(agenteId);
	}
	
	public List<Incidencias> incidenciasConDatos(String estado) {
		return dao.incidenciasConDatos(estado);
	}
	
	public void crearIncidenciaConPrimerComentario(Incidencias ia, Comentarios c) {
		dao.crearIncidenciaConPrimerComentario(ia, c);
	}
}
