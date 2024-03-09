package co.com.sofka.api.mapper;

import co.com.sofka.api.dto.CustomerDto;
import co.com.sofka.api.dto.PersonDto;
import co.com.sofka.model.Customer;
import co.com.sofka.model.Person;

public interface Mapper {


    default CustomerDto mapToCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .contrasena(customer.getContrasena())
                .estado(customer.getEstado())
                .person(mapToPersonDto(customer.getPerson()))
                .build();

    }

    default Customer mapToCustomer (CustomerDto customer) {
        return Customer.builder()
                .id(customer.getId())
                .contrasena(customer.getContrasena())
                .estado(customer.getEstado())
                .person(mapToPerson(customer.getPerson()))
                .build();

    }
    default PersonDto mapToPersonDto(Person person) {
        return PersonDto.builder()
                .identificacion(Integer.valueOf(person.getIdentificacion()))
                .edad(person.getEdad())
                .direccion(person.getDireccion())
                .nombre(person.getNombre())
                .telefono(person.getTelefono())
                .build();

    }

    default Person mapToPerson (PersonDto personDto) {
        return Person.builder()
                .identificacion(String.valueOf(personDto.getIdentificacion()))
                .edad(personDto.getEdad())
                .direccion(personDto.getDireccion())
                .nombre(personDto.getNombre())
                .telefono(personDto.getTelefono())
                .build();

    }
}
