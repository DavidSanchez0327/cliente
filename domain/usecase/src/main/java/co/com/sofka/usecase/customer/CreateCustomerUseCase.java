package co.com.sofka.usecase.customer;

import co.com.sofka.model.Customer;
import co.com.sofka.model.gateway.CustomerRepository;
import co.com.sofka.model.gateway.PersonRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateCustomerUseCase {

    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;

    public Mono<Customer> createCustomer(Customer customer) {
        return personRepository.savePerson(customer.getPerson())
                .flatMap(person ->  customerRepository.saveCustomer(customer));
    }
}
