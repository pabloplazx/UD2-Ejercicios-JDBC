package com.dam2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dam2.dao.CuentaDao;

@Service
public class BancoService {
	
	private final CuentaDao cuentaDao;

	public BancoService(CuentaDao cuentaDao) {
		super();
		this.cuentaDao = cuentaDao;
	}
	
	// 2. LA CLAVE: @Transactional
	// Spring abre una transacción al empezar este método
	// Si el método termina bien -> COMMIT (Guarda todo).
	// Si el método lanza una RunTimeException -> ROOLBACK (Deshace todo).
	@Transactional
	public void realizarTransferencia(Integer idOrigen, Integer idDestino, Double cantidad) {
		
		System.out.println("\n--- INICIO TRANSACCIÓN ---");
		
		// Paso 1: Sacar dinero
		cuentaDao.retirarDinero(idOrigen, cantidad);
		
		// --- SIMULACIÓN DE FALLO ---
		// Si intentamos mover más de 1000 euros, simularemos que "se va la luz"
		// o explota el servidor
		if (cantidad > 100) {
			System.out.println("!!! ERROR CRÍTICO DETECTADO !!!");
			System.out.println("Lanzando excepción para provocar Rollback...");
			throw new RuntimeException("Error simulado: Importe demasiado alto");
		}
		
		// Paso 2: Meter dinero
		cuentaDao.ingresarDinero(idDestino, cantidad);
		System.out.println("--- FIN TRANSACCIÓN (EXITO) --- \n");
		
		
			
		}
	
}
