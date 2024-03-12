package co.com.sofka.usecase.customer;

import co.com.sofka.model.Customer;
import co.com.sofka.model.exception.ErrorEnum;
import co.com.sofka.model.exception.TechnicalException;
import co.com.sofka.model.gateway.CustomerRepository;
import co.com.sofka.model.gateway.PersonRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindCustomerUseCase {
    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;


    public Mono<Customer> findCustomer(String id) {
        return customerRepository.findCustomer(id)
                .switchIfEmpty(Mono.error(new TechnicalException(ErrorEnum.DONT_EXIST_PERSON.getMessage())))
                .flatMap(this::findPerson);
    }

    private Mono<Customer> findPerson(Customer customer) {
        return personRepository.findPerson(customer.personId())
                .switchIfEmpty(Mono.error(new TechnicalException(ErrorEnum.DONT_EXIST_PERSON.getMessage())))
                .map(person -> customer.toBuilder().person(person).build());
    }
}
