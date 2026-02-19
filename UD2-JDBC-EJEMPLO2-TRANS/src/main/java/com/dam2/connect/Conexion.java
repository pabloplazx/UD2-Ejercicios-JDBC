package com.dam2.connect;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration //DEFINE BEANS GESTIONADOS POR SPRING
//GESTIÓN: CADA BEAN SE INSTANCIA UNA SOLA VEZ, SE INYECTAN
//ENTRE SÍ..., REMPLAZA LOS ANTIGUOS XML

@ComponentScan(basePackages = "com.dam2") //DICE DÓNDE HAY PAQUETES
//DONDE TIENE QUE BUSCAR CLASES ANOTADAS PARA GESTIONARLAS COMO BEANS TAMBIÉN

@EnableTransactionManagement //HACE QUE SPRING TENGA EN CUENTA
//@transactional, ES DECIR, QUE USE TRANSACCIONES
public class Conexion {

    @Bean
    public DataSource dataSource() throws IOException {

        Properties prop = new Properties();
        prop.load(Conexion.class
                .getClassLoader()
                .getResourceAsStream("bd.properties"));

        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setJdbcUrl(prop.getProperty("url"));
        ds.setUsername(prop.getProperty("user"));
        ds.setPassword(prop.getProperty("passwd"));
        ds.setMaximumPoolSize(10);
        ds.setMinimumIdle(2);

        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }
}

