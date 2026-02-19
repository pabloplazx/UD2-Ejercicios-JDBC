package com.dam.dao;

import java.util.List;

import com.dam2.model.Departamento;
import com.dam2.model.Empleado;

public interface EmpresaDao {
	
	public void crearDepartamento(Departamento dept);
	
	public void crearEmpleado(Empleado empleado);
	
	public List<Empleado> listarEmpleadosPorDepartament(int deptId);
	
	public void modificarSalario(int empleadoId, double nuevoSalario);
	
	public void eliminarEmpleado(int idEmpleado);
	
	public void eliminarDepartamento(int idDepartamento);

}
