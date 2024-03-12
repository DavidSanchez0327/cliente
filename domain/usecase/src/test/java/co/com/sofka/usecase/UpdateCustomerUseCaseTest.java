package co.com.sofka.usecase;

import co.com.sofka.model.Customer;
import co.com.sofka.model.Person;
import co.com.sofka.model.gateway.CustomerRepository;
import co.com.sofka.model.gateway.PersonRepository;
import co.com.sofka.usecase.customer.UpdateCustomerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UpdateCustomerUseCaseTest {


    @Mock
    CustomerRepository customerRepository;
    @Mock
    PersonRepository personRepository;


    @InjectMocks
    UpdateCustomerUseCase updateCustomerUseCase;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        updateCustomerUseCase = new UpdateCustomerUseCase(customerRepository, personRepository);
    }


    @Test
    void testUpdateCustomer() {

        Person person = Person.builder()
                .id("123456")
                .gender("masculino")
                .address("carrera")
                .phone("3104645768")
                .identityId("1234567890")
                .name("david")
                .age(21)
                .state(Boolean.TRUE)
                .build();
        Customer customer = Customer.builder()
                .id("asdasd")
                .password("asdasd123123")
                .state(Boolean.TRUE)
                .personId("126789009876543")
                .person(person)
                .build();
        when(personRepository.findPerson(any())).thenReturn(Mono.just(person));
        when(customerRepository.updateCustomer(any())).thenReturn(Mono.just(customer));

        StepVerifier.create(updateCustomerUseCase.updateCustomer(customer))
                .expectNext(customer).verifyComplete();

    }

    @Test
    void testUpdateCustomerWithPerson() {

        Person person = Person.builder()
                .id("123456")
                .gender("masculino")
                .address("carrera")
                .phone("3104645768")
                .identityId("1234567890")
                .name("david")
                .age(21)
                .state(Boolean.TRUE)
                .build();
        Person person2 = Person.builder()
                .id("1234567")
                .gender("masculino")
                .address("carrera")
                .phone("3104645768")
                .identityId("1234567890")
                .name("david")
                .age(21)
                .state(Boolean.TRUE)
                .build();
        Customer customer = Customer.builder()
                .id("asdasd")
                .password("asdasd123123")
                .state(Boolean.TRUE)
                .personId("126789009876543")
                .person(person)
                .build();
        when(personRepository.findPerson(any())).thenReturn(Mono.just(person2));
        when(personRepository.updatePerson(any())).thenReturn(Mono.just(person));
        when(customerRepository.updateCustomer(any())).thenReturn(Mono.just(customer));

        StepVerifier.create(updateCustomerUseCase.updateCustomer(customer))
                .expectNext(customer).verifyComplete();

    }
}
