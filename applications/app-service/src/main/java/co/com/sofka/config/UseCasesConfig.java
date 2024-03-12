package co.com.sofka.config;

import co.com.sofka.model.gateway.CustomerRepository;
import co.com.sofka.model.gateway.PersonRepository;
import co.com.sofka.usecase.customer.CreateCustomerUseCase;
import co.com.sofka.usecase.customer.DeleteCustomerUseCase;
import co.com.sofka.usecase.customer.FindCustomerUseCase;
import co.com.sofka.usecase.customer.UpdateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UseCasesConfig {


    @Bean
    public CreateCustomerUseCase createCustomerUseCase(CustomerRepository customerRepository, PersonRepository personRepository) {
        return new CreateCustomerUseCase(
                customerRepository, personRepository);
    }

    @Bean
    public DeleteCustomerUseCase deleteCustomerUseCase(CustomerRepository customerRepository, PersonRepository personRepository) {
        return new DeleteCustomerUseCase(
                customerRepository, personRepository);
    }

    @Bean
    public FindCustomerUseCase findCustomerUseCase(CustomerRepository customerRepository, PersonRepository personRepository) {
        return new FindCustomerUseCase(
                customerRepository, personRepository);
    }

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(CustomerRepository customerRepository, PersonRepository personRepository) {
        return new UpdateCustomerUseCase(
                customerRepository, personRepository);
    }
}
