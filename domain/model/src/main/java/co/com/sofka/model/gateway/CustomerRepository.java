package co.com.sofka.model.gateway;

import co.com.sofka.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomerRepository {

    Mono<Customer> findCustomer(String id);
    Mono<Void> deleteCustomer(String id);
    Mono<Customer> saveCustomer(Customer customer);
    Mono<Customer> updateCustomer(Customer customer);
}
