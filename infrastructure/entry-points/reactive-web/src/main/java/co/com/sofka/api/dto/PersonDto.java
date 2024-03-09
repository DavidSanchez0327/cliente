package co.com.sofka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class PersonDto {

    private final Integer identificacion;
    private final String nombre;
    private final String genero;
    private final Integer edad;
    private final String direccion;
    private final Integer telefono;
}
