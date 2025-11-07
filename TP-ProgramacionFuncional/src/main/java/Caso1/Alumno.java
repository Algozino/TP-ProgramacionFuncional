package Caso1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Alumno {
    private String nombre;
    private double nota;
    private String curso;
}
