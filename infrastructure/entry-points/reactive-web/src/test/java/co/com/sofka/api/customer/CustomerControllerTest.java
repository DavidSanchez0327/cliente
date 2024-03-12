package co.com.sofka.api.customer;

import co.com.sofka.api.dto.CustomerDto;
import co.com.sofka.api.dto.PersonDto;
import co.com.sofka.model.Customer;
import co.com.sofka.model.Person;
import co.com.sofka.usecase.customer.CreateCustomerUseCase;
import co.com.sofka.usecase.customer.FindCustomerUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerControllerTest {


    @Test
    void testSaveCustomer() {
        CreateCustomerUseCase createCustomerUseCase = Mockito.mock(CreateCustomerUseCase.class);

        CustomerController controller = new CustomerController(null, createCustomerUseCase, null, null);

        when(createCustomerUseCase.createCustomer(any())).thenReturn(Mono.just(getCustomer()));

        CustomerDto customerDto = getCustomerDto();
        StepVerifier.create(controller.saveCustomer(customerDto))
                .expectNext(customerDto).verifyComplete();
    }

    @Test
    void testFindCustomer() {
        FindCustomerUseCase findCustomerUseCase = Mockito.mock(FindCustomerUseCase.class);

        CustomerController controller = new CustomerController(findCustomerUseCase, null, null, null);

        when(findCustomerUseCase.findCustomer(any())).thenReturn(Mono.just(getCustomer()));

        CustomerDto customerDto = getCustomerDto();
        StepVerifier.create(controller.findCustomer(any()))
                .expectNext(customerDto).verifyComplete();
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
