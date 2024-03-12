package co.com.sofka.jpa.adapter;

import co.com.sofka.jpa.gateway.CustomerAdapterRepository;
import co.com.sofka.jpa.mapper.Mapper;
import co.com.sofka.model.Customer;
import co.com.sofka.model.exception.ErrorEnum;
import co.com.sofka.model.exception.TechnicalException;
import co.com.sofka.model.gateway.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CustomerAdapter implements CustomerRepository, Mapper {
    private final CustomerAdapterRepository customerAdapterRepository;


    @Override
    public Mono<Customer> findCustomer(String id) {
        return Mono.justOrEmpty(customerAdapterRepository.findById(id))
                .map(this::createCustomer);
    }

    @Override
    public Mono<Void> deleteCustomer(String id) {
        customerAdapterRepository.deleteById(id);
        return Mono.empty();
    }

    @Override
    public Mono<Customer> saveCustomer(Customer customer) {
        return Mono.just(customerAdapterRepository.save(createCustomerEntity(customer)))
                .map(this::createCustomer);
    }

    @Override
    public Mono<Customer> updateCustomer(Customer customer) {
        return Mono.justOrEmpty(customerAdapterRepository.findById(customer.id()))
                .flatMap(customerEntity -> Objects.nonNull(customerEntity) || customerEntity.getId().isEmpty()
                        ? Mono.error(new TechnicalException(ErrorEnum.ERROR_VALIDATE_CUSTOMER.getMessage()))
                        : Mono.just(customerAdapterRepository.save(createCustomerEntity(customer)))
                        .map(this::createCustomer));
    }

}
