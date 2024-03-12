package co.com.sofka.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record Person(String id,
                     String gender,
                     String address,
                     String phone,
                     String identityId,
                     String name,
                     Integer age,
                     Boolean state) {

}
