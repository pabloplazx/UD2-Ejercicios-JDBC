package com.dam2.model;

import java.time.LocalDateTime;

public class Comentarios {

	private Long id;
	private Long incidencia_id;
	private String autor_tipo;
	private String autor_nombre;
	private String mensaje;
	private LocalDateTime fecha;
	public Comentarios() {
		super();
	}
	public Comentarios(Long id, Long incidencia_id, String autor_tipo, String autor_nombre, String mensaje,
			LocalDateTime fecha) {
		super();
		this.id = id;
		this.incidencia_id = incidencia_id;
		this.autor_tipo = autor_tipo;
		this.autor_nombre = autor_nombre;
		this.mensaje = mensaje;
		this.fecha = fecha;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIncidencia_id() {
		return incidencia_id;
	}
	public void setIncidencia_id(Long incidencia_id) {
		this.incidencia_id = incidencia_id;
	}
	public String getAutor_tipo() {
		return autor_tipo;
	}
	public void setAutor_tipo(String autor_tipo) {
		this.autor_tipo = autor_tipo;
	}
	public String getAutor_nombre() {
		return autor_nombre;
	}
	public void setAutor_nombre(String autor_nombre) {
		this.autor_nombre = autor_nombre;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
}
