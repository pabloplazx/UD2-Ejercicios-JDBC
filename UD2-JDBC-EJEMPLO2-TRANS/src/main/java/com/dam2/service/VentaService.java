package com.dam2.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dam2.dao.VentaDao;

//ESTA ANOTACIÓN ES NECESARIA PARA QUE SPRING
//LO REGISTRE COMO BEAN SUYO (Y ASÍ LO INSTANCIA
//CUANDO HACE FALTA, ETC...)
@Service
public class VentaService {

    private VentaDao ventaDao;

    public VentaService(VentaDao ventaDao) {
        this.ventaDao = ventaDao;
    }

    //@Transactional
    public void cambiarEscaparateEnTransaccion(Integer id1, Integer id2) {

        ventaDao.cambiarEscaparate(id1, id2);

        // Forzamos el fallo para ver el rollback
      
        throw new RuntimeException("Provocamos error en cambio de escaparate");
        
    }
}
