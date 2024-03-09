package co.com.sofka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String id;
    private String contrasena;
    private Boolean estado;
    private String personaId;
    private Person person;
}
