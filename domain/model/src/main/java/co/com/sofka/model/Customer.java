package co.com.sofka.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record Customer(String id,
                       String password,
                       Boolean state,
                       String personId,
                       Person person) {

}
