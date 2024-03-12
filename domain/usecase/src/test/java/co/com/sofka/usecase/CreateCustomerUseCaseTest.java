package co.com.sofka.usecase;

import co.com.sofka.model.Customer;
import co.com.sofka.model.Person;
import co.com.sofka.model.gateway.CustomerRepository;
import co.com.sofka.model.gateway.PersonRepository;
import co.com.sofka.usecase.customer.CreateCustomerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateCustomerUseCaseTest {

    @Mock
    CustomerRepository customerRepository;
    @Mock
    PersonRepository personRepository;


    @InjectMocks
    CreateCustomerUseCase createCustomerUseCase;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        createCustomerUseCase = new CreateCustomerUseCase(customerRepository, personRepository);
    }


    @Test
    void testCreateCustomer() {

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
        when(personRepository.savePerson(any())).thenReturn(Mono.just(person));
        when(customerRepository.saveCustomer(any())).thenReturn(Mono.just(customer));

        StepVerifier.create(createCustomerUseCase.createCustomer(customer))
                .expectNext(customer).verifyComplete();

    }
}
