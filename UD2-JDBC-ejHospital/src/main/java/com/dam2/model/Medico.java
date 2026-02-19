package com.dam2.model;

import java.util.Objects;

public class Medico {
	
	private int id;
	private String nombre;
	private String especialidad;
	public Medico() {
		super();
	}
	public Medico(int id, String nombre, String especialidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.especialidad = especialidad;
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
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	@Override
	public int hashCode() {
		return Objects.hash(especialidad, id, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		return Objects.equals(especialidad, other.especialidad) && id == other.id
				&& Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
		return "Medico [id=" + id + ", nombre=" + nombre + ", especialidad=" + especialidad + "]";
	}
	
	

}
