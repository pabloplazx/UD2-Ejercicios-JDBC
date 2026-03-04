package com.dam2.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dam2.config.AppConfig;
import com.dam2.model.Alumno;
import com.dam2.repository.AcademiaRepositoryImpl;

import java.util.List;

public class AcademiaApp {

    public static void main(String[] args) {
        
        // 1. Arrancamos Spring usando nuestra clase de configuración
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        // 2. Le pedimos a Spring que nos dé el Repositorio ya montado y listo para usar
        AcademiaRepositoryImpl repo = context.getBean(AcademiaRepositoryImpl.class);

        System.out.println("--- INICIANDO PRUEBAS SPRING JDBC ---");

        // PRUEBA 1: Matricular a un nuevo alumno
        System.out.println("\n1. Matriculando alumno...");
        Alumno nuevoAlumno = new Alumno(0, "Marta Diaz", "marta@email.com", 3);
        repo.matricularAlumno(nuevoAlumno);

        // PRUEBA 2: Buscar alumnos por curso
        System.out.println("\n2. Buscando alumnos del curso 'Java Spring Boot'...");
        List<Alumno> alumnosJava = repo.buscarAlumnosPorCurso("Java Spring Boot");
        for (Alumno a : alumnosJava) {
            System.out.println(" - " + a.getNombre() + " (" + a.getEmail() + ")");
        }

        // PRUEBA 3: Añadir horas a un curso
        System.out.println("\n3. Añadiendo 10 horas al curso con ID 2...");
        repo.extenderHorasCurso(2, 10);

        // PRUEBA 4: Borrar un curso y sus alumnos (Transacción Automática)
        System.out.println("\n4. Borrando el curso 3 y sus alumnos...");
        repo.eliminarCursoYAlumnos(3);

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}