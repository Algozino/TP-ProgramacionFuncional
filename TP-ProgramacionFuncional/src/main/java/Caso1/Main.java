package Caso1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Alumno> alumnos = Arrays.asList(
                new Alumno("Juan", 8.5, "Java"),
                new Alumno("Candela", 6.2, "Python"),
                new Alumno("Ignacio", 9.0, "Web"),
                new Alumno("Ana", 5.5, "Java"),
                new Alumno("Luis", 7.8, "Python"),
                new Alumno("Lucia", 9.5, "Web"),
                new Alumno("Marcos", 6.9, "Java")
        );

        // Obtener los nombres de los alumnos aprobados (nota ≥ 7) en mayúsculas y ordenados
        List<String> aprobados = alumnos.stream()
                .filter(a -> a.getNota() >= 7)
                .map(a -> a.getNombre().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
        System.out.println("\nAlumnos aprobados: " + aprobados);

        // Calcular el promedio general de notas
        double promedioGeneral = alumnos.stream()
                .collect(Collectors.averagingDouble(Alumno::getNota));
        System.out.println("\nPromedio general: " + String.format("%.2f", promedioGeneral));

        // Agrupar alumnos por curso usando Collectors.groupingBy()
        Map<String, List<Alumno>> alumnosPorCurso = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));
        System.out.println("\nAlumnos por curso: ");
        alumnosPorCurso.forEach((curso, lista) -> System.out.println(curso + ": " + lista.size() + " alumnos"));

        // Obtener los 3 mejores promedios
        List<Alumno> mejores3Alumnos = alumnos.stream()
                .sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("\nTop 3 alumnos con mejores notas: ");
        mejores3Alumnos.forEach(a -> System.out.println(a));
    }
}
