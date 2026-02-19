package com.dam2.model;

import java.time.LocalDate;
import java.util.Objects;

public class Empleado {
	
	private int id;
	private String nombre;
	private String puesto;
	private double salario;
	private LocalDate fechaAlta;
	private int departamentoId;
	public Empleado() {
		super();
	}
	public Empleado(int id, String nombre, String puesto, double salario, LocalDate fechaAlta, int departamentoId) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.puesto = puesto;
		this.salario = salario;
		this.fechaAlta = fechaAlta;
		this.departamentoId = departamentoId;
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
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public int getDepartamentoId() {
		return departamentoId;
	}
	public void setDepartamentoId(int departamentoId) {
		this.departamentoId = departamentoId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(departamentoId, fechaAlta, id, nombre, puesto, salario);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return departamentoId == other.departamentoId && Objects.equals(fechaAlta, other.fechaAlta) && id == other.id
				&& Objects.equals(nombre, other.nombre) && Objects.equals(puesto, other.puesto)
				&& Double.doubleToLongBits(salario) == Double.doubleToLongBits(other.salario);
	}
	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", puesto=" + puesto + ", salario=" + salario
				+ ", fechaAlta=" + fechaAlta + ", departamentoId=" + departamentoId + "]";
	}
	
	

}
