package co.com.sofka.jpa.gateway;

import co.com.sofka.jpa.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonAdapterRepository extends JpaRepository<PersonEntity, String> {
}
