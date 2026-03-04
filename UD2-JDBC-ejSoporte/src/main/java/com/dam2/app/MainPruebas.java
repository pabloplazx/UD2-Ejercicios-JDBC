package com.dam2.app;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.dam2.daoImpl.ImplDAO;
import com.dam2.model.Clientes;
import com.dam2.model.Comentarios;
import com.dam2.model.Incidencias;
import com.dam2.service.SoporteService;

// Ajusta el import al paquete real de tu clase conexión:
import com.dam2.connect.Conexion; // <-- cambia esto

public class MainPruebas {

    public static void main(String[] args) {

        // 1) Abrir conexión leyendo bd.properties (lo hace tu clase Conexion)
        try (Connection con = Conexion.getConnection()) {   // <-- si tu método se llama distinto, cámbialo

            // 2) DAO + Service
            ImplDAO dao = new ImplDAO(con);
            SoporteService service = new SoporteService(dao);

            System.out.println("✅ Conexión OK");

            // ===== PRUEBAS =====

            // A) Insertar cliente
            service.insertarCliente(
                    "Pepe López",
                    "pepe@demo.com",
                    "611222333",
                    LocalDate.now()
            );
            System.out.println("✅ Cliente insertado");

            // B) Insertar agente
            service.insertarAgente("Agente 1", "agente1@soporte.com", true);
            System.out.println("✅ Agente insertado");

            // C) Listar cliente por id (ajusta el id a uno que exista)
            List<Clientes> clientes = service.listarClientes(1L);
            System.out.println("\n=== CLIENTE ID 1 ===");
            for (Clientes c : clientes) {
                System.out.println(
                        c.getId() + " | " + c.getNombre() + " | " + c.getEmail() + " | " + c.getTelefono()
                );
            }

            // D) Actualizar teléfono (ajusta el id)
            service.actualizarTelefonoCliente(1L, "699999999");
            System.out.println("✅ Teléfono actualizado");

            // E) Crear incidencia + primer comentario (transacción)
            Incidencias inc = new Incidencias();
            inc.setCliente_id(1L);    // cliente existente
            inc.setAgente_id(1L);     // agente existente (o null)
            inc.setTitulo("Prueba JDBC");
            inc.setDescripcion("Creación de incidencia con su primer comentario");
            inc.setPrioridad("MEDIA");
            inc.setEstado("ABIERTA");
            inc.setFecha_creacion(LocalDateTime.now()); // si no la insertas en SQL, no pasa nada

            Comentarios com = new Comentarios();
            com.setAutor_tipo("CLIENTE");
            com.setAutor_nombre("Pepe López");
            com.setMensaje("Este es el primer comentario");
            com.setFecha(LocalDateTime.now()); // si no la insertas en SQL, no pasa nada

            service.crearIncidenciaConPrimerComentario(inc, com);
            System.out.println("✅ Incidencia + comentario creados");

            // F) Listar incidencias por estado
            System.out.println("\n=== INCIDENCIAS ABIERTAS ===");
            List<Incidencias> incidencias = service.incidenciasConDatos("ABIERTA");
            for (Incidencias i : incidencias) {
                System.out.println(i.getId() + " | " + i.getTitulo() + " | " + i.getPrioridad()
                        + " | " + i.getEstado() + " | " + i.getFecha_creacion());
            }

            // G) Borrar agente (ajusta el id y cuidado con FKs)
            // service.borrarAgente(1L);
            // System.out.println("✅ Agente borrado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}