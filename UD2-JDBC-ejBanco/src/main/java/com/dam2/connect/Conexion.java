package com.dam2.connect;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration // 1. Soy una clase de configuración
@ComponentScan(basePackages = "com.dam2") //2. "Busca de componentes (DAOs, Services) aquí"
@EnableTransactionManagement //3. Activa la magia de las transacciones
public class Conexion {
	
	// BEAN 1: La fuente de datos (la conexion fisica)
	@Bean
	public DataSource dataSource() {
		//Usamos DriverManagerDataSource por sencillez (viene con Spring)
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/banco_db?useSSL=false&serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		
		return dataSource;
		
	}
	
	// BEAN 2: La herramienta para ejecutar SQL
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	// BEAN 3: El gestor de Transacciones (El árbitro)
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		// Necesita el DataSource para saber sobre que BD hacer commit/roolback
		
		return new DataSourceTransactionManager(dataSource);
	}

}
