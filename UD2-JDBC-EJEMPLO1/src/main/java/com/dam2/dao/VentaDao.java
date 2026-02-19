package com.dam2.dao;

import java.util.List;

import com.dam2.model.Venta;

public interface VentaDao {
	
	void insertVenta(Venta v);
	
	Venta findById(Integer id);
	
	List<Venta> findAll();
	
	void deleteVentaById(Integer id);

}
