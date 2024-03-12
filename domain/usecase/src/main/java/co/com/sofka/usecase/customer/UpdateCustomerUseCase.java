package co.com.sofka.usecase.customer;

import co.com.sofka.model.Customer;
import co.com.sofka.model.Person;
import co.com.sofka.model.exception.ErrorEnum;
import co.com.sofka.model.exception.TechnicalException;
import co.com.sofka.model.gateway.CustomerRepository;
import co.com.sofka.model.gateway.PersonRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateCustomerUseCase {

    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;

    public Mono<Customer> updateCustomer(Customer customer) {
        return personRepository.findPerson(customer.person().identityId())
                .switchIfEmpty(Mono.error(new TechnicalException(ErrorEnum.DONT_EXIST_PERSON.getMessage())))
                .flatMap(person -> validateIfUpdatePerson(customer, person));
    }

    private Mono<Customer> validateIfUpdatePerson(Customer customer, Person person) {
        return person.equals(customer.person())
                ? customerRepository.updateCustomer(customer)
                : updatePersonAndCustomer(customer);
    }

    private Mono<Customer> updatePersonAndCustomer(Customer customer) {
        return personRepository.updatePerson(customer.person())
                .flatMap(person1 -> customerRepository.updateCustomer(customer)
                        .map(customer1 -> customer1.toBuilder().person(person1).build()));
    }
}
