package co.com.sofka.api.dto;

import co.com.sofka.model.Customer;
import lombok.Builder;

@Builder(toBuilder = true)
public record CustomerDto(String id,
                          String password,
                          Boolean state,
                          String personId,
                          PersonDto person) {

    public static Customer from(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.id())
                .password(customerDto.password())
                .state(customerDto.state())
                .personId(customerDto.personId())
                .build();
    }

    public static CustomerDto fromDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.id())
                .password(customer.password())
                .state(customer.state())
                .personId(customer.personId())
                .person(PersonDto.fromDto(customer.person()))
                .build();
    }

}