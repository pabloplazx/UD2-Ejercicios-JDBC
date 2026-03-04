package com.dam2.model;

import java.time.LocalDate;

public class Clientes {
	
	private Long id;
	private String nombre;
	private String email;
	private String telefono;
	private LocalDate fecha_alta;
	public Clientes() {
		super();
	}
	public Clientes(Long id, String nombre, String email, String telefono, LocalDate fecha_alta) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.fecha_alta = fecha_alta;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public LocalDate getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(LocalDate fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	
	

}
