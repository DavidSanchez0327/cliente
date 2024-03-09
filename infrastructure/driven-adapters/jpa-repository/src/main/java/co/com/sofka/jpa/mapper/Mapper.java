package co.com.sofka.jpa.mapper;

import co.com.sofka.jpa.entity.CustomerEntity;
import co.com.sofka.jpa.entity.PersonEntity;
import co.com.sofka.model.Customer;
import co.com.sofka.model.Person;

public interface Mapper {

    default CustomerEntity mapToCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId())
                .contrasena(customer.getContrasena())
                .estado(customer.getEstado())
                .personaId(customer.getPersonaId())
                .build();

    }

    default Customer mapToCustomer (CustomerEntity customer) {
        return Customer.builder()
                .id(customer.getId())
                .contrasena(customer.getContrasena())
                .estado(customer.getEstado())
                .personaId(customer.getPersonaId())
                .build();

    }
    default PersonEntity mapToPersonEntity(Person person) {
        return PersonEntity.builder()
                .identificacion(person.getIdentificacion())
                .edad(person.getEdad())
                .direccion(person.getDireccion())
                .nombre(person.getNombre())
                .telefono(person.getTelefono())
                .build();

    }

    default Person mapToPerson (PersonEntity personEntity) {
        return Person.builder()
                .identificacion(personEntity.getIdentificacion())
                .edad(personEntity.getEdad())
                .direccion(personEntity.getDireccion())
                .nombre(personEntity.getNombre())
                .telefono(personEntity.getTelefono())
                .build();

    }
}
