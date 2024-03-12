package co.com.sofka.api.customer;

import co.com.sofka.api.dto.CustomerDto;
import co.com.sofka.api.dto.PersonDto;
import co.com.sofka.usecase.customer.CreateCustomerUseCase;
import co.com.sofka.usecase.customer.DeleteCustomerUseCase;
import co.com.sofka.usecase.customer.FindCustomerUseCase;
import co.com.sofka.usecase.customer.UpdateCustomerUseCase;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static co.com.sofka.api.dto.CustomerDto.from;


@RestController
@RequestMapping(value = "/v1/clientes")
@RequiredArgsConstructor
public class CustomerController {


    private final FindCustomerUseCase findCustomerUseCase;
    private final CreateCustomerUseCase createCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto) {
        return createCustomerUseCase.createCustomer(from(customerDto).toBuilder()
                        .id(UUID.randomUUID().toString())
                        .state(Boolean.TRUE)
                        .person(PersonDto.from(customerDto.person()))
                        .build())
                .map(CustomerDto::fromDto);
    }

    @PutMapping()
    public Mono<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) {

        return updateCustomerUseCase.updateCustomer(from(customerDto).toBuilder()
                        .person(PersonDto.from(customerDto.person()))
                        .build())
                .map(CustomerDto::fromDto);
    }

    @GetMapping("/{id}")
    public Mono<CustomerDto> findCustomer(@PathVariable("id") String id) {

        return findCustomerUseCase.findCustomer(id)
                .map(CustomerDto::fromDto);
    }

    @GetMapping("/{id}/name")
    public Mono<String> findNameCustomer(@PathVariable("id") String id) {

        return findCustomerUseCase.findCustomer(id)
                .map(customer -> customer.person().name());
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> deleteCustomer(@PathVariable("id") String id) {

        return deleteCustomerUseCase.deleteCustomer(id);
    }
}
