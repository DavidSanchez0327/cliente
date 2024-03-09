package co.com.sofka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String identificacion;
    private String nombre;
    private String genero;
    private Integer edad;
    private String direccion;
    private Integer telefono;
}
