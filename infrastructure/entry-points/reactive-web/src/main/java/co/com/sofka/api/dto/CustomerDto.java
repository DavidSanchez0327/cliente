package co.com.sofka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class CustomerDto {

    private final String id;
    private final String contrasena;
    private final Boolean estado;
    private final PersonDto person;
}
