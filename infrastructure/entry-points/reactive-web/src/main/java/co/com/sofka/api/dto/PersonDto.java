package co.com.sofka.api.dto;

import co.com.sofka.model.Person;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record PersonDto(String id,
                        String gender,
                        String address,
                        String phone,
                        String identityId,
                        String name,
                        Integer age,
                        Boolean state) {

    public static Person from(PersonDto personDto) {
        return Person.builder()
                .id(personDto.id() == null ? UUID.randomUUID().toString() : personDto.id())
                .identityId(personDto.identityId())
                .name(personDto.name())
                .gender(personDto.gender())
                .age(personDto.age())
                .address(personDto.address())
                .phone(personDto.phone())
                .state(personDto.state())
                .build();
    }

    public static PersonDto fromDto(Person person) {
        return PersonDto.builder()
                .id(person.id())
                .identityId(person.identityId())
                .name(person.name())
                .gender(person.gender())
                .age(person.age())
                .address(person.address())
                .phone(person.phone())
                .state(person.state())
                .build();
    }

}