package com.dam2.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dam2.connect.Conexion;
import com.dam2.service.VentaService;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext con =
                new AnnotationConfigApplicationContext(Conexion.class);

        VentaService service = con.getBean(VentaService.class);

        try {
            service.cambiarEscaparateEnTransaccion(5, 6);
        } catch (Exception e) {
            System.out.println("Rollback realizado por Spring");
        }

        con.close();
    }
}
