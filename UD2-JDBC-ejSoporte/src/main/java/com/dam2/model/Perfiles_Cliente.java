package com.dam2.model;

public class Perfiles_Cliente {
	
	private Long cliente_id;
	private String direccion;
	private String ciudad;
	private String codigo_postal;
	public Perfiles_Cliente() {
		super();
	}
	public Perfiles_Cliente(Long cliente_id, String direccion, String ciudad, String codigo_postal) {
		super();
		this.cliente_id = cliente_id;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.codigo_postal = codigo_postal;
	}
	public Long getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(Long cliente_id) {
		this.cliente_id = cliente_id;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCodigo_postal() {
		return codigo_postal;
	}
	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	
	

}
