package co.com.sofka.jpa.mapper;

import co.com.sofka.jpa.entity.CustomerEntity;
import co.com.sofka.jpa.entity.PersonEntity;
import co.com.sofka.model.Customer;
import co.com.sofka.model.Person;

public interface Mapper {



    default CustomerEntity createCustomerEntity(Customer customer) {
        return new CustomerEntity(customer.id(),customer.password(),customer.state(),customer.personId());
    }
    default Customer createCustomer(CustomerEntity customerEntity) {
        return Customer.builder()
                .id(customerEntity.getId())
                .password(customerEntity.getPassword())
                .state(customerEntity.getState())
                .personId(customerEntity.getPersonId())
                .build();
    }
    default PersonEntity createPersonEntity(Person person) {
        return new PersonEntity(person.id(), person.gender(), person.address(),person.phone(),person.identityId(),person.name(),person.age(),person.state());
    }




    default Person createPerson(PersonEntity personEntity) {
        return Person.builder()
                .id(personEntity.getId())
                .identityId(personEntity.getIdentityId())
                .name(personEntity.getName())
                .gender(personEntity.getGender())
                .age(personEntity.getAge())
                .address(personEntity.getAddress())
                .phone(personEntity.getPhone())
                .state(personEntity.getState())
                .build();
    }
}
