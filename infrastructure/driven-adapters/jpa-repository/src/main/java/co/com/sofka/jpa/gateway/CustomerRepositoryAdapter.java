package co.com.sofka.jpa.gateway;

import co.com.sofka.jpa.entity.CustomerEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

public interface CustomerRepositoryAdapter extends ReactiveCrudRepository<CustomerEntity, String> {
}
