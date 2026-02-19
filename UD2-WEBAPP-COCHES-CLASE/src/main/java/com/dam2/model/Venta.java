package com.dam2.model;

public class Venta {
	
	private Integer id;
	private String nifComprador, coche, color;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNifComprador() {
		return nifComprador;
	}
	public void setNifComprador(String nifComprador) {
		this.nifComprador = nifComprador;
	}
	public String getCoche() {
		return coche;
	}
	public void setCoche(String coche) {
		this.coche = coche;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Venta [id=" + id + ", nifComprador=" + nifComprador + ", coche=" + coche + ", color=" + color
				+ ", getId()=" + getId() + ", getNifComprador()=" + getNifComprador() + ", getCoche()=" + getCoche()
				+ ", getColor()=" + getColor() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	
	
}
