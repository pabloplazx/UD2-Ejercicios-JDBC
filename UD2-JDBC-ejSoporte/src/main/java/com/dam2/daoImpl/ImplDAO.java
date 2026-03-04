package com.dam2.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // solo para RETURN_GENERATED_KEYS (en un método)
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dam2.dao.SoporteDAO;
import com.dam2.model.Clientes;
import com.dam2.model.Comentarios;
import com.dam2.model.Incidencias;

public class ImplDAO implements SoporteDAO {

    private Connection connection;

    public ImplDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertarCliente(String nombre, String email, String telefono, LocalDate fecha_alta) {

        String sql = "INSERT INTO clientes (nombre, email, telefono, fecha_alta) VALUES (?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, email);

            if (telefono == null) ps.setNull(3, Types.VARCHAR);
            else ps.setString(3, telefono);

            ps.setDate(4, java.sql.Date.valueOf(fecha_alta));

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertarAgente(String nombre, String email, Boolean activo) {

        String sql = "INSERT INTO agentes (nombre, email, activo) VALUES (?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setBoolean(3, activo != null ? activo : true);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Clientes> listarClientes(Long id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        List<Clientes> clientes = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet resultados = ps.executeQuery()) {
                while (resultados.next()) {
                    Clientes cliente = new Clientes();

                    cliente.setId(resultados.getLong("id"));
                    cliente.setNombre(resultados.getString("nombre"));
                    cliente.setEmail(resultados.getString("email"));
                    cliente.setTelefono(resultados.getString("telefono"));

                    java.sql.Date fa = resultados.getDate("fecha_alta");
                    cliente.setFecha_alta(fa == null ? null : fa.toLocalDate());

                    clientes.add(cliente);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public void actualizarTelefonoCliente(Long clienteId, String nuevoTelefono) {

        String sql = "UPDATE clientes SET telefono = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            if (nuevoTelefono == null) ps.setNull(1, Types.VARCHAR);
            else ps.setString(1, nuevoTelefono);

            ps.setLong(2, clienteId);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se ha actualizado correctamente");
            } else {
                System.out.println("Id erroneo, no se pudo actualizar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void borrarAgente(Long agenteId) {

        String sql = "DELETE FROM agentes WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, agenteId);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se elimino correctamente");
            } else {
                System.out.println("No se ha podido eliminar (id inexistente o FK)");
            }
        } catch (SQLException e) {
            // si el agente está referenciado en incidencias te saltará excepción de FK
            e.printStackTrace();
        }
    }

    @Override
    public List<Incidencias> incidenciasConDatos(String estado) {

        // OJO: tu clase Incidencias no tiene campos de cliente/agente (nombre/email),
        // así que aquí devolvemos SOLO datos de incidencias.
        String sql =
            "SELECT " +
            "  i.id, i.cliente_id, i.agente_id, i.titulo, i.descripcion, i.prioridad, i.estado, i.fecha_creacion, i.fecha_cierre " +
            "FROM incidencias i " +
            "WHERE i.estado = ? " +
            "ORDER BY i.fecha_creacion DESC";

        List<Incidencias> incidencias = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, estado);

            try (ResultSet resultados = ps.executeQuery()) {
                while (resultados.next()) {

                    Incidencias incidencia = new Incidencias();

                    incidencia.setId(resultados.getLong("id"));
                    incidencia.setCliente_id(resultados.getLong("cliente_id"));

                    // agente_id puede ser null -> getObject
                    Long agenteId = (Long) resultados.getObject("agente_id");
                    incidencia.setAgente_id(agenteId);

                    incidencia.setTitulo(resultados.getString("titulo"));
                    incidencia.setDescripcion(resultados.getString("descripcion"));
                    incidencia.setPrioridad(resultados.getString("prioridad"));
                    incidencia.setEstado(resultados.getString("estado"));

                    Timestamp tsCreacion = resultados.getTimestamp("fecha_creacion");
                    incidencia.setFecha_creacion(tsCreacion == null ? null : tsCreacion.toLocalDateTime());

                    Timestamp tsCierre = resultados.getTimestamp("fecha_cierre");
                    incidencia.setFecha_cierre(tsCierre == null ? null : tsCierre.toLocalDateTime());

                    incidencias.add(incidencia);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return incidencias;
    }

    @Override
    public void crearIncidenciaConPrimerComentario(Incidencias ia, Comentarios c) {

        String sqlIncidencia =
            "INSERT INTO incidencias (cliente_id, agente_id, titulo, descripcion, prioridad, estado) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

        String sqlComentario =
            "INSERT INTO comentarios (incidencia_id, autor_tipo, autor_nombre, mensaje) " +
            "VALUES (?, ?, ?, ?)";

        try {
            connection.setAutoCommit(false);

            long idIncidencia;

            // 1) Insertar incidencia + recoger ID
            try (PreparedStatement ps = connection.prepareStatement(sqlIncidencia, Statement.RETURN_GENERATED_KEYS)) {

                ps.setLong(1, ia.getCliente_id());

                if (ia.getAgente_id() == null) ps.setNull(2, Types.BIGINT);
                else ps.setLong(2, ia.getAgente_id());

                ps.setString(3, ia.getTitulo());
                ps.setString(4, ia.getDescripcion());
                ps.setString(5, ia.getPrioridad());
                ps.setString(6, ia.getEstado() == null ? "ABIERTA" : ia.getEstado());

                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (!rs.next()) throw new SQLException("No se pudo obtener el ID generado de la incidencia");
                    idIncidencia = rs.getLong(1);
                }
            }

            // 2) Insertar comentario (sin fecha: la BD pone CURRENT_TIMESTAMP)
            try (PreparedStatement ps2 = connection.prepareStatement(sqlComentario)) {
                ps2.setLong(1, idIncidencia);
                ps2.setString(2, c.getAutor_tipo());
                ps2.setString(3, c.getAutor_nombre());
                ps2.setString(4, c.getMensaje());
                ps2.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();

        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}