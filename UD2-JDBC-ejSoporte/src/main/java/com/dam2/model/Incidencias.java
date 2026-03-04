package com.dam2.model;

import java.time.LocalDateTime;

public class Incidencias {
	
	private Long id;
	private Long cliente_id;
	private Long agente_id;
	private String titulo;
	private String descripcion;
	private String prioridad;
	private String estado;
	private LocalDateTime fecha_creacion;
	private LocalDateTime fecha_cierre;
	public Incidencias() {
		super();
	}
	public Incidencias(Long id, Long cliente_id, Long agente_id, String titulo, String descripcion, String prioridad,
			String estado, LocalDateTime fecha_creacion, LocalDateTime fecha_cierre) {
		super();
		this.id = id;
		this.cliente_id = cliente_id;
		this.agente_id = agente_id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.prioridad = prioridad;
		this.estado = estado;
		this.fecha_creacion = fecha_creacion;
		this.fecha_cierre = fecha_cierre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(Long cliente_id) {
		this.cliente_id = cliente_id;
	}
	public Long getAgente_id() {
		return agente_id;
	}
	public void setAgente_id(Long agente_id) {
		this.agente_id = agente_id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public LocalDateTime getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(LocalDateTime fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public LocalDateTime getFecha_cierre() {
		return fecha_cierre;
	}
	public void setFecha_cierre(LocalDateTime fecha_cierre) {
		this.fecha_cierre = fecha_cierre;
	}
	

}
