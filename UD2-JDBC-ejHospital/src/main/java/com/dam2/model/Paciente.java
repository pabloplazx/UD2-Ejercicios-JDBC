package com.dam2.model;

import java.util.Objects;

public class Paciente {

	private int id;
	private String nombre;
	private String enfermedad;
	private int medicoId;
	public Paciente() {
		super();
	}
	public Paciente(int id, String nombre, String enfermedad, int medicoId) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.enfermedad = enfermedad;
		this.medicoId = medicoId;
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
	public String getEnfermedad() {
		return enfermedad;
	}
	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}
	public int getMedicoId() {
		return medicoId;
	}
	public void setMedicoId(int medicoId) {
		this.medicoId = medicoId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(enfermedad, id, medicoId, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(enfermedad, other.enfermedad) && id == other.id && medicoId == other.medicoId
				&& Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
		return "Paciente [id=" + id + ", nombre=" + nombre + ", enfermedad=" + enfermedad + ", medicoId=" + medicoId
				+ "]";
	}
	
	
	
	
}
