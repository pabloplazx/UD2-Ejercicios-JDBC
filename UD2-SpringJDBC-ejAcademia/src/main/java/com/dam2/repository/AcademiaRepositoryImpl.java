package com.dam2.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.dam2.model.Alumno;

// 1. Le decimos a Spring que esta clase es un Repositorio de base de datos
@Repository 
public class AcademiaRepositoryImpl {

    // 2. Declaramos la herramienta mágica de Spring
    private final JdbcTemplate jdbcTemplate;

    // 3. Inyección de dependencias (Spring nos pasa el JdbcTemplate automáticamente al constructor)
    public AcademiaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    // ... aquí irán nuestros métodos ...
    public void matricularAlumno(Alumno alumno) {
        // La consulta SQL es exactamente igual que siempre
        String sql = "INSERT INTO alumnos (nombre, email, curso_id) VALUES (?, ?, ?)";

        // ¡MIRA QUÉ LIMPIO! Ni try-catch, ni abrir/cerrar conexiones.
        // update() devuelve directamente el número de filas afectadas.
        int filasAfectadas = jdbcTemplate.update(
            sql, 
            alumno.getNombre(), 
            alumno.getEmail(), 
            alumno.getCursoId()
        );

        if (filasAfectadas > 0) {
            System.out.println("✅ Alumno " + alumno.getNombre() + " matriculado con éxito.");
        }
    }
    
    public List<Alumno> buscarAlumnosPorCurso(String nombreCurso) {
        String sql = "SELECT a.id, a.nombre, a.email, a.curso_id " +
                     "FROM alumnos a " +
                     "INNER JOIN cursos c ON a.curso_id = c.id " +
                     "WHERE c.nombre = ?";

        // Usamos jdbcTemplate.query(). 
        // Parámetro 1: la SQL
        // Parámetro 2: El RowMapper (cómo convertir la fila en objeto)
        // Parámetro 3: El valor del interrogante (?)
        
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Alumno alumno = new Alumno();
            alumno.setId(rs.getInt("id"));
            alumno.setNombre(rs.getString("nombre"));
            alumno.setEmail(rs.getString("email"));
            alumno.setCursoId(rs.getInt("curso_id"));
            return alumno;
        }, nombreCurso);
    }
    
    public void extenderHorasCurso(int cursoId, int horasExtra) {
        String sql = "UPDATE cursos SET horas = horas + ? WHERE id = ?";

        // Pasamos la SQL y luego las variables en el mismo orden que los interrogantes
        int cursosActualizados = jdbcTemplate.update(sql, horasExtra, cursoId);

        if (cursosActualizados > 0) {
            System.out.println("✅ Se han añadido " + horasExtra + " horas al curso.");
        } else {
            System.out.println("⚠️ No se encontró ningún curso con ese ID.");
        }
    }
    
 // ¡ESTA ES LA MAGIA! Spring protege toda la transacción por ti
    @Transactional 
    public void eliminarCursoYAlumnos(int cursoId) {
        
        // 1. Borramos los alumnos primero (Clave Foránea)
        String sqlAlumnos = "DELETE FROM alumnos WHERE curso_id = ?";
        int alumnosBorrados = jdbcTemplate.update(sqlAlumnos, cursoId);
        
        // 2. Borramos el curso
        String sqlCurso = "DELETE FROM cursos WHERE id = ?";
        int cursoBorrado = jdbcTemplate.update(sqlCurso, cursoId);
        
        if (cursoBorrado > 0) {
            System.out.println("✅ Curso eliminado junto con sus " + alumnosBorrados + " alumnos.");
        } else {
            System.out.println("⚠️ El curso no existe.");
        }
    }
}
