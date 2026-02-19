package com.dam2.daoImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dam2.dao.VentaDao;
import com.dam2.model.Venta;

//ESTA ANOTACIÓN ES NECESARIA PARA QUE SPRING
//LO REGISTRE COMO BEAN SUYO (Y ASÍ LO INSTANCIA
//CUANDO HACE FALTA, ETC...)
@Repository
public class VentaDaoImpl implements VentaDao{

	JdbcTemplate jdbcTemplate;
	RowMapper<Venta> mapper = (rs,num) -> 
		new Venta(rs.getInt("id"), rs.getInt("escaparate"),rs.getString("nif_comprador"),rs.getString("coche"),rs.getString("color"));
	
	public VentaDaoImpl(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//ESTO DEBERÍA HACERSE O TODO O NADA
	//ES DECIR, ES UNA TRANSACCIÓN, PERO NO SE
	//MARCA AQUÍ!
	@Override
	public void cambiarEscaparate(Integer id1, Integer id2) {
		
		
	    String sql1 = 
	    		"UPDATE ventas SET escaparate = 0 "
	    		+ "WHERE id = ?";
	    jdbcTemplate.update(sql1, id1);

	    String sql2 = 
	    		"UPDATE ventas SET escaparate = 1 "
	    		+ "WHERE id = ?";
	    jdbcTemplate.update(sql2, id2);
		
		
	}

}
