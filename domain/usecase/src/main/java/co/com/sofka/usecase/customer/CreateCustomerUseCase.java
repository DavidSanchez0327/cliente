package co.com.sofka.usecase.customer;

import co.com.sofka.model.Customer;
import co.com.sofka.model.Person;
import co.com.sofka.model.exception.TechnicalException;
import co.com.sofka.model.gateway.CustomerRepository;
import co.com.sofka.model.gateway.PersonRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateCustomerUseCase {

    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;

    public Mono<Customer> createCustomer(Customer customer) {
        return personRepository.savePerson(customer.person().toBuilder().state(Boolean.TRUE).build())
                .flatMap(person -> saveCustomer(customer, person));
    }

    private Mono<Customer> saveCustomer(Customer customer, Person person) {
        return customerRepository.saveCustomer(customer.toBuilder().personId(person.id()).build())
                .switchIfEmpty(Mono.error(new TechnicalException("error creando el customer")))
                .map(customer1 -> customer1.toBuilder().person(person).build());
    }
}
