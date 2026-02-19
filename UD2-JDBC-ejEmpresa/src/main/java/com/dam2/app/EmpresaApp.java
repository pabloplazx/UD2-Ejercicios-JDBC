package com.dam2.app;

import java.time.LocalDate;
import java.util.List;

import com.dam.dao.EmpresaDao;
import com.dam2.connect.Conexion;
import com.dam2.daoImplement.EmpresaDaoImpl;
import com.dam2.model.Departamento;
import com.dam2.model.Empleado;
import com.dam2.services.EmpresaService;


import consola.Leer;

public class EmpresaApp {

	public static void main(String[] args) {
	
	    // -----------------------------------------------------
		
		EmpresaDao dao = null;
		
		dao = new EmpresaDaoImpl(Conexion.getConnection());

		EmpresaService service = new EmpresaService(dao);
		
		int op = 0;
		
		do {
			mostrarMenu();
			System.out.print("Introduzca opcion :");
			op = Leer.datoInt();
			
			switch (op) {
			case 1:
				Departamento dept = añadirDepartamento();
				
				service.creaDepartamento(dept);
				break;
			case 2:
				//(int id, String nombre, String puesto, double salario, LocalDate fechaAlta, int departamentoId)
				Empleado empl = añadirEmpleado();
				
				service.crearEmpleado(empl);
				break;
			case 3:
				System.out.print("Introduzca id del departamento: ");
				int idDeptBuscar = Leer.datoInt();
				
				List<Empleado> empleados = service.listarEmpleadoPorDepartamento(idDeptBuscar);
				
				System.out.println("LISTA EMPLEADOS DEPARTAMENTO CON ID - "+idDeptBuscar);
				for (Empleado empleado : empleados) {
					System.out.println(empleado);
				}
				break;
			case 4:
				System.out.print("Introduzca id del empleado a modificar: ");
				int idBuscarModificar = Leer.datoInt();
				
				System.out.print("Introduca nuevo salario :");
				double nuevoSalario = Leer.datoDouble();
				
				service.modificarSalario(idBuscarModificar, nuevoSalario);
				break;
			case 5:
				System.out.print("Id departamento eliminar: ");
				int idDeptEliminar = Leer.datoInt();
				
				service.eliminarDepartamento(idDeptEliminar);
				break;
			case 6:
				System.out.print("Id empleado eliminar: ");
				int idEmplEliminar = Leer.datoInt();
				
				service.eliminarEmpleado(idEmplEliminar);
				break;
			case 7:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("OPCIÓN NO VÁLIDA");
				break;
				

			}
		} while (op != 7);
	}
	
	
	private static void mostrarMenu() {
		System.out.println("1. - Crear departamento");
		System.out.println("2. - Crear empleado");
		System.out.println("3. - Listar empleados por departamento");
		System.out.println("4. - Modificar el salario de un empleado");
		System.out.println("5. - Eliminar un departamento");
		System.out.println("6. - Eliminar un empleado");
		System.out.println("7. - SALIR");
	}
	
	private static Departamento añadirDepartamento() {
	    // 1. NO pidas el ID.
	    // System.out.print("Id departamento :");
	    // int idDept = Leer.datoInt();
	    
	    System.out.print("Nombre departamento :");
	    String nombreDept = Leer.dato();
	    
	    System.out.print("Ubicacion departamento :");
	    String ubicacionDept = Leer.dato();
	    
	    // 2. Pasa un 0 en el ID. La base de datos pondrá el correcto.
	    Departamento dept = new Departamento(0, nombreDept, ubicacionDept);
	    
	    return dept;
	}

	private static Empleado añadirEmpleado() {
	    // 1. NO pidas el ID.
	    // System.out.print("Id empleado: ");
	    // int idEmpleado = Leer.datoInt();
	    
	    System.out.print("Nombre empleado: ");
	    String nombreEmpleado = Leer.dato();
	    
	    System.out.print("Puesto empleado: ");
	    String puestoEmpleado = Leer.dato();
	    
	    System.out.print("Salario empleado: ");
	    double salarioEmpleado = Leer.datoDouble();
	    
	    System.out.print("Fecha de alta empleado (yyyy-mm-dd): ");
	    // Asegúrate que tu clase Leer devuelve LocalDate. 
	    // Si no, usa: LocalDate.parse(Leer.dato());
	    LocalDate fechaAltaEmpleado = Leer.datoFecha(); 
	    
	    System.out.print("Id departamento empleado: ");
	    int idDepartamentoEmpleado = Leer.datoInt();
	    
	    // 2. Pasa un 0 en el ID.
	    Empleado empleado = new Empleado(0, nombreEmpleado, puestoEmpleado, salarioEmpleado, fechaAltaEmpleado, idDepartamentoEmpleado);
	    return empleado;
	}

}
