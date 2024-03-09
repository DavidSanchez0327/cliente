package co.com.sofka.usecase.customer;

import co.com.sofka.model.Customer;
import co.com.sofka.model.gateway.CustomerRepository;
import co.com.sofka.model.gateway.PersonRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindCustomerUseCase {

    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;

    public Mono<Customer> findCustomer(String id){
        return customerRepository.findCustomer(id)
                .flatMap(customer -> personRepository.findPerson(customer.getPersonaId())
                        .map(person -> customer.toBuilder().person(person).build()));
    }
}
