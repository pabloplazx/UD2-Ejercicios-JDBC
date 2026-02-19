package com.dam2.app;

import java.util.List;

import com.dam2.connect.Conexion;
import com.dam2.dao.HospitalDao;
import com.dam2.impl.HospitalDaoImple;
import com.dam2.model.Paciente;
import com.dam2.services.HospitalService;

import consola.Leer;

public class HospitalApp {
	
	public static void main(String[] args) {
		
		HospitalDao dao = null;
		
		dao = new HospitalDaoImple(Conexion.getConnection());
		
		HospitalService service = new HospitalService(dao);
		
		int op = 0;
		
		do {
			mostrarMenu();
			System.out.print("Introduzca opcion: ");
			op = Leer.datoInt();
			
			switch (op) {
			case 1:
				Paciente paciente = añadirPaciente();
				
				service.ingresarPaciente(paciente);
				break;
			case 2:
				System.out.print("Introduzca id del paciente: ");
				int idPaciente = Leer.datoInt();
				
				System.out.println("Introduza id del nuevo medico");
				int idNuevoMedico = Leer.datoInt();
				
				service.asignarMedico(idPaciente, idNuevoMedico);
				break;
			case 3:
				System.out.print("Id paciente alta: ");
				int idAlta = Leer.datoInt();
				
				service.darAlta(idAlta);
				break;
			case 4: 
				System.out.print("Id medico: ");
				int idMedico = Leer.datoInt();
				
				List<Paciente> pacientesMedico = service.listaPacienteMedico(idMedico);
				
				for (Paciente pac : pacientesMedico) {
					System.out.println(pac);
				}
				break;
			case 5:
				System.out.print("Especialidad: ");
				String especialidad = Leer.dato();
				
				List<Paciente> pacientesEspecialidad = service.listarPacienteEspecialidad(especialidad);
				
				for (Paciente pac : pacientesEspecialidad) {
					System.out.println(pac);
				}
				
				break;
			case 6:
				List<Paciente> pacientes = service.buscarPacientes();
				
				for (Paciente pac : pacientes) {
					System.out.println(pac);
				}
				break;
			case 7:
				System.out.println("SALIENDO...");
				break;
			default:
				System.out.println("Opción no validad");
			}
		} while (op != 7);
		
		
	}
	
	private static void mostrarMenu() {
		System.out.println("1. Ingresar paciente");
		System.out.println("2. Asignar medico");
		System.out.println("3. Dar de alta un paciente");
		System.out.println("4. Listar pacientes de un medico");
		System.out.println("5. Listar pacientes por especialidad");
		System.out.println("6. Buscar pacientes");
		System.out.println("7. SALIR");
	}
	
	private static Paciente añadirPaciente() {
		
		System.out.print("Nombre: ");
		String nombre = Leer.dato();
		
		System.out.print("Enfermedad: ");
		String enfermedad = Leer.dato();
		
		System.out.print("Id de su medico: ");
		int idMedico = Leer.datoInt();
		
		Paciente paciente = new Paciente(0, nombre, enfermedad, idMedico);
		
		return paciente;
	}

}
