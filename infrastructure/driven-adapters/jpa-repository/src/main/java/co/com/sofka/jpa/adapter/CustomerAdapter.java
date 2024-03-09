package co.com.sofka.jpa.adapter;

import co.com.sofka.jpa.gateway.CustomerRepositoryAdapter;
import co.com.sofka.jpa.mapper.Mapper;
import co.com.sofka.model.Customer;
import co.com.sofka.model.exception.TechnicalException;
import co.com.sofka.model.gateway.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class CustomerAdapter implements CustomerRepository, Mapper {
    private final CustomerRepositoryAdapter customerRepositoryAdapter;
    private static final String ERROR_VALIDATE_CUSTOMER = "No existe el cliente";

    @Autowired
    public CustomerAdapter(CustomerRepositoryAdapter customerRepositoryAdapter) {
        this.customerRepositoryAdapter = customerRepositoryAdapter;
    }

    @Override
    public Mono<Customer> findCustomer(String id) {
        return customerRepositoryAdapter.findById(id)
                .map(this::mapToCustomer);
    }

    @Override
    public Mono<Void> deleteCustomer(String id) {
        return customerRepositoryAdapter.deleteById(id);
    }

    @Override
    public Mono<Customer> saveCustomer(Customer customer) {
        return customerRepositoryAdapter.save(mapToCustomerEntity(customer))
                .map(this::mapToCustomer);
    }

    @Override
    public Mono<Customer> updateCustomer(Customer customer) {
        return customerRepositoryAdapter.findById(customer.getId())
                .flatMap(customerEntity -> Objects.nonNull(customerEntity) || customerEntity.getId().isEmpty()
                        ? Mono.error(new TechnicalException(ERROR_VALIDATE_CUSTOMER))
                        : customerRepositoryAdapter.save(mapToCustomerEntity(customer))
                        .map(this::mapToCustomer));
    }
}
