package com.dam2.services;


import java.util.List;

import com.dam.dao.EmpresaDao;
import com.dam2.model.Departamento;
import com.dam2.model.Empleado;

public class EmpresaService {
	
	EmpresaDao dao;
	
	public EmpresaService(EmpresaDao dao) {
		super();
		this.dao = dao;
	}

	public void creaDepartamento(Departamento dept) {
		dao.crearDepartamento(dept);
	}
	
	public void crearEmpleado(Empleado empl) {
		dao.crearEmpleado(empl);
	}
	
	public List<Empleado> listarEmpleadoPorDepartamento(int idDept) {
		
		return dao.listarEmpleadosPorDepartament(idDept);
	}

	public void modificarSalario(int empleadoId, double nuevoSalario) {
		dao.modificarSalario(empleadoId, nuevoSalario);
	}
	
	public void eliminarDepartamento(int idDept) {
		dao.eliminarDepartamento(idDept);
	}
	
	public void eliminarEmpleado(int idEmple) {
		dao.eliminarEmpleado(idEmple);
	}
	
	

}
