package com.dam2.service;

import com.dam2.dao.VentaDao;
import com.dam2.model.Venta;

public class VentasService {
	
	VentaDao dao;
	
	public VentasService(VentaDao dao) {
		this.dao = dao;
	}
	
	public void createVenta(Venta venta) {
		dao.insertVenta(venta);
	}
	
	public Venta getVenta(Integer id) {
		
		return dao.findById(id);
	}

}
