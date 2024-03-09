package co.com.sofka.jpa.gateway;

import co.com.sofka.jpa.entity.PersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

public interface PersonRepositoryAdapter extends ReactiveCrudRepository<PersonEntity, String> {
}
