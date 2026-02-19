package com.dam2.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dam2.dao.MundoDao;
import com.dam2.model.Heroe;

public class MundoDaoImpl implements MundoDao {

    private Connection connection;

    public MundoDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void reclutarHeroe(Heroe heroe) {
        // Concatenamos las cadenas con comillas simples ('') y los números sin ellas
        String sql = "INSERT INTO heroes (nombre, clase, nivel, gremio_id) " +
                     "VALUES ('" + heroe.getNombre() + "', '" + heroe.getClase() + 
                     "', " + heroe.getNivel() + ", " + heroe.getGremioId() + ")";

        try (Statement sentencia = connection.createStatement()) {
            sentencia.executeUpdate(sql);
            System.out.println("¡Héroe reclutado con éxito!");
        } catch (SQLException e) {
            System.out.println("Error al reclutar: " + e.getMessage());
        }
    }

    @Override
    public List<Heroe> mostraHeroesPorGremio(String nombreGremio) {
        List<Heroe> heroesGremio = new ArrayList<>();

        // Traemos TODAS las columnas que Java va a pedir (id, nombre, clase, nivel, gremio_id)
        String sql = "SELECT h.id, h.nombre, h.clase, h.nivel, h.gremio_id " +
                     "FROM heroes h " +
                     "INNER JOIN gremios g ON h.gremio_id = g.id " +
                     "WHERE g.nombre = '" + nombreGremio + "'";

        try (Statement sentencia = connection.createStatement()) {
            ResultSet resultados = sentencia.executeQuery(sql);

            while (resultados.next()) {
                Heroe heroe = new Heroe();

                heroe.setId(resultados.getInt("id"));
                heroe.setNombre(resultados.getString("nombre"));
                heroe.setClase(resultados.getString("clase"));
                heroe.setNivel(resultados.getInt("nivel"));
                heroe.setGremioId(resultados.getInt("gremio_id"));

                heroesGremio.add(heroe);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar héroes: " + e.getMessage());
        }
        
        // ¡CAMBIO CLAVE! Devolvemos la lista en lugar de null para evitar NullPointerException
        return heroesGremio;
    }

    @Override
    public void subirNivelGremio(int gremioId, int nivelesSubir) {
        String sql = "UPDATE heroes SET nivel = nivel + " + nivelesSubir + " WHERE gremio_id = " + gremioId;
        
        try (Statement sentencia = connection.createStatement()) {
            int filasAfectadas = sentencia.executeUpdate(sql);

            if (filasAfectadas > 0) {
                System.out.println("Héroes mejorados: " + filasAfectadas);
            } else {
                System.out.println("No se ha podido realizar el cambio.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disolverGremio(int gremioId) {
        String sqlHeroes = "DELETE FROM heroes WHERE gremio_id = " + gremioId;
        String sqlGremio = "DELETE FROM gremios WHERE id = " + gremioId; // Añadido el FROM

        try {
            connection.setAutoCommit(false); // Iniciamos transacción manual

            try (Statement sentencia = connection.createStatement()) {
                
                // Borramos los héroes primero
                int heroesBorrados = sentencia.executeUpdate(sqlHeroes);

                // Luego borramos el gremio
                int gremioBorrado = sentencia.executeUpdate(sqlGremio);

                if (gremioBorrado > 0) {
                    connection.commit(); // Todo fue bien, guardamos
                    System.out.println("Se ha borrado el gremio y sus " + heroesBorrados + " héroes.");
                } else {
                    System.out.println("No se encontró el gremio.");
                    connection.rollback(); 
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fatal. Haciendo Rollback... " + e.getMessage());
            try {
                connection.rollback(); // Si hay fallo, deshacemos
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true); // Devolvemos el control automático a la conexión
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}