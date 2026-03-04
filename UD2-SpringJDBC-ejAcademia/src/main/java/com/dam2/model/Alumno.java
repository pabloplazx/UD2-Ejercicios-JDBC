package com.dam2.model;

public class Alumno {
	
	private int id;
	private String nombre;
	private String email;
	private int cursoId;
	public Alumno() {
		super();
	}
	public Alumno(int id, String nombre, String email, int cursoId) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.cursoId = cursoId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getCursoId() {
		return cursoId;
	}
	public void setCursoId(int cursoId) {
		this.cursoId = cursoId;
	}
	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", email=" + email + ", cursoId=" + cursoId + "]";
	}
	
	

}
