package com.dam2.daoImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dam2.dao.CuentaDao;


//@Repository: Le dice a Spring "Esta clase maneja datos, trátala con Cariño
@Repository
public class CuentaDaoImpl implements CuentaDao {

	//Esta es nuestra herramienta principal
	private final JdbcTemplate jdbcTemplate;

	public CuentaDaoImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void retirarDinero(Integer id, Double cantidad) {
		// Cuidado con el orden de los interrogantes (?)
		// 1er ? = cantidad
		// 2do ? = id
		String sql = "UPDATE cuentas SET saldo = saldo - ? WHERE id = ?";
		
		
		//Ejecutamos
		jdbcTemplate.update(sql, cantidad, id);
		
		System.out.println("DAO: Retirados "+cantidad+" del ID: "+id);
		
	}

	@Override
	public void ingresarDinero(Integer id, Double cantidad) {
		String sql = "UPDATE cuentas SET saldo = saldo + ? WHERE id = ?";
		
		jdbcTemplate.update(sql, cantidad, id);
		
		System.out.println("DAO: Ingresados "+cantidad+" al ID: "+id);
		
	}
}
