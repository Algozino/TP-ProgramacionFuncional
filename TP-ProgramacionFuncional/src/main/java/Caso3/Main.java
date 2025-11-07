package Caso3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Libro> libros = Arrays.asList(
                new Libro("El señor de los anillos", "Tolkien", 1069, 25.50),
                new Libro("Los juegos del hambre", "Suzanne Collins", 424, 22.00),
                new Libro("Percy Jackson", "Rick Riordan", 288, 18.00),
                new Libro("El principito", "Antoine de Saint-Exupéry", 120, 24.00),
                new Libro("Harry Potter", "J.K. Rowling", 223, 17.50),
                new Libro("Animales fantásticos", "J.K. Rowling", 160, 15.00)
        );

        // Listar los títulos de los libros con más de 300 páginas, ordenados alfabéticamente
        List<String> titulosLargos = libros.stream()
                .filter(l -> l.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("\nLibros con mas de 300 páginas: ");
        titulosLargos.forEach(t -> System.out.println(t));

        // Calcular el promedio de páginas de todos los libros
        double promedioPaginas = libros.stream()
                .collect(Collectors.averagingInt(Libro::getPaginas));
        System.out.println("\nPromedio de paginas: " + String.format("%.2f", promedioPaginas));

        // Agrupar los libros por autor y contar cuántos libros tiene cada uno
        Map<String, Long> librosPorAutor = libros.stream()
                .collect(Collectors.groupingBy(
                        Libro::getAutor,
                        Collectors.counting()
                ));
        System.out.println("\nConteo de libros por autor: ");
        librosPorAutor.forEach((autor, count) -> System.out.println(autor + ": " + count + " libro(s)"));

        // Obtener el libro más caro de la lista
        Optional<Libro> libroMasCaro = libros.stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(Libro::getPrecio)));

        System.out.println("\nLibro más caro:");
        libroMasCaro.ifPresent(libro -> System.out.println(libro));
    }
}
