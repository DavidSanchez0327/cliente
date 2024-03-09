package co.com.sofka.usecase.customer;

import co.com.sofka.model.gateway.CustomerRepository;
import co.com.sofka.model.gateway.PersonRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteCustomerUseCase {


    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;


    public Mono<Boolean> deleteCustomer(String id) {
        return customerRepository.findCustomer(id)
                .flatMap(customer -> customerRepository.deleteCustomer(id)
                        .flatMap(unused ->  personRepository.deletePerson(customer.getPersonaId())
                                .thenReturn(Boolean.TRUE)));
    }
}
