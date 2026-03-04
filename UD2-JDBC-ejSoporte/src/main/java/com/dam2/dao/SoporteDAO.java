package com.dam2.dao;

import java.time.LocalDate;
import java.util.List;

import com.dam2.model.Clientes;
import com.dam2.model.Comentarios;
import com.dam2.model.Incidencias;

public interface SoporteDAO {
	
	public void insertarCliente(String nombre, String email, String telefono, LocalDate fecha_alta);
	public void insertarAgente(String nombre, String email, Boolean activo);
	public List<Clientes> listarClientes(Long id);
	public void actualizarTelefonoCliente(Long clienteId, String nuevoTelefono);
	public void borrarAgente(Long agenteId);
	public List<Incidencias> incidenciasConDatos(String estado);
	public void crearIncidenciaConPrimerComentario(Incidencias ia, Comentarios c);

}
