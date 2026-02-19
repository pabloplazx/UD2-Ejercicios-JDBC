package com.dam2.model;

public class Cuenta {
	
	private Integer id;
	private String titulo;
	private Double saldo;
	public Cuenta() {
		super();
	}
	public Cuenta(Integer id, String titulo, Double saldo) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.saldo = saldo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", titulo=" + titulo + ", saldo=" + saldo + "]";
	}
	
	

}
