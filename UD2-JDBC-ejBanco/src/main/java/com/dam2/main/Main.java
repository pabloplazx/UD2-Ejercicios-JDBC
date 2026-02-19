package com.dam2.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dam2.connect.Conexion;
import com.dam2.service.BancoService;

public class Main {

	public static void main(String[] args) {
		
		// 1. Arrancamos el "motor" de Spring leyendo nuestra clase de configuración
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(Conexion.class);
		
		// 2. Le pedimos a Spring que nos dé el Servicio (que ya tiene el DAO inyectado por dentro)
		BancoService servicio = context.getBean(BancoService.class);
		
		
		// ---------------------------------------------------------
		// CASO A: Transferencia Normal (100€) -> COMMIT
		// ---------------------------------------------------------
		System.out.println("\n**************************************************");
		System.out.println("CASO A: Transferencia de 100€ (Debe funcionar)");
		System.out.println("**************************************************");
		
		try {
			// Ana (ID 1) envía 100€ a Pepe (ID 2)
			// Como la cantidad es menor a 1000, no saltará nuestra excepción provocada.
			servicio.realizarTransferencia(1, 2, 100.0);
		} catch (Exception e) {
			System.out.println("Error inesperado en Caso A: " + e.getMessage());
		}
		
		
		// ---------------------------------------------------------
		// CASO B: Transferencia Gigante (5000€) -> ROLLBACK
		// ---------------------------------------------------------
		System.out.println("\n**************************************************");
		System.out.println("CASO B: Transferencia de 5000€ (Debe fallar y hacer ROLLBACK)");
		System.out.println("**************************************************");
		
		try {
			// Intentamos sacar 5000€ de Ana.
			// El DAO ejecutará el UPDATE para restar (en memoria temporal), 
			// pero luego el Servicio lanzará nuestra RuntimeException.
			servicio.realizarTransferencia(1, 2, 5000.0);
			
		} catch (Exception e) {
			// Al saltar la excepción en el servicio, la capturamos aquí.
			// Spring, de forma invisible, ya le ha dicho a MySQL: "¡Deshaz el UPDATE anterior!"
			System.out.println("\n>> ¡EXCEPCIÓN CAPTURADA EN EL MAIN! <<");
			System.out.println("Mensaje: " + e.getMessage());
			System.out.println("Spring ha detectado esto y ha ejecutado un ROLLBACK automático.");
		}
		
		// 3. Apagamos Spring limpiamente
		context.close();
	}
}