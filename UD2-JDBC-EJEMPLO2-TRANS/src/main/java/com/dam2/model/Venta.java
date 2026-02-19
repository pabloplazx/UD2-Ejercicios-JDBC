package com.dam2.model;

public class Venta {
	
	private Integer id, escaparate;
	private String nifComprador, coche, color;
	
	
	
	public Venta() {
		super();
	}
	public Venta(Integer escaparate, String nifComprador, String coche, String color) {
		super();
		this.escaparate = escaparate;
		this.nifComprador = nifComprador;
		this.coche = coche;
		this.color = color;
	}
	public Venta(Integer id, Integer escaparate, String nifComprador, String coche, String color) {
		super();
		this.id = id;
		this.escaparate = escaparate;
		this.nifComprador = nifComprador;
		this.coche = coche;
		this.color = color;
	}
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
	
	public Integer getEscaparate() {
		return escaparate;
	}
	public void setEscaparate(Integer escaparate) {
		this.escaparate = escaparate;
	}
	@Override
	public String toString() {
		return "Venta [id=" + id + ", nifComprador=" + nifComprador + ", coche=" + coche + ", color=" + color
				+ ", getId()=" + getId() + ", getNifComprador()=" + getNifComprador() + ", getCoche()=" + getCoche()
				+ ", getColor()=" + getColor() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	
	
}
