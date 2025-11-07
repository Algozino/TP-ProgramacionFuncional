package Caso4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Empleado> empleados = Arrays.asList(
                new Empleado("Juan", "Ventas", 2100.0, 30),
                new Empleado("Candela", "TI", 3500.0, 25),
                new Empleado("Ignacio", "Ventas", 2300.0, 35),
                new Empleado("Ana", "Marketing", 1900.0, 28),
                new Empleado("Luis", "TI", 3200.0, 22)
        );

        // Obtener empleados con salario > 2000, ordenados por salario descendente
        List<Empleado> empleadosSalarioAlto = empleados.stream()
                .filter(e -> e.getSalario() > 2000)
                .sorted(Comparator.comparingDouble(Empleado::getSalario).reversed())
                .collect(Collectors.toList());
        System.out.println("\nEmpleados con salario > 2000 (ordenados desc):");
        empleadosSalarioAlto.forEach(e -> System.out.println(e));

        // Calcular el salario promedio general
        double promedioSalario = empleados.stream()
                .collect(Collectors.averagingDouble(Empleado::getSalario));
        System.out.println("\nSalario promedio general: $" + String.format("%.2f", promedioSalario));

        // Agrupar por departamento y calcular la suma de salarios de cada uno
        Map<String, Double> sumaSalariosPorDepto = empleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)
                ));
        System.out.println("\nSuma de salarios por departamento: ");
        sumaSalariosPorDepto.forEach((depto, suma) -> System.out.println(depto + ": $" + String.format("%.2f", suma)));

        // Obtener los nombres de los 2 empleados más jóvenes
        List<String> empleadosMasJovenes = empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                .limit(2)
                .map(Empleado::getNombre)
                .collect(Collectors.toList());
        System.out.println("\nNombres de los 2 empleados más jóvenes: ");
        empleadosMasJovenes.forEach(nombre -> System.out.println(nombre));
    }
}
