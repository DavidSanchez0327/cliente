package co.com.sofka.api;


import co.com.sofka.api.dto.CustomerDto;
import co.com.sofka.api.dto.PersonDto;
import co.com.sofka.model.Customer;
import co.com.sofka.model.Person;
import co.com.sofka.usecase.customer.FindCustomerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
/*
public class CustomerControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private FindCustomerUseCase findCustomerUseCase;

    @BeforeEach
    public void setUp() {
        Customer customer = getCustomer();
        when(findCustomerUseCase.findCustomer("1")).thenReturn(Mono.just(customer));
    }

    @Test
    public void testFindById() {

        webTestClient.get()
                .uri("/v1/customers/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(CustomerDto.class).isEqualTo(getCustomerDto());

    }

    private static CustomerDto getCustomerDto() {
        PersonDto personDto = PersonDto.builder()
                .id("123456")
                .gender("masculino")
                .address("carrera")
                .phone("3104645768")
                .identityId("1234567890")
                .name("david")
                .age(21)
                .state(Boolean.TRUE)
                .build();
        return CustomerDto.builder()
                .id("asdasd")
                .password("asdasd123123")
                .state(Boolean.TRUE)
                .personId("126789009876543")
                .person(personDto)
                .build();
    }

    private static Customer getCustomer() {
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
        return Customer.builder()
                .id("asdasd")
                .password("asdasd123123")
                .state(Boolean.TRUE)
                .personId("126789009876543")
                .person(person)
                .build();
    }

}
*/