package Caso2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Producto> productos = Arrays.asList(
                new Producto("Laptop", "Electronica", 1200.50, 10),
                new Producto("Teclado", "Electronica", 80.00, 50),
                new Producto("Chomba", "Ropa", 45.99, 120),
                new Producto("Telefono", "Electronica", 750.00, 30),
                new Producto("Zapatilla", "Ropa", 90.75, 80)
        );

        // Listar los productos con precio mayor a 100, ordenados por precio descendente.
        List<Producto> productosCaros = productos.stream()
                .filter(p -> p.getPrecio() > 100)
                .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
                .collect(Collectors.toList());
        System.out.println("\nProductos con precio > 100 (ordenados desc): ");
        productosCaros.forEach(p -> System.out.println(p));

        // Agrupar productos por categoría y calcular el stock total.
        Map<String, Integer> stockPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)
                ));
        System.out.println("\nStock total por categoría: ");
        stockPorCategoria.forEach((cat, stock) -> System.out.println(cat + ": " + stock + " unidades"));

        // Generar un String separando con ";" cada producto (nombre y precio).
        String reporteProductos = productos.stream()
                .map(p -> p.getNombre() + ": $" + p.getPrecio())
                .collect(Collectors.joining("; "));
        System.out.println("\nReporte (Nombre: Precio): ");
        System.out.println(reporteProductos);

        // Calcular el precio promedio general y por categoría. [cite: 33]
        double promedioGeneral = productos.stream()
                .collect(Collectors.averagingDouble(Producto::getPrecio));
        System.out.println("\nPrecio promedio general: $" + String.format("%.2f", promedioGeneral));

        Map<String, Double> promedioPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));
        System.out.println("   Precio promedio por categoría: ");
        promedioPorCategoria.forEach((cat, prom) -> System.out.println(cat + ": $" + String.format("%.2f", prom)));
    }
}

