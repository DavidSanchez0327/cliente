package co.com.sofka.api.customer;

import co.com.sofka.api.dto.CustomerDto;
import co.com.sofka.api.mapper.Mapper;
import co.com.sofka.usecase.customer.CreateCustomerUseCase;
import co.com.sofka.usecase.customer.DeleteCustomerUseCase;
import co.com.sofka.usecase.customer.FindCustomerUseCase;
import co.com.sofka.usecase.customer.UpdateCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class CustomerController implements Mapper {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final FindCustomerUseCase findCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;


    @PostMapping("/cliente")
    public Mono<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto) {

        return createCustomerUseCase.createCustomer(mapToCustomer(customerDto)
                        .toBuilder()
                        .id(UUID.randomUUID().toString())
                        .estado(Boolean.TRUE)
                        .build())
                .map(this::mapToCustomerDto);
    }

    @PutMapping("/cliente")
    public Mono<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) {

        return updateCustomerUseCase.updateCustomer(mapToCustomer(customerDto))
                .map(this::mapToCustomerDto);
    }

    @GetMapping("/cliente/{id}")
    public Mono<CustomerDto> findCustomer(@PathVariable String id) {

        return findCustomerUseCase.findCustomer(id)
                .map(this::mapToCustomerDto);
    }

    @DeleteMapping("/cliente/{id}")
    public Mono<Boolean> deleteCustomer(@PathVariable String id) {

        return deleteCustomerUseCase.deleteCustomer(id);
    }
}
